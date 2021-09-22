package WebManager;

import Manager.LogicEngineManager;
import Threads.ActivateAlgoThread;

public class LogicEngineWrapper {
    private ActivateAlgoThread m_Thread;
    private LogicEngineManager m_EngineManager;

    public LogicEngineWrapper(LogicEngineManager i_Manager)
    {
        m_EngineManager=i_Manager;
        m_Thread=null;
    }

    public ActivateAlgoThread getThread() {
        return m_Thread;
    }

    public LogicEngineManager getEngineManager() {
        return m_EngineManager;
    }

}
