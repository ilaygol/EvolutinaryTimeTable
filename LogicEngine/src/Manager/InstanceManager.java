package Manager;

import DataTransferClasses.RowData;

public class InstanceManager {
    private Integer m_HostID;
    private String m_HostName;
    private LogicEngineManager m_Manager;

    public InstanceManager(Integer i_HostID,String i_HostName, LogicEngineManager i_LogicEngineManager)
    {
        m_HostID=i_HostID;
        m_HostName=i_HostName;
        m_Manager=i_LogicEngineManager;
    }

    public RowData getRowData()
    {
        RowData retRowData=new RowData(m_HostName, m_Manager.getAmountOfData());
        return retRowData;
    }

    public Integer getHostID() {
        return m_HostID;
    }

    public LogicEngineManager getManager() {
        return m_Manager;
    }


}
