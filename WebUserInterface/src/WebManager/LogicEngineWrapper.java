package WebManager;

import DataTransferClasses.ProgressData;
import Manager.LogicEngineManager;
import Threads.ActivateAlgoThread;

public class LogicEngineWrapper {
    private ActivateAlgoThread m_Thread;
    private LogicEngineManager m_EngineManager;
    private Boolean m_IsPaused;
    private ProgressData m_ProgressData;

    public LogicEngineWrapper(LogicEngineManager i_Manager)
    {
        m_EngineManager=i_Manager;
        m_Thread=null;
        m_IsPaused=false;
    }

    public ActivateAlgoThread getThread() {
        return m_Thread;
    }

    public LogicEngineManager getEngineManager() {
        return m_EngineManager;
    }

    public ProgressData getProgressData() {
        return m_ProgressData;
    }

    public Boolean getIsPausedFlag()
    {
        return m_IsPaused;
    }

    public void setThread(ActivateAlgoThread i_Thread)
    {
        m_Thread=i_Thread;
    }

    public void startAlgorithm()
    {
        if(m_Thread!=null) {
            m_Thread.start();
        }
        else
            throw new RuntimeException("Error: Trying to run null thread");
    }

    public void pauseAlgorithm()
    {
        if(m_Thread!=null) {
            m_Thread.interrupt();
            m_IsPaused=true;
        }
        else
            throw new RuntimeException("Error: trying to pause null Thread");
    }

    public void stopAlgorithm()
    {
        m_EngineManager.setStopBoolean(true);
        if(m_IsPaused) {
            resumeAlgorithm();
            m_IsPaused=false;
        }


    }
    public void resumeAlgorithm()
    {
        m_EngineManager.resumeAlgo();
    }
    //consumer
    public void updateProgress(ProgressData i_ProgressData)
    {
        m_ProgressData.setNewValues(i_ProgressData.getGeneration(), i_ProgressData.getFitness(), i_ProgressData.getTimePassedInMillis());
    }


}
