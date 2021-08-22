package DataTransferClasses;

import AlgorithmClasses.Crossover;

public class CrossoverData {
    private String m_Name;
    private Integer m_NumOfCuttingPoints;
    private Character m_Aspect;

    public CrossoverData(Crossover i_Crossover)
    {
        m_Name=i_Crossover.getName();
        m_NumOfCuttingPoints=i_Crossover.getCuttingPoints();
        m_Aspect=i_Crossover.getChar();
    }


    public String getName() {
        return m_Name;
    }

    public Integer getNumOfCuttingPoints() {
        return m_NumOfCuttingPoints;
    }

    public Character getAspect() {
        return m_Aspect;
    }
}
