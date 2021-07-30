package DataClasses.AlgorithmData;

import java.util.ArrayList;
import java.util.List;

public class ProblemSolutions {
    List<Parent> m_ParentsList;

    public ProblemSolutions()
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

}
