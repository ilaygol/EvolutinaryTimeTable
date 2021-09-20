package Users;

import DataTransferClasses.MutationData;
import Manager.LogicEngineManager;

import java.util.ArrayList;
import java.util.List;

public class User {
    private Integer m_ID;
    private String m_Username;
    private List<LogicEngineManager> m_EngineList;

    public User(Integer i_ID, String i_Username) {
        this.m_ID = i_ID;
        this.m_Username = i_Username;
        this.m_EngineList = new ArrayList<>();
    }

    public Integer getID() {
        return m_ID;
    }

    public String getUsername() {
        return m_Username;
    }

    public List<LogicEngineManager> getEngineList() {
        return m_EngineList;
    }

    public void addNewManager(LogicEngineManager i_LogicEngineManager)
    {
        m_EngineList.add(i_LogicEngineManager);
    }

    public boolean isManagerExist(Integer i_ManagerIndex)
    {
        boolean retValue;
        if(m_EngineList.stream().filter(manager-> manager.getProblemIndex().equals(i_ManagerIndex)).count()>0)
            retValue = true;
        else
            retValue = false;
        return retValue;
    }

    public void updateAlgoReferences(Integer i_ManagerIndex,String i_InitialPopulation,String i_ReqGenerations,String i_ReqFitness,String i_ReqTimeInMinutes,
                                     String i_CrossoverName,String i_NumOfCuttingPoints,String i_CrossoverComponent,
                                     String i_SelectionType, String i_Percent, String i_PTE, String i_Elitism)
    {
        LogicEngineManager wantedManager=getManagerByProblemIndex(i_ManagerIndex);
        wantedManager.updateAlgoReference(i_InitialPopulation,i_ReqGenerations,i_ReqFitness,
                i_ReqTimeInMinutes,i_CrossoverName,i_NumOfCuttingPoints,i_CrossoverComponent,i_SelectionType,i_Percent,i_PTE,i_Elitism);
    }
    public void addNewMutationToManager(Integer i_ManagerIndex,String i_Name,String i_Tupples,String i_Char,String i_Probability)
    {
        LogicEngineManager wantedManager=getManagerByProblemIndex(i_ManagerIndex);
        wantedManager.addNewMutationToList(i_Name,i_Tupples,i_Char,i_Probability);
    }

    public MutationData getMutationDataByIndex(Integer i_ManagerIndex,Integer i_MutationIndex)
    {
        LogicEngineManager wantedManager=getManagerByProblemIndex(i_ManagerIndex);
        return wantedManager.getMutationDataByIndex(i_MutationIndex);
    }

    public List<MutationData> getMutationDataListByManagerIndex(Integer i_ManagerIndex)
    {
        LogicEngineManager wantedManager=getManagerByProblemIndex(i_ManagerIndex);
        return wantedManager.getMutationDataList();
    }

    public void deleteMutationByIndex(Integer i_ManagerIndex,Integer i_MutationIndex)
    {
        LogicEngineManager wantedManager=getManagerByProblemIndex(i_ManagerIndex);
        wantedManager.deleteMutationByIndex(i_MutationIndex);

    }

    public void updateMutationByIndex(Integer i_ManagerIndex,Integer i_MutationIndex,String i_Name,String i_Tupples,String i_Char,String i_Probability)
    {
        LogicEngineManager wantedManager=getManagerByProblemIndex(i_ManagerIndex);
        wantedManager.updateMutation(i_MutationIndex,i_Name,i_Tupples,i_Char,i_Probability);
    }


    private LogicEngineManager getManagerByProblemIndex(Integer i_ProblemIndex)
    {
        LogicEngineManager retManager=null;
        for(LogicEngineManager manager:m_EngineList)
        {
            if(manager.getProblemIndex().equals(i_ProblemIndex)) {
                retManager = manager;
                break;
            }
        }
        return retManager;
    }
}
