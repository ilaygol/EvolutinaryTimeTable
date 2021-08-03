
package AlgorithmClasses;

import DataClasses.AlgorithmData.AmountOfObjectsCalc;
import DataClasses.AlgorithmData.Parent;
import DataClasses.AlgorithmData.Generation;
import DataClasses.FileInputDataClasses.TimeTable;
import ParsedClasses.ETTEvolutionEngine;

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

    public void activateAlgorithm(TimeTable i_TimeTable,AmountOfObjectsCalc i_AmountOfObj)
    {
        Integer remainingGenerations=m_NumOfGenerations;
        Integer counter=0;
        initialSolutions(i_AmountOfObj);
        while(remainingGenerations>0) {
            while(counter< m_PrintingReq && counter < remainingGenerations) {
                //elay function receives i_TimeTable
                //selection
                m_Crossover.createNewGeneration(m_Generation,i_AmountOfObj);
                //mutation
                counter++;
                System.out.println("Done making "+counter+" generations");
                //checking best solution if need update
                m_Generation=m_Crossover.getNewGeneration();

            }
            //update map
            //event elay gol
            remainingGenerations-=counter;
            counter=0;

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
