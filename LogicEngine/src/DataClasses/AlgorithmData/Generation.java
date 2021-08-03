package DataClasses.AlgorithmData;

import java.util.*;

public class Generation{
    List<Parent> m_ParentsList;

    public Generation()
    {
        m_ParentsList =new ArrayList<>();
    }

    public List<Parent> getParentsList() {
        return m_ParentsList;
    }
    public void addParentToGeneration(Parent i_Parent)
    {
        m_ParentsList.add(i_Parent);
    }
    public Integer getGenerationSize() { return m_ParentsList.size(); }
    public Parent getParentByIndex(Integer index) { return m_ParentsList.get(index); }
    public void sortGenerationByFitness()
    {
        Collections.sort(m_ParentsList, new Comparator<Parent>()
        {public int compare(Parent p1, Parent p2) { return p1.getFitness() - p2.getFitness(); }});
    }
    //**
    @Override
    public String toString() {
        return "Generation{"
                + m_ParentsList +
                '}';
    }

}
