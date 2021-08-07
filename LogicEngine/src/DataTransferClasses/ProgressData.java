package DataTransferClasses;

public class ProgressData {
    private Integer m_Generation;
    private Integer m_Fitness;

    public ProgressData(Integer i_Generation,Integer i_Fitness)
    {
        m_Generation=i_Generation;
        m_Fitness=i_Fitness;
    }

    public Integer getGeneration() {
        return m_Generation;
    }

    public Integer getFitness() {
        return m_Fitness;
    }
}
