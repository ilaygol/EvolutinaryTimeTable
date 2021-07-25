
package AlgorithmClasses;

import ParsedClasses.ETTEvolutionEngine;

public class EvolutionEngine {

    private Integer m_InitialPopulation;
    private Selection m_Selection;
    private Crossover m_Crossover;
    private Mutations m_Mutations;

    public EvolutionEngine(ETTEvolutionEngine i_ETTEvolutionEngine)
    {
        m_InitialPopulation=i_ETTEvolutionEngine.getETTInitialPopulation().getSize();
        m_Selection=new Selection(i_ETTEvolutionEngine.getETTSelection());
        m_Crossover=new Crossover(i_ETTEvolutionEngine.getETTCrossover());
        m_Mutations=new Mutations(i_ETTEvolutionEngine.getETTMutations());
    }
}
