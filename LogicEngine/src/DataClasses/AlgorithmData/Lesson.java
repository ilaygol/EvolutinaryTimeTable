package DataClasses.AlgorithmData;


public class Lesson {
    private Integer m_Day;
    private Integer m_Hour;
    private Integer m_ClassID;
    private Integer m_TeacherID;
    private Integer m_SubjectID;

    public Lesson(Integer i_Day,Integer i_Hour,
                  Integer i_ClassID,Integer i_TeacherID,
                  Integer i_SubjectID)
    {
        m_Day= i_Day;
        m_Hour=i_Hour;
        m_ClassID= i_ClassID;
        m_TeacherID= i_TeacherID;
        m_SubjectID= i_SubjectID;
    }


    public Integer getDay() {
        return m_Day;
    }

    public void setDay(Integer i_Day) {
        this.m_Day = i_Day;
    }

    public Integer getHour() {
        return m_Hour;
    }

    public void setHour(Integer i_Hour) {
        this.m_Hour = i_Hour;
    }

    public Integer getClassID() {
        return m_ClassID;
    }

    public void setClassID(Integer i_ClassID) {
        this.m_ClassID = i_ClassID;
    }

    public Integer getTeacherID() {
        return m_TeacherID;
    }

    public void setTeacherID(Integer i_TeacherID) {
        this.m_TeacherID = i_TeacherID;
    }

    public Integer getSubjectID() {
        return m_SubjectID;
    }

    public void setSubjectID(Integer i_SubjectID) {
        this.m_SubjectID = i_SubjectID;
    }


}
