package DataClasses.AlgorithmData;

import java.util.Random;

public class Lesson {
    private Integer m_Day;
    private Integer m_Hour;
    private Integer m_ClassID;
    private Integer m_TeacherID;
    private Integer m_SubjectID;

    public Lesson(Integer i_MaxDay,Integer i_MaxHour,
                  Integer i_MaxClassID,Integer i_MaxTeacherID,
                  Integer i_MaxSubjectID)
    {
        Random roller=new Random();
        m_Day= roller.nextInt(i_MaxDay);
        m_Hour= roller.nextInt(i_MaxHour);
        m_ClassID= roller.nextInt(i_MaxClassID);
        m_TeacherID= roller.nextInt(i_MaxTeacherID);
        m_SubjectID= roller.nextInt(i_MaxSubjectID);
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
