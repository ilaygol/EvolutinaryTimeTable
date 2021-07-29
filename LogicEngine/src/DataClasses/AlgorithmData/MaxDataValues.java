package DataClasses.AlgorithmData;

public class MaxDataValues {
    private Integer m_MaxDay;
    private Integer m_MaxHour;
    private Integer m_MaxTeacherID;
    private Integer m_MaxClassID;
    private Integer m_MaxSubjectID;
    private Integer m_LessonsInSolution;

    public MaxDataValues(Integer i_MaxDay,Integer i_MaxHour,Integer i_MaxTeacherID,
                         Integer i_MaxClassID,Integer i_MaxSubjectID,
                         Integer i_LessonsInSolution)
    {
        m_MaxDay=i_MaxDay;
        m_MaxHour=i_MaxHour;
        m_MaxTeacherID=i_MaxTeacherID;
        m_MaxSubjectID=i_MaxSubjectID;
        m_MaxClassID=i_MaxClassID;
        m_LessonsInSolution=i_LessonsInSolution;
    }

    public Integer getMaxDay() {
        return m_MaxDay;
    }

    public Integer getMaxHour() {
        return m_MaxHour;
    }

    public Integer getMaxTeacherID() {
        return m_MaxTeacherID;
    }

    public Integer getMaxClassID() {
        return m_MaxClassID;
    }

    public Integer getMaxSubjectID() {
        return m_MaxSubjectID;
    }

    public Integer getLessonsInSolution() {
        return m_LessonsInSolution;
    }

    public Integer getMaxAmountOfLessons()
    {
        return (m_MaxDay * m_MaxHour * m_MaxClassID * m_MaxSubjectID * m_MaxTeacherID);
    }
}
