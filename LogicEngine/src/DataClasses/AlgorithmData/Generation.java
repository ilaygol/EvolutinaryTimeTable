package DataClasses.AlgorithmData;

import java.util.ArrayList;
import java.util.List;

public class Generation {
    List<Parent> m_ParentsList;

    public Generation()
    {
        m_ParentsList =new ArrayList<>();
    }

    public List<Parent> getParentsList() {
        return m_ParentsList;
    }
    public void addSolutionToList(Parent i_Solution)
    {
        m_ParentsList.add(i_Solution);
    }
    public Integer getGenerationSize() { return m_ParentsList.size(); }
    public Parent getParentByIndex(Integer index) { return m_ParentsList.get(index); }


    //**
    @Override
    public String toString() {
        return "Generation{"
                + m_ParentsList +
                '}';
    }
}
