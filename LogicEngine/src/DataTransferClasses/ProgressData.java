package DataTransferClasses;

public class ProgressData {
    private Integer m_Generation;
    private Integer m_Fitness;
    private Long m_TimePassedInMillis;

    public ProgressData(Integer i_Generation,Integer i_Fitness,Long i_TimePassedInMillis)
    {
        m_Generation=i_Generation;
        m_Fitness=i_Fitness;
        m_TimePassedInMillis=i_TimePassedInMillis;
    }

    public Integer getGeneration() { return m_Generation; }

    public Integer getFitness() { return m_Fitness; }

    public Long getTimePassedInMillis() { return m_TimePassedInMillis; }

    public void setGeneration(Integer i_Generation) { m_Generation = i_Generation; }
    public void setFitness(Integer i_Fitness) { m_Fitness = i_Fitness; }
    public void setTimePassedInMillis(Long i_TimePassedInMillis) { m_TimePassedInMillis = i_TimePassedInMillis; }

    public void setNewValues(Integer i_Generaion,Integer i_Fitness,Long i_TimePassedInMillis)
    {
        m_Generation=i_Generaion;
        m_Fitness=i_Fitness;
        m_TimePassedInMillis=i_TimePassedInMillis;
    }
}
