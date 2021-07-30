package DataClasses.AlgorithmData;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TimeTableSolution {
    private List<Lesson> m_Solution;
    private Random m_Roller;


    public TimeTableSolution(Integer i_Length)
    {
        m_Solution=new ArrayList<>(i_Length);
        for(int i=0;i<i_Length;i++) {
            m_Solution.add(null);
        }
        m_Roller=new Random();
    }

    public List<Lesson> getSolution() {
        return m_Solution;
    }

    public void buildSolution (AmountOfObjectsCalc i_AmountOfObjects)
    {
        Integer minAmountOfLessons,maxAmountOfLessons;
        minAmountOfLessons=i_AmountOfObjects.getLessonsInSolution();
        maxAmountOfLessons=i_AmountOfObjects.getMaxAmountOfLessons();
        int lessonsInSolution= m_Roller.nextInt(maxAmountOfLessons-minAmountOfLessons)+minAmountOfLessons;

        for(int i=1;i<=lessonsInSolution;i++)
        {
            Integer day= m_Roller.nextInt(i_AmountOfObjects.getAmountOfDays()-1)+1;
            Integer hour= m_Roller.nextInt(i_AmountOfObjects.getAmountOfHours()-1)+1;
            Integer classID= m_Roller.nextInt(i_AmountOfObjects.getAmountOfClasses()-1)+1;
            Integer teacherID= m_Roller.nextInt(i_AmountOfObjects.getAmountOfTeachers()-1)+1;
            Integer subjectID= m_Roller.nextInt(i_AmountOfObjects.getAmountOfSubjects()-1)+1;

            Lesson lesson=new Lesson(day,hour,classID,teacherID,subjectID);
            m_Solution.add(getIndexFromLessonData(i_AmountOfObjects,lesson),lesson);
        }


    }

    //a function that receive a Lesson object and calculate his index in the solution list
    public Integer getIndexFromLessonData(AmountOfObjectsCalc i_AmountOfObjects,Lesson i_Lesson)
    {
        Integer retIndex;
        Integer dayBase,hourBase,classBase,teacherBase;
        teacherBase=i_AmountOfObjects.getAmountOfSubjects();
        classBase=teacherBase * i_AmountOfObjects.getAmountOfTeachers();
        hourBase=classBase * i_AmountOfObjects.getAmountOfClasses();
        dayBase=hourBase * i_AmountOfObjects.getAmountOfHours();

        retIndex= (int)(((i_Lesson.getDay()-1)*dayBase)+((i_Lesson.getHour()-1)*hourBase)+
                ((i_Lesson.getClassID()-1)*classBase)+ ((i_Lesson.getTeacherID()-1)*teacherBase) +
                (i_Lesson.getSubjectID()-1));


        return retIndex;
    }


}
