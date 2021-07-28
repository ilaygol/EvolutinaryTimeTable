
package AlgorithmClasses;

import DataTransferClasses.SelectionData;
import ParsedClasses.ETTSelection;

import java.util.ArrayList;
import java.util.List;

public class Selection {

    private String m_Type;
    private List<String> m_Configuration;

    public Selection(ETTSelection i_ETTSelection)
    {
        m_Type=i_ETTSelection.getType();
        m_Configuration=i_ETTSelection.getConfiguration();
    }

    public String getType() {
        return m_Type;
    }

    public List<String> getConfiguration() {
        return m_Configuration;
    }

    public SelectionData getSelectionData()
    {
        return new SelectionData(this);
    }
}
