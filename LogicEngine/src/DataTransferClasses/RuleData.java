package DataTransferClasses;

import DataClasses.FileInputDataClasses.Rule;

public class RuleData {
    private String m_Name;
    private String m_Type;
    private Integer m_Grade;

    public RuleData(Rule i_Rule)
    {
        m_Name=i_Rule.getId().name();
        m_Type=i_Rule.getType().name();
    }

    public String getName() {
        return m_Name;
    }

    public String getType() {
        return m_Type;
    }

    public Integer getGrade() {
        return m_Grade;
    }

    public void setGrade(Integer i_Grade) {
        this.m_Grade = i_Grade;
    }
}
