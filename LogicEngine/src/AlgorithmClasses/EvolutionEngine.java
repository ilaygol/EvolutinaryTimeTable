
package AlgorithmClasses;

import DataClasses.AlgorithmData.AmountOfObjectsCalc;
import DataClasses.AlgorithmData.Parent;
import DataClasses.AlgorithmData.Generation;
import DataClasses.FileInputDataClasses.TimeTable;
import DataTransferClasses.EvolutionEngineData;
import DataTransferClasses.ProgressData;
import ParsedClasses.ETTEvolutionEngine;
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
    private Integer m_PrintingReq;
    private Integer m_ReqFitness;
    private Long m_ReqMinutesInMillis;
    private Selection m_Selection;
    private Crossover m_Crossover;
    private Mutations m_Mutations;
    private Generation m_Generation;

    public EvolutionEngine(ETTEvolutionEngine i_ETTEvolutionEngine)
    {
        m_InitialPopulationAmount =i_ETTEvolutionEngine.getETTInitialPopulation().getSize();
        m_Selection=new Selection(i_ETTEvolutionEngine.getETTSelection());
        m_Crossover=new Crossover(i_ETTEvolutionEngine.getETTCrossover());
        m_Mutations=new Mutations(i_ETTEvolutionEngine.getETTMutations());
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

    public void setNumOfGenerations(Integer i_NumOfGenerations) {
        m_NumOfGenerations = i_NumOfGenerations;
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

    public EvolutionEngineData activateAlgorithm(TimeTable i_TimeTable, AmountOfObjectsCalc i_AmountOfObj, Consumer<ProgressData> i_ProgressDataConsumer, Collection<eStoppingCondition> i_StoppingConditions)
    {
        EvolutionEngineData dataSaver=new EvolutionEngineData();
        ProgressData progressTracker=new ProgressData(0, 0, (long)0);
        Integer counter=0,generationsMade=0,bestFitness=0;
        Long timePassedInMillis=(long)0;
        Instant startCountingTime,endCountingTime;
        initialSolutions(i_AmountOfObj);
        i_TimeTable.getRules().calculateFitnesses(m_Generation,i_TimeTable);
        m_Generation.sortGenerationByFitness();
        Boolean stopAlgo=false;

        while(!stopAlgo) {
            while(counter< m_PrintingReq && !stopAlgo) {
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
                    dataSaver.setBestSolution(m_Generation.getParentByIndex(0));
                    bestFitness= dataSaver.getBestSolutionFitness();
                }
                else if(dataSaver.getBestSolutionFitness()<m_Generation.getParentByIndex(0).getFitness()) {
                    dataSaver.setBestSolution(m_Generation.getParentByIndex(0));
                    bestFitness= dataSaver.getBestSolutionFitness();
                }
                endCountingTime=Instant.now();
                timePassedInMillis+= Duration.between(startCountingTime,endCountingTime).toMillis();
                i_ProgressDataConsumer.accept(progressTracker);
                stopAlgo=checkStoppingConditions(m_NumOfGenerations,generationsMade,m_ReqFitness,bestFitness,m_ReqMinutesInMillis,timePassedInMillis,i_StoppingConditions);

            }
            dataSaver.addToGeneration2BestFitnessMap(generationsMade,m_Generation.getParentByIndex(0).getFitness());
            progressTracker.setNewValues(generationsMade,bestFitness,timePassedInMillis);
            counter=0;
        }
        i_TimeTable.getRules().recheckBestSolution(dataSaver.getBestSolution(),i_TimeTable,dataSaver);
        dataSaver.updateRulesAverage();
        return dataSaver;
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
