package DataClasses.AlgorithmData;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Solution {
    private List<Lesson> m_Solution;
    private Random m_Roller;


    public Solution(Integer i_Length)
    {
        m_Solution=new ArrayList<>(i_Length);
        m_Roller=new Random();
    }

    public List<Lesson> getSolution() {
        return m_Solution;
    }

    public void buildSolution (AmountOfObjectsCalc i_AmountOfObjects)
    {

    }

    //a function that receive a Lesson object and calculate his index in the solution list
    public Integer getIndexFromLessonData(Lesson i_Lesson)
    {
        return 0;
    }

}
