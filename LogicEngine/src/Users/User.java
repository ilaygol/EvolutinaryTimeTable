package Users;

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

}
