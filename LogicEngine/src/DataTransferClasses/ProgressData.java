package DataTransferClasses;

public class ProgressData {
    private Integer m_GenerationMade;
    private Integer m_Fitness;
    private Long m_TimePassedInMillis;
    private Integer m_ShowEveryGeneration;
    private Integer m_ShowEveryFitness;
    private Integer m_ShowEvery;
    private Boolean m_isPaused;
    private Boolean m_IsRunningAlgo;
    private Boolean m_AlreadyActivatedAlgo;

    public ProgressData(Integer i_ShowEvery)
    {
        m_GenerationMade =0;
        m_Fitness=0;
        m_TimePassedInMillis=(long)0;
        m_ShowEveryGeneration=0;
        m_ShowEveryFitness=0;
        m_ShowEvery=i_ShowEvery;
        m_IsRunningAlgo=false;
        m_isPaused=false;
        m_AlreadyActivatedAlgo=false;
    }

    public Integer getGenerationMade() { return m_GenerationMade; }
    public Integer getFitness() { return m_Fitness; }
    public Integer getShowEvery(){return m_ShowEvery;}
    public Integer getShowEveryGeneration() {
        return m_ShowEveryGeneration;
    }
    public Integer getShowEveryFitness() {
        return m_ShowEveryFitness;
    }
    public Long getTimePassedInMillis() { return m_TimePassedInMillis; }
    public Boolean getIsPaused() {
        return m_isPaused;
    }
    public Boolean getIsRunningAlgo() {
        return m_IsRunningAlgo;
    }
    public Boolean getIsAlreadyActivatedAlgo(){return m_AlreadyActivatedAlgo;}

    public void setGenerationMade(Integer i_Generation) { m_GenerationMade = i_Generation; }
    public void setFitness(Integer i_Fitness) { m_Fitness = i_Fitness; }
    public void setTimePassedInMillis(Long i_TimePassedInMillis) { m_TimePassedInMillis = i_TimePassedInMillis; }
    public void setShowEvery(Integer i_ShowEvery)
    {
        m_ShowEvery=i_ShowEvery;
    }

    public void setShowEveryGeneration(Integer i_ShowEveryGeneration) { this.m_ShowEveryGeneration = i_ShowEveryGeneration; }
    public void setIsPaused(Boolean i_isPaused) {
        this.m_isPaused = i_isPaused;
    }
    public void setIsRunningAlgo(Boolean i_IsRunningAlgo) {
        this.m_IsRunningAlgo = i_IsRunningAlgo;
    }
    public void setIsAlreadyActivatedAlgo(Boolean i_AlreadyActivatedAlgo) { this.m_AlreadyActivatedAlgo = i_AlreadyActivatedAlgo; }
    public void setShowEveryFitness(Integer i_ShowEveryFitness) {
        this.m_ShowEveryFitness = i_ShowEveryFitness;
    }

    public void setNewValues(Integer i_Generaion, Integer i_Fitness, Long i_TimePassedInMillis)
    {
        m_GenerationMade =i_Generaion;
        m_Fitness=i_Fitness;
        m_TimePassedInMillis=i_TimePassedInMillis;
    }
}
