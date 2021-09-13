package Manager;

public class InstanceManager {
    private Integer m_HostID;
    private LogicEngineManager m_Manager;

    public InstanceManager(Integer i_HostID, LogicEngineManager i_LogicEngineManager)
    {
        m_HostID=i_HostID;
        m_Manager=i_LogicEngineManager;
    }

    public Integer getHostID() {
        return m_HostID;
    }

    public LogicEngineManager getManager() {
        return m_Manager;
    }


}
