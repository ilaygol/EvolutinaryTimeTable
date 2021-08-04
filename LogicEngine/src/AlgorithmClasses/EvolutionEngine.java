
package AlgorithmClasses;

import DataClasses.AlgorithmData.AmountOfObjectsCalc;
import DataClasses.AlgorithmData.Parent;
import DataClasses.AlgorithmData.Generation;
import DataClasses.FileInputDataClasses.TimeTable;
import ParsedClasses.ETTEvolutionEngine;

public class EvolutionEngine {
    private Integer m_InitialPopulation;
    private Selection m_Selection;
    private Crossover m_Crossover;
    private Mutations m_Mutations;
    private Generation m_Generation;

    public EvolutionEngine(ETTEvolutionEngine i_ETTEvolutionEngine)
    {
        m_InitialPopulation=i_ETTEvolutionEngine.getETTInitialPopulation().getSize();
        m_Selection=new Selection(i_ETTEvolutionEngine.getETTSelection());
        m_Crossover=new Crossover(i_ETTEvolutionEngine.getETTCrossover());
        m_Mutations=new Mutations(i_ETTEvolutionEngine.getETTMutations());
    }

    public void initialSolutions(AmountOfObjectsCalc i_AmountOfObj)
    {
        m_Generation =new Generation();
        for(int i=1;i<=m_InitialPopulation;i++)
        {
            Parent timeTableSolution=new Parent(i_AmountOfObj.getMaxAmountOfLessons());
            timeTableSolution.buildSolution(i_AmountOfObj);
            m_Generation.addSolutionToList(timeTableSolution);
        }

    }

    public Integer getInitialPopulation() {
        return m_InitialPopulation;
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
}
