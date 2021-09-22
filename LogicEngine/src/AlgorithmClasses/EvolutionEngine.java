
package AlgorithmClasses;

import DataClasses.AlgorithmData.AmountOfObjectsCalc;
import DataClasses.AlgorithmData.Parent;
import DataClasses.AlgorithmData.Generation;
import DataClasses.FileInputDataClasses.TimeTable;
import DataTransferClasses.AlgorithmReferenceData;
import DataTransferClasses.EvolutionEngineData;
import DataTransferClasses.MutationData;
import DataTransferClasses.ProgressData;
import javafx.application.Platform;
import sun.security.jca.GetInstance;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class EvolutionEngine {
    private Integer m_InitialPopulationAmount;
    private Integer m_NumOfGenerations;
    private Integer m_ReqFitness;
    private Long m_ReqMinutesInMillis;
    private List<eStoppingCondition> m_StoppingConditionList;
    private Integer m_PrintingReq;
    private Selection m_Selection;
    private Crossover m_Crossover;
    private Mutations m_Mutations;
    private Generation m_Generation;
    private Boolean m_isStop;

    public EvolutionEngine()
    {
        m_Mutations=new Mutations();
        m_StoppingConditionList=new ArrayList<>();
        m_isStop= Boolean.FALSE;
    }

    public AlgorithmReferenceData getAlgoReferenceData()
    {
        Integer minutes=null;
        if(m_ReqMinutesInMillis!=null) {
             minutes = (int) TimeUnit.MILLISECONDS.toMinutes(m_ReqMinutesInMillis);
        }
        return new AlgorithmReferenceData(m_InitialPopulationAmount,m_NumOfGenerations,m_ReqFitness,minutes,m_Crossover,m_Selection,m_StoppingConditionList);
    }
    public Integer getInitialPopulation() {
        return m_InitialPopulationAmount;
    }

    public Selection getSelection() {
        return m_Selection;
    }

    public Crossover getCrossover() {
        return m_Crossover;
    }

    public Mutations getMutations() {
        return m_Mutations;
    }

    public Integer getNumOfGenerations() {
        return m_NumOfGenerations;
    }

    public Integer getPrintingReq() {
        return m_PrintingReq;
    }

    public Integer getReqFitness() {
        return m_ReqFitness;
    }

    public MutationData getMutationDataByString(String i_String){
        Mutation mutation= m_Mutations.getMutationByString(i_String);
        return (new MutationData(mutation));
    }

    public void setMutationSettings(String i_String,MutationData i_MutationData) {
        Mutation mutation=m_Mutations.getMutationByString(i_String);
        mutation.setChar(i_MutationData.getComponent());
        mutation.setProbability(i_MutationData.getProbability());
        mutation.setTupples(i_MutationData.getTupples());
    }
    public Boolean getStopBoolean() {
        synchronized (m_isStop) {
            return m_isStop;
        }
    }

    public void setStoppingConditionList(List<eStoppingCondition> i_StoppingConditionList) {
        this.m_StoppingConditionList = i_StoppingConditionList;
    }

    public void setInitialPopulationAmount(Integer i_InitialPopulationAmount) {
        this.m_InitialPopulationAmount = i_InitialPopulationAmount;
    }

    public void setNumOfGenerations(Integer i_NumOfGenerations) {
        m_NumOfGenerations = i_NumOfGenerations;
    }

    public void setSelection(Selection i_Selection) {
        m_Selection = i_Selection;
    }

    public void setCrossover(Crossover i_Crossover) {
        m_Crossover = i_Crossover;
    }

    public void setMutations(Mutations i_Mutations) {
        m_Mutations = i_Mutations;
    }

    public void setPrintingReq(Integer i_PrintingReq) {
        m_PrintingReq = i_PrintingReq;
    }

    public void setReqFitness(Integer i_ReqFitness) {
        m_ReqFitness = i_ReqFitness;
    }

    public void setReqMinutes(Integer i_ReqMinutes) {
        m_ReqMinutesInMillis = TimeUnit.MINUTES.toMillis(i_ReqMinutes);
    }

    public void setStopBoolean(Boolean i_isStop) {
        synchronized (m_isStop) {
            m_isStop = i_isStop;
        }
    }

    public void initialSolutions(AmountOfObjectsCalc i_AmountOfObj)
    {
        m_Generation =new Generation();
        for(int i = 1; i<= m_InitialPopulationAmount; i++)
        {
            Parent timeTableSolution=new Parent(i_AmountOfObj.getMaxAmountOfLessons());
            timeTableSolution.buildSolution(i_AmountOfObj);
            m_Generation.addParentToGeneration(timeTableSolution);
        }

    }

    public synchronized void activateAlgorithm(TimeTable i_TimeTable, AmountOfObjectsCalc i_AmountOfObj,EvolutionEngineData i_EvolutionEngineData ,Consumer<ProgressData> i_ProgressDataConsumer) {
        setStopBoolean(false);
        ProgressData progressTracker=new ProgressData(0, 0, (long)0,m_PrintingReq);
        Integer counter=0,generationsMade=0,bestFitness=0;
        Long timePassedInMillis=(long)0;
        Instant startCountingTime,endCountingTime;
        initialSolutions(i_AmountOfObj);
        i_TimeTable.getRules().calculateFitnesses(m_Generation,i_TimeTable);
        m_Generation.sortGenerationByFitness();

        while(!getStopBoolean()) {
            while(counter< m_PrintingReq && !getStopBoolean()) {

                startCountingTime=Instant.now();
                //activating selection (crossover activation is inside)
                m_Generation=m_Selection.createNextGeneration(m_Generation,m_Crossover,m_InitialPopulationAmount,i_AmountOfObj);

                //activating mutations
                m_Mutations.scanAndActivateMutations(m_Generation,i_AmountOfObj);

                i_TimeTable.getRules().calculateFitnesses(m_Generation,i_TimeTable);
                m_Generation.sortGenerationByFitness();
                counter++;
                generationsMade++;
                if(counter==1) {
                    i_EvolutionEngineData.setBestSolution(m_Generation.getParentByIndex(0));
                    bestFitness= i_EvolutionEngineData.getBestSolutionFitness();
                }
                else if(i_EvolutionEngineData.getBestSolutionFitness()<m_Generation.getParentByIndex(0).getFitness()) {
                    i_EvolutionEngineData.setBestSolution(m_Generation.getParentByIndex(0));
                    bestFitness= i_EvolutionEngineData.getBestSolutionFitness();
                }
                endCountingTime=Instant.now();
                timePassedInMillis+= Duration.between(startCountingTime,endCountingTime).toMillis();
                progressTracker.setNewValues(generationsMade,bestFitness,timePassedInMillis);
                if(!getStopBoolean()) {
                    setStopBoolean(checkStoppingConditions(m_NumOfGenerations, generationsMade, m_ReqFitness, bestFitness, m_ReqMinutesInMillis, timePassedInMillis, m_StoppingConditionList));
                }
                i_ProgressDataConsumer.accept(progressTracker);
                if(Thread.interrupted()) {
                    try {
                        i_TimeTable.getRules().recheckBestSolution(i_EvolutionEngineData.getBestSolution(),i_TimeTable,i_EvolutionEngineData);
                        i_EvolutionEngineData.updateRulesAverage();
                        this.wait();
                    } catch (InterruptedException e) {
                        System.out.println("Interrupted while waiting");
                    }
                }
            }
            i_EvolutionEngineData.addToGeneration2BestFitnessMap(generationsMade,m_Generation.getParentByIndex(0).getFitness());
            counter=0;
        }
        i_TimeTable.getRules().recheckBestSolution(i_EvolutionEngineData.getBestSolution(),i_TimeTable,i_EvolutionEngineData);
        i_EvolutionEngineData.updateRulesAverage();
    }

    public synchronized void resumeAlgo()
    {
        notifyAll();
    }

    private Boolean checkStoppingConditions(Integer m_numOfGenerations, Integer i_GenerationsMade, Integer i_ReqFitness, Integer i_BestFitness,
                                            Long i_ReqMinutesInMillis, Long i_TimePassedInMillis,
                                            Collection<eStoppingCondition> i_StoppingCondition) {
        boolean retVal=false;
        boolean checkRes;
        for(eStoppingCondition eStop:i_StoppingCondition)
        {
            checkRes=eStop.checkStoppingCondition(m_numOfGenerations,i_GenerationsMade,i_ReqFitness,i_BestFitness,i_ReqMinutesInMillis,i_TimePassedInMillis);
            retVal= (retVal || checkRes);
        }
        return retVal;
    }
}
