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
        m_ProgressData=new ProgressData(10);
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


    public void setThread(ActivateAlgoThread i_Thread)
    {
        m_Thread=i_Thread;
    }

    public boolean isThreadExist()
    {
        boolean retVal;
        retVal=m_Thread != null;
        return retVal;
    }

    public Boolean isPausedAlgo()
    {
        return m_IsPaused;
    }

    public void createAndSetThread(Integer i_ShowEvery)
    {
        ActivateAlgoThread thread=new ActivateAlgoThread(m_EngineManager,this::updateProgress,i_ShowEvery);
        setThread(thread);
    }

    public void startAlgorithm()
    {
        if(m_Thread!=null) {
            if(isPausedAlgo()) {
                resumeAlgorithm();
            }
            else{
                m_Thread.start();
            }
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
        if(i_ProgressData.getGeneration()==1)
        {
            m_ProgressData.setShowEveryGeneration(0);
            m_ProgressData.setShowEveryFitness(0);
        }
        m_ProgressData.setGeneration(i_ProgressData.getGeneration());
        m_ProgressData.setFitness(i_ProgressData.getFitness());
        m_ProgressData.setTimePassedInMillis(i_ProgressData.getTimePassedInMillis());
        m_ProgressData.setShowEvery(i_ProgressData.getShowEvery());
        m_ProgressData.setIsPaused(i_ProgressData.getIsPaused());
        m_ProgressData.setIsRunningAlgo(i_ProgressData.getIsRunningAlgo());
        m_ProgressData.setIsAlreadyActivatedAlgo(i_ProgressData.getIsAlreadyActivatedAlgo());

        if(m_ProgressData.getGeneration()%m_ProgressData.getShowEvery()==0) {
            m_ProgressData.setShowEveryGeneration(m_ProgressData.getGeneration());
            m_ProgressData.setShowEveryFitness(m_ProgressData.getFitness());
        }
    }
}

