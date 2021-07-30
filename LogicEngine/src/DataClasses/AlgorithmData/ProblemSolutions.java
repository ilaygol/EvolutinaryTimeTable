package DataClasses.AlgorithmData;

import java.util.ArrayList;
import java.util.List;

public class ProblemSolutions {
    List<TimeTableSolution> m_SolutionList;

    public ProblemSolutions()
    {
        m_SolutionList=new ArrayList<>();
    }

    public List<TimeTableSolution> getSolutionList() {
        return m_SolutionList;
    }
    public void addSolutionToList(TimeTableSolution i_Solution)
    {
        m_SolutionList.add(i_Solution);
    }

}
