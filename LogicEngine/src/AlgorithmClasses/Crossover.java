package AlgorithmClasses;

import DataClasses.AlgorithmData.AmountOfObjectsCalc;
import DataClasses.AlgorithmData.Generation;
import DataClasses.AlgorithmData.Parent;
import DataTransferClasses.CrossoverData;
import ParsedClasses.ETTCrossover;

import java.util.*;

public class Crossover {

    private String m_Name;
    private Integer m_NumOfCuttingPoints;
    private String m_Configuration;
    private Character m_Char;
    private eCrossover m_eType;
    private Random m_Roller;



    public Crossover(ETTCrossover i_ETTCrossover)
    {
        m_Name=i_ETTCrossover.getName();
        m_NumOfCuttingPoints =i_ETTCrossover.getCuttingPoints();
        m_Configuration=i_ETTCrossover.getConfiguration();
        m_eType =eCrossover.valueOf(m_Name.toUpperCase());
        m_Roller=new Random();
        extractConfiguration();
    }

    public CrossoverData getCrossoverData()
    {
        return new CrossoverData(this);
    }

    public eCrossover getConfiguration() {
        return m_eType;
    }

    public String getName() {
        return m_Name;
    }

    public Integer getCuttingPoints() {
        return m_NumOfCuttingPoints;
    }

    public Character getChar() {
        return m_Char;
    }

    public void createNewGenerationFromGroupOfParents(Generation i_PrevGeneration,Generation i_NextGeneration, AmountOfObjectsCalc i_AmountOfObj){
        Integer generationSize=i_PrevGeneration.getGenerationSize();
        List<Integer> cuttingPoints = rollCuttingPoints(i_AmountOfObj.getMaxAmountOfLessons());////rolling the cutting points
        for (int i = 0; i < generationSize; i += 2) {
            //checking out of range (not always we have pairs)
            if ((i + 1) == generationSize)
                i--;
            m_eType.activate(i_PrevGeneration.getParentByIndex(i), i_PrevGeneration.getParentByIndex(i + 1)
                    , i_AmountOfObj,m_Char, cuttingPoints, i_NextGeneration);
        }

    }

    public void createTwoChildren(Generation i_NextGeneration,Parent i_P1,Parent i_P2,AmountOfObjectsCalc i_AmountOfObj)
    {
        List<Integer> cuttingPoints = rollCuttingPoints(i_AmountOfObj.getMaxAmountOfLessons());////rolling the cutting
        m_eType.activate(i_P1,i_P2,i_AmountOfObj,m_Char,cuttingPoints,i_NextGeneration);
    }

    public boolean checkIfConfigurationEmpty()
    {
        if(m_Configuration==null)
            return true;
        return false;
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

    private void extractConfiguration() {
        if(m_Configuration!=null) {
            int configurationSize = m_Configuration.length();
            char[] chars = m_Configuration.toCharArray();
            int count = 0;
            while (chars[count] != '=')
                count++;
            count++;
            m_Char = chars[count];
        }
    }
}
