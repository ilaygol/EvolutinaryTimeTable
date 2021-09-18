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

    public void addNewMutationToManager(Integer i_ManagerIndex,String i_Name,String i_Tupples,String i_Char,String i_Probability)
    {
        LogicEngineManager wantedManager=null;
        for(LogicEngineManager manager:m_EngineList)
        {
            if(manager.getProblemIndex().equals(i_ManagerIndex)) {
                wantedManager = manager;
                break;
            }
        }
        wantedManager.addNewMutationToList(i_Name,i_Tupples,i_Char,i_Probability);
    }

    public List<MutationData> getMutationDataListByManagerIndex(Integer i_ManagerIndex)
    {
        LogicEngineManager wantedManager=null;
        for(LogicEngineManager manager:m_EngineList)
        {
            if(manager.getProblemIndex().equals(i_ManagerIndex)) {
                wantedManager = manager;
                break;
            }
        }
        return wantedManager.getMutationDataList();
    }

}
