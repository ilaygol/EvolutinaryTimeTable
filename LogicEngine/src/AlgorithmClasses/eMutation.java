package AlgorithmClasses;

import DataClasses.AlgorithmData.AmountOfObjectsCalc;
import DataClasses.AlgorithmData.Generation;
import DataClasses.AlgorithmData.Parent;

import java.util.Random;

public enum eMutation {
    FLIPPING
            {
                @Override
                public void activate(Integer i_MaxTupples, Generation i_Generation,
                                                    AmountOfObjectsCalc i_AmountOfObj,
                                                    Character i_Char,Random i_Roller) {

                    int parentIndex=i_Roller.nextInt(i_Generation.getGenerationSize());
                    Parent newParent=new Parent(i_Generation.getParentByIndex(parentIndex));
                    int numOfChanges=i_Roller.nextInt(i_MaxTupples-1)+1;
                    for(int i=0;i<numOfChanges;i++)
                    {
                        int lessonToChange=i_Roller.nextInt(newParent.getParentSize());
                        makeAChangeInParent(newParent,i_AmountOfObj,i_Char,lessonToChange,i_Roller);
                    }
                    i_Generation.addParentToGeneration(newParent);
                }
            };
    public abstract void activate(Integer i_MaxTupples, Generation i_Generation,
                                        AmountOfObjectsCalc i_AmountOfObj,
                                        Character i_Char,Random i_Roller);

    public void makeAChangeInParent(Parent i_Parent,AmountOfObjectsCalc i_AmountOfObj,Character i_Char
                                    ,Integer i_LessonIndex,Random i_Roller)
    {
        int newVal;
        switch (i_Char)
        {
            case 'C':
                newVal=i_Roller.nextInt(i_AmountOfObj.getAmountOfClasses()-1)+1;
                i_Parent.getLessonByIndex(i_LessonIndex).setClassID(newVal);
                break;
            case 'H':
                newVal=i_Roller.nextInt(i_AmountOfObj.getAmountOfHours()-1)+1;
                i_Parent.getLessonByIndex(i_LessonIndex).setHour(newVal);
                break;
            case 'D':
                newVal=i_Roller.nextInt(i_AmountOfObj.getAmountOfDays()-1)+1;
                i_Parent.getLessonByIndex(i_LessonIndex).setDay(newVal);
                break;
            case 'T':
                newVal=i_Roller.nextInt(i_AmountOfObj.getAmountOfTeachers()-1)+1;
                i_Parent.getLessonByIndex(i_LessonIndex).setTeacherID(newVal);
                break;
            case 'S':
                newVal=i_Roller.nextInt(i_AmountOfObj.getAmountOfSubjects()-1)+1;
                i_Parent.getLessonByIndex(i_LessonIndex).setSubjectID(newVal);
                break;

        }

    }
}
