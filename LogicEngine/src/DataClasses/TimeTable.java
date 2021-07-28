
package DataClasses;

import ParsedClasses.ETTTimeTable;

public class TimeTable {

    private Teachers m_Teachers;
    private Subjects m_Subjects;
    private Clazzes m_Clazzes;
    private Rules m_Rules;
    private Integer m_Hours;
    private Integer m_Days;


    public TimeTable(ETTTimeTable i_ETTTimeTable)
    {
        m_Teachers=new Teachers(i_ETTTimeTable.getETTTeachers());
        m_Subjects=new Subjects(i_ETTTimeTable.getETTSubjects());
        m_Clazzes=new Clazzes(i_ETTTimeTable.getETTClasses());
        m_Rules=new Rules(i_ETTTimeTable.getETTRules());
        m_Hours=i_ETTTimeTable.getHours();
        m_Days=i_ETTTimeTable.getDays();
    }

    @Override
    public String toString() {
        return "TimeTable{" +
                "m_Teachers=" + m_Teachers +
                ", m_Subjects=" + m_Subjects +
                ", m_Clazzes=" + m_Clazzes +
                ", m_Rules=" + m_Rules +
                ", m_Hours=" + m_Hours +
                ", m_Days=" + m_Days +
                '}';
    }

    public Teachers getTeachers() {
        return m_Teachers;
    }

    public Subjects getSubjects() {
        return m_Subjects;
    }

    public Clazzes getClazzes() {
        return m_Clazzes;
    }

    public Rules getRules() {
        return m_Rules;
    }

    public Integer getHours() {
        return m_Hours;
    }

    public Integer getDays() {
        return m_Days;
    }
}
