package DataTransferClasses;

import AlgorithmClasses.Selection;

import java.util.List;

public class SelectionData {
    private String m_Type;
    private List<String> m_Configuration;

    public SelectionData(Selection i_Selection)
    {
        m_Type=i_Selection.getType();
        m_Configuration=i_Selection.getConfiguration();
    }

    public String getType() {
        return m_Type;
    }

    public List<String> getConfiguration() {
        return m_Configuration;
    }
}
