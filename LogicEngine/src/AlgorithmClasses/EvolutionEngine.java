
package AlgorithmClasses;

import DataClasses.AlgorithmData.AmountOfObjectsCalc;
import ParsedClasses.ETTEvolutionEngine;

public class EvolutionEngine {

    private Integer m_InitialPopulation;
    private Selection m_Selection;
    private Crossover m_Crossover;
    private Mutations m_Mutations;
    //private Solutions<T> m_Solutions;

    public EvolutionEngine(ETTEvolutionEngine i_ETTEvolutionEngine)
    {
        m_InitialPopulation=i_ETTEvolutionEngine.getETTInitialPopulation().getSize();
        m_Selection=new Selection(i_ETTEvolutionEngine.getETTSelection());
        m_Crossover=new Crossover(i_ETTEvolutionEngine.getETTCrossover());
        m_Mutations=new Mutations(i_ETTEvolutionEngine.getETTMutations());
    }

    public void initSolutions(AmountOfObjectsCalc i_AmountOfObj)
    {
        System.out.print(i_AmountOfObj);
        System.out.println(" max lessons available="+ i_AmountOfObj.getMaxAmountOfLessons());
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
