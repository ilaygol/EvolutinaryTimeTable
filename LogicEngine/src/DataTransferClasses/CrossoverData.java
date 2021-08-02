package DataTransferClasses;

import AlgorithmClasses.Crossover;

public class CrossoverData {
    private String m_Name;
    private Integer m_CuttingPoints;

    public CrossoverData(Crossover i_Crossover)
    {
        m_Name=i_Crossover.getName();
        m_CuttingPoints=i_Crossover.getCuttingPoints();
    }


    public String getName() {
        return m_Name;
    }

    public Integer getCuttingPoints() {
        return m_CuttingPoints;
    }
}
