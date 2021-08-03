
package AlgorithmClasses;

import DataClasses.AlgorithmData.Generation;
import DataTransferClasses.SelectionData;
import ParsedClasses.ETTSelection;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Selection {

    private String m_Type;
    private List<String> m_Configuration;
    private Integer m_Percent;
    private eSelection m_eType;


    public Selection(ETTSelection i_ETTSelection)
    {
        m_Type=i_ETTSelection.getType();
        m_Configuration=i_ETTSelection.getConfiguration();
        extractConfiguration();
        m_eType=eSelection.valueOf(m_Type.toUpperCase(Locale.ROOT));
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

    public Integer getPercent() {
        return m_Percent;
    }

    public Generation activateSelection(Generation i_Generation){
        return m_eType.activate(m_Percent, i_Generation);
    }

    public void extractConfiguration()
    {
        Scanner in = new Scanner(m_Configuration.toString()).useDelimiter("[^0-9]+");
        m_Percent= in.nextInt();
        in.close();
    }
}
