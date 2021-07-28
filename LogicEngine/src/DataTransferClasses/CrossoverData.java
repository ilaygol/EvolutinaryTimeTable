package DataTransferClasses;

import AlgorithmClasses.Crossover;

public class CrossoverData {
    private String m_Configuration;
    private String m_Name;
    private Integer m_CuttingPoints;

    public CrossoverData(Crossover i_Crossover)
    {
        m_Name=i_Crossover.getName();
        m_Configuration=i_Crossover.getConfiguration();
        m_CuttingPoints=i_Crossover.getCuttingPoints();
    }

    public String getConfiguration() {
        return m_Configuration;
    }

    public String getName() {
        return m_Name;
    }

    public Integer getCuttingPoints() {
        return m_CuttingPoints;
    }
}
