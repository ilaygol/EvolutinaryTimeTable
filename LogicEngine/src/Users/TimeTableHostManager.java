package Users;

import Manager.LogicEngineManager;
import Manager.InstanceManager;

import java.util.ArrayList;
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


    public synchronized void addInstance(String i_HostID, LogicEngineManager i_LogicEngineManager) {
        Integer hostID=Integer.parseInt(i_HostID);
        InstanceManager instance=new InstanceManager(hostID,i_LogicEngineManager);
        m_InstancesList.add(instance);
    }



    public synchronized void removeUser(Integer i_InstanceIndx) {
        m_InstancesList.remove(i_InstanceIndx.intValue());
    }

    public synchronized List<InstanceManager> getAllInstances() {
        return Collections.unmodifiableList(m_InstancesList);
    }


    public List<InstanceManager> getInstanceByHostID(String i_ID){
        Integer userID=Integer.parseInt(i_ID);
        return m_InstancesList.stream().filter(instance->instance.getHostID()==userID).collect(Collectors.toList());
    }
}
