
package AlgorithmClasses;

import DataClasses.AlgorithmData.AmountOfObjectsCalc;
import DataClasses.AlgorithmData.Parent;
import DataClasses.AlgorithmData.Generation;
import DataClasses.FileInputDataClasses.TimeTable;
import DataTransferClasses.EvolutionEngineData;
import DataTransferClasses.ProgressData;
import ParsedClasses.ETTEvolutionEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class EvolutionEngine {
    private Integer m_InitialPopulationAmount;
    private Integer m_NumOfGenerations;
    private Integer m_PrintingReq;
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

    public EvolutionEngineData activateAlgorithm(TimeTable i_TimeTable, AmountOfObjectsCalc i_AmountOfObj, Consumer<ProgressData> i_ProgressDataConsumer)
    {
        EvolutionEngineData dataSaver=new EvolutionEngineData();
        Integer remainingGenerations=m_NumOfGenerations;
        Integer counter=0,totalCounter=0;
        Integer mutationToActivateIndex;
        initialSolutions(i_AmountOfObj);

        /*
        i_TimeTable.getRules().calculateFitnesses(m_Generation,i_TimeTable);
        for(Parent p:m_Generation.getParentsList())
        {
            System.out.println("Parent "+counter+" with fitness "+p.getFitness());
            counter++;
        }
         */


        while(remainingGenerations>0) {
            while(counter< m_PrintingReq && counter < remainingGenerations) {
                i_TimeTable.getRules().calculateFitnesses(m_Generation,i_TimeTable);
                m_Generation.sortGenerationByFitness();

                //activating selection
                m_Generation=m_Selection.activateSelection(m_Generation);

                //System.out.println("Generation "+totalCounter+" Fitnesses");
                //for(Parent p:m_Generation.getParentsList())
                   // System.out.print(p.getFitness()+" ");
                //System.out.println(System.lineSeparator());


                //activating crossover
                m_Crossover.createNewParents(m_Generation,i_AmountOfObj,m_InitialPopulationAmount);

                //activating mutation
                mutationToActivateIndex=m_Mutations.getWhichMutationToActivateByIndex();
                m_Mutations.getMutationByIndex(mutationToActivateIndex).activateMutation(m_Generation,i_AmountOfObj);

                counter++;
                totalCounter++;
                if(counter==1)
                    dataSaver.setBestSolution(m_Generation.getParentByIndex(0));
                else if(dataSaver.getBestSolutionFitness()<m_Generation.getParentByIndex(0).getFitness())
                    dataSaver.setBestSolution(m_Generation.getParentByIndex(0));
            }
            i_TimeTable.getRules().calculateFitnesses(m_Generation,i_TimeTable);
            m_Generation.sortGenerationByFitness();

            //System.out.println("Done making "+counter+" generations, in total "+totalCounter+" generations, map Updated");
            dataSaver.addToGeneration2BestFitnessMap(totalCounter,m_Generation.getParentByIndex(0).getFitness());

            //event Ilay Gol
            i_ProgressDataConsumer.accept(new ProgressData(totalCounter,m_Generation.getParentByIndex(0).getFitness()));

            remainingGenerations-=counter;
            counter=0;

        }
        i_TimeTable.getRules().recheckBestSolution(dataSaver.getBestSolution(),i_TimeTable,dataSaver);
        dataSaver.updateRulesAverage();
        return dataSaver;
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

    public void setNumOfGenerations(Integer i_NumOfGenerations) {
        m_NumOfGenerations = i_NumOfGenerations;
    }

    public void setPrintingReq(Integer i_PrintingReq) {
        m_PrintingReq = i_PrintingReq;
    }

}
