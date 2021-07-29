package DataClasses.AlgorithmData;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Solution {
    private List<Lesson> m_Solution;

    public Solution(Integer length)
    {
        List<Lesson> lst=new ArrayList<>(length);
    }

    public List<Lesson> getSolution() {
        return m_Solution;
    }

}
