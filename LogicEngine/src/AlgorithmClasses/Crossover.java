package AlgorithmClasses;

import ParsedClasses.ETTCrossover;

public class Crossover {

    private String m_Configuration;
    private String m_Name;
    private Integer m_CuttingPoints;

    public Crossover(ETTCrossover i_ETTCrossover)
    {
        m_Configuration=i_ETTCrossover.getConfiguration();
        m_Name=i_ETTCrossover.getName();
        m_CuttingPoints=i_ETTCrossover.getCuttingPoints();
    }
}
