package AlgorithmClasses;

import DataClasses.AlgorithmData.Generation;
import DataClasses.AlgorithmData.Parent;
import DataTransferClasses.CrossoverData;
import ParsedClasses.ETTCrossover;

import java.util.Locale;

public class Crossover {

    private String m_Name;
    private Integer m_CuttingPoints;
    private eCrossover m_Configuration;
    private Generation m_NewGeneration;

    public Crossover(ETTCrossover i_ETTCrossover)
    {
        m_Name=i_ETTCrossover.getName();
        m_CuttingPoints=i_ETTCrossover.getCuttingPoints();
        m_Configuration=eCrossover.valueOf(m_Name.toUpperCase(Locale.ROOT));
        m_NewGeneration=new Generation();
    }

    public CrossoverData getCrossoverData()
    {
        return new CrossoverData(this);
    }

    public eCrossover getConfiguration() {
        return m_Configuration;
    }

    public String getName() {
        return m_Name;
    }

    public Integer getCuttingPoints() {
        return m_CuttingPoints;
    }

    public void addToNewGeneration(Parent i_Parent)
    {
        m_NewGeneration.addSolutionToList(i_Parent);
    }
}
