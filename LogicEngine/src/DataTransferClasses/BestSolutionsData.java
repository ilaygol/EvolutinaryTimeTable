package DataTransferClasses;

import DataClasses.AlgorithmData.Lesson;
import DataClasses.AlgorithmData.Parent;

import java.util.ArrayList;
import java.util.List;

public class BestSolutionsData {
    private List<LessonData> m_LessonsDataList;
    private Integer m_Fitness;

    public BestSolutionsData(Parent i_Parent)
    {
        m_LessonsDataList=new ArrayList<>();
        for(Lesson lesson:i_Parent.getLessonsList())
        {
            m_LessonsDataList.add(new LessonData(lesson));
        }
        m_Fitness=i_Parent.getFitness();
    }

    public Integer getFitness() {
        return m_Fitness;
    }

    public List<LessonData> getLessonsDataList() {
        return m_LessonsDataList;
    }
}
