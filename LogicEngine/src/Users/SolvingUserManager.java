package Users;

import DataTransferClasses.SolverData;

import java.util.*;

public class SolvingUserManager {
    private List<Solver> m_SolversList;

    public SolvingUserManager() {
        m_SolversList = new ArrayList<>();
    }

    public synchronized void addSolver(Solver i_Solver) {
        m_SolversList.add(i_Solver);
    }

    public synchronized void removeUser(Solver i_Solver) {
        m_SolversList.remove(i_Solver);
    }

    public synchronized List<Solver> getUsers() {
        return Collections.unmodifiableList(m_SolversList);
    }

    public boolean isUserExists(Solver i_Solver) {
        return m_SolversList.contains(i_Solver);
    }

    public Integer getSolversAmount() { return m_SolversList.size(); }

    public List<SolverData> getSolversDataList()
    {
        List<SolverData> retList=new ArrayList<>();
        m_SolversList.forEach(solver-> retList.add(new SolverData(solver.getSolverName(),solver.getGenerationsMade(),solver.getBestFitness())));
        return retList;
    }

}
