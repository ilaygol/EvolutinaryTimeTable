package DataTransferClasses;

import DataClasses.AlgorithmData.Parent;

import java.util.HashMap;
import java.util.Map;

public class EvolutionEngineData {
    private Map<Integer,Integer> m_Generation2BestFitnessMap;
    private Parent m_BestSolution;

    public EvolutionEngineData(){
        m_Generation2BestFitnessMap=new HashMap<>();
    }

    public Map<Integer, Integer> getGeneration2BestFitnessMap() {
        return m_Generation2BestFitnessMap;
    }

    public Parent getBestSolution() {
        return m_BestSolution;
    }

    public Integer getBestSolutionFitness()
    {
        return m_BestSolution.getFitness();
    }

    public void setBestSolution(Parent i_BestSolution) {
        m_BestSolution = i_BestSolution;
    }

    public void addToGeneration2BestFitnessMap(Integer i_GenerationCount, Integer i_Fitness)
    {
        m_Generation2BestFitnessMap.put(i_GenerationCount,i_Fitness);
    }


}
