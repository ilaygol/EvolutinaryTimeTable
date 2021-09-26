package WebManager;

import DataTransferClasses.*;
import Manager.LogicEngineManager;
import Threads.ActivateAlgoThread;
import Users.Solver;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LogicEngineWrapper {
    private ActivateAlgoThread m_Thread;
    private LogicEngineManager m_EngineManager;
    private Boolean m_IsPaused;
    private Solver m_Solver;
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

    public void setSolver(Solver i_Solver)
    {
        m_Solver=i_Solver;
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
    public List<WebLessonData> getWebLessonDataListRaw()
    {
        List<WebLessonData> retList=new ArrayList<>();
        BestSolutionsData bestSolutionsData=m_EngineManager.getBestSolutionData();
        bestSolutionsData.getLessonsDataList().sort(Comparator
                .comparing(LessonData::getDay)
                .thenComparing(LessonData::getHour)
                .thenComparing(LessonData::getClassID)
                .thenComparing(LessonData::getTeacherID));
        for(LessonData lessonData: bestSolutionsData.getLessonsDataList())
        {
            retList.add(m_EngineManager.getWebLessonData(lessonData));
        }
        return  retList;

    }

    public List<DayWebLessonsData> getBestSolutionByTeacherID(Integer i_TeacherID)
    {
        List<DayWebLessonsData> retList=new ArrayList<>();
        DayWebLessonsData dayWebLessonsData=new DayWebLessonsData();



        return retList;
    }

    public List<DayWebLessonsData> getBestSolutionByClassID(Integer i_ClassID)
    {
        List<DayWebLessonsData> retList=new ArrayList<>();
        DayWebLessonsData dayWebLessonsData=new DayWebLessonsData();



        return retList;
    }
    public void startAlgorithm()
    {
        if(m_Thread!=null) {
            if(isPausedAlgo()) {
                resumeAlgorithm();
                m_IsPaused=false;
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
        if(i_ProgressData.getGenerationMade()==1)
        {
            m_ProgressData.setShowEveryGeneration(0);
            m_ProgressData.setShowEveryFitness(0);
        }
        m_ProgressData.setGenerationMade(i_ProgressData.getGenerationMade());
        m_Solver.setGenerationsMade(i_ProgressData.getGenerationMade());
        m_ProgressData.setFitness(i_ProgressData.getFitness());
        m_Solver.setBestFitness(i_ProgressData.getFitness());
        m_ProgressData.setTimePassedInMillis(i_ProgressData.getTimePassedInMillis());
        m_ProgressData.setShowEvery(i_ProgressData.getShowEvery());
        m_ProgressData.setIsPaused(i_ProgressData.getIsPaused());
        m_ProgressData.setIsRunningAlgo(i_ProgressData.getIsRunningAlgo());
        m_ProgressData.setIsAlreadyActivatedAlgo(i_ProgressData.getIsAlreadyActivatedAlgo());

        m_ProgressData.setIsGenerationStopPicked(i_ProgressData.getIsGenerationStopPicked());
        m_ProgressData.setIsFitnessStopPicked(i_ProgressData.getIsFitnessStopPicked());
        m_ProgressData.setIsTimeStopPicked(i_ProgressData.getIsTimeStopPicked());

        m_ProgressData.setReqGeneration(i_ProgressData.getReqGeneration());
        m_ProgressData.setReqFitness(i_ProgressData.getReqFitness());
        m_ProgressData.setReqTimeInMillis(i_ProgressData.getReqTimeInMillis());

        
        if(m_ProgressData.getGenerationMade()%m_ProgressData.getShowEvery()==0) {
            m_ProgressData.setShowEveryGeneration(m_ProgressData.getGenerationMade());
            m_ProgressData.setShowEveryFitness(m_ProgressData.getFitness());
        }

    }
}

