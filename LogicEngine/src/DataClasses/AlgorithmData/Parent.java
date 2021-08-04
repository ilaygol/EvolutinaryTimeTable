package DataClasses.AlgorithmData;

import DataClasses.FileInputDataClasses.Teacher;

import java.util.*;

public class Parent {
    private List<Lesson> m_LessonsList;
    private Random m_Roller;
    private Integer m_Fitness;


    public Parent(Integer i_Length)
    {
        m_LessonsList =new ArrayList<>(i_Length);
        m_Roller=new Random();
    }

    public List<Lesson> getLessonsList() {
        return m_LessonsList;
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
            m_LessonsList.add(lesson);
        }
    }


    public void setFitness(Integer i_Fitness) {
        this.m_Fitness = i_Fitness;
    }

    public Integer getFitness() {
        return m_Fitness;
    }
}
