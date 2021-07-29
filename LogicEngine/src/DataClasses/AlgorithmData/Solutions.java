package DataClasses.AlgorithmData;

import java.util.ArrayList;
import java.util.List;

public class Solutions {
    List<Solution> m_SolutionList;

    public Solutions()
    {
        m_SolutionList=new ArrayList<>();
    }

    public List<Solution> getSolutionList() {
        return m_SolutionList;
    }
    public void addSolutionToList(Solution i_Solution)
    {
        m_SolutionList.add(i_Solution);
    }
}
