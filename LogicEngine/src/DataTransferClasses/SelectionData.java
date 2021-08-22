package DataTransferClasses;

import AlgorithmClasses.Selection;

import java.util.List;

public class SelectionData {
    private String m_Type;
    private Integer m_Percent;
    private Integer m_Elitism;

    public SelectionData(Selection i_Selection)
    {
        m_Type=i_Selection.getType();
        m_Percent=i_Selection.getPercent();
        m_Elitism=i_Selection.getElitism();
    }

    public String getType() {
        return m_Type;
    }

    public Integer getPercent() {
        return m_Percent;
    }

    public Integer getElitism() {
        return m_Elitism;
    }
}
