package Users;

import DataTransferClasses.RowData;
import Manager.LogicEngineManager;
import Manager.InstanceManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TimeTableHostManager {
    private List<InstanceManager> m_InstancesList;

    public TimeTableHostManager() {
        m_InstancesList = new ArrayList<>();
    }

    public synchronized void addInstance(InstanceManager i_LogicEngineInstance) {
        m_InstancesList.add(i_LogicEngineInstance);
    }


    public synchronized void addInstance(String i_HostID,String i_HostName, LogicEngineManager i_LogicEngineManager) {
        Integer hostID=Integer.parseInt(i_HostID);

        InstanceManager instance=new InstanceManager(hostID,i_HostName,i_LogicEngineManager);
        m_InstancesList.add(instance);
    }



    public synchronized void removeUser(Integer i_InstanceIndx) {
        m_InstancesList.remove(i_InstanceIndx.intValue());
    }

    public synchronized List<InstanceManager> getAllInstances() {
        return Collections.unmodifiableList(m_InstancesList);
    }


    public List<RowData> getRowDataList(){
        List<RowData> retList=new ArrayList<>();
        for(InstanceManager instance:m_InstancesList)
        {
            retList.add(instance.getRowData());
        }
        return Collections.unmodifiableList(retList);
    }
}
