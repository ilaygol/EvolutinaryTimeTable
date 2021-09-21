package Manager;

import DataTransferClasses.HomePageTableRowsData;
import DataTransferClasses.WebFileData;

public class InstanceManager {
    private Integer m_HostID;
    private String m_HostName;
    private LogicEngineManager m_Manager;
    private WebFileData m_fileDataSaver;

    public InstanceManager(Integer i_HostID,String i_HostName, LogicEngineManager i_LogicEngineManager)
    {
        m_HostID=i_HostID;
        m_HostName=i_HostName;
        m_Manager=i_LogicEngineManager;
        m_fileDataSaver=m_Manager.getWebFileData();
    }

    public HomePageTableRowsData getRowData()
    {
        HomePageTableRowsData retHomePageTableRowsData =new HomePageTableRowsData(m_HostName, m_Manager.getAmountOfData());
        return retHomePageTableRowsData;
    }

    public Integer getHostID() {
        return m_HostID;
    }

    public LogicEngineManager getManager() {
        return m_Manager;
    }

    public WebFileData getFileDataSaver() {
        return m_fileDataSaver;
    }
}
