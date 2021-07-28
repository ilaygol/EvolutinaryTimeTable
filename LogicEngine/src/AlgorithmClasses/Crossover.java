package AlgorithmClasses;

import DataTransferClasses.CrossoverData;
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

    public CrossoverData getCrossoverData()
    {
        return new CrossoverData(this);
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
