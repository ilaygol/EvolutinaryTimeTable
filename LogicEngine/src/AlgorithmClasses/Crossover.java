package AlgorithmClasses;

import DataClasses.AlgorithmData.AmountOfObjectsCalc;
import DataClasses.AlgorithmData.Generation;
import DataClasses.AlgorithmData.Parent;
import DataTransferClasses.CrossoverData;
import ParsedClasses.ETTCrossover;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class Crossover {

    private String m_Name;
    private Integer m_NumOfCuttingPoints;
    private eCrossover m_Configuration;
    private Generation m_NewGeneration;
    private Random m_Roller;


    public Crossover(ETTCrossover i_ETTCrossover)
    {
        m_Name=i_ETTCrossover.getName();
        m_NumOfCuttingPoints =i_ETTCrossover.getCuttingPoints();
        m_Configuration=eCrossover.valueOf(m_Name.toUpperCase(Locale.ROOT));
        m_Roller=new Random();
    }

    public CrossoverData getCrossoverData()
    {
        return new CrossoverData(this);
    }

    public eCrossover getConfiguration() {
        return m_Configuration;
    }

    public String getName() {
        return m_Name;
    }

    public Integer getCuttingPoints() {
        return m_NumOfCuttingPoints;
    }

    public Generation getNewGeneration() { return m_NewGeneration; }

    public void createNewGeneration(Generation i_OldGeneration, AmountOfObjectsCalc i_AmountOfObj){
        Integer generationSize=i_OldGeneration.getGenerationSize();

        ////rolling the cutting points
        List<Integer> cuttingPoints=rollCuttingPoints(i_AmountOfObj.getMaxAmountOfLessons());
        m_NewGeneration=new Generation();
        for(int i=0;i<generationSize;i+=2)
            {
                //checking out of range (not always we have pairs)
                if((i+1)==generationSize)
                    i--;
                m_Configuration.activate(i_OldGeneration.getParentByIndex(i),i_OldGeneration.getParentByIndex(i+1)
                        ,i_AmountOfObj,cuttingPoints,m_NewGeneration);
            }
    }
    public void addParentToNewGeneration(Parent i_Parent)
    {
        m_NewGeneration.addSolutionToList(i_Parent);
    }

    private List<Integer> rollCuttingPoints(Integer i_Max)
    {
        List<Integer> retList=new ArrayList<>(m_NumOfCuttingPoints);
        int index;
        for(int i = 1; i<= m_NumOfCuttingPoints; i++) {
            do {
                index = m_Roller.nextInt(i_Max - 1) + 1; //1 -> (maxAmount-1)
            } while (retList.contains(index));
            retList.add(index);
        }
        return retList;
    }

}
