package AlgorithmClasses;

import DataClasses.AlgorithmData.AmountOfObjectsCalc;
import DataClasses.AlgorithmData.Generation;
import DataClasses.AlgorithmData.Lesson;
import DataClasses.AlgorithmData.Parent;

import java.util.Collection;

public enum eCrossover {
    DAYTIMEORIENTED
            {
                @Override
                public  void activate(Parent p1, Parent p2, AmountOfObjectsCalc amounts
                        , Collection<Integer> cuttingPoints, Generation i_NextGeneration) {

                    int index=0;
                    Parent childOne=new Parent(amounts.getMaxAmountOfLessons());
                    Parent childTwo=new Parent(amounts.getMaxAmountOfLessons());

                    boolean copyFromFirstParent=true;
                    for(int i=1;i<=amounts.getAmountOfDays();i++)
                        for(int j=1;j<=amounts.getAmountOfHours();j++)
                            for(int k=1;k<= amounts.getAmountOfClasses();k++)
                                for(int m=1;m<= amounts.getAmountOfTeachers();m++)
                                    for(int n=1;n<= amounts.getAmountOfSubjects();n++) {
                                        Lesson checkLesson=new Lesson(i,j,k,m,n);
                                        if (copyFromFirstParent) {
                                            if (p1.isContain(checkLesson))
                                                childOne.addLessonToParent(checkLesson);
                                        }
                                        else {
                                            if(p2.isContain(checkLesson))
                                                childOne.addLessonToParent(checkLesson);
                                            }
                                        index++;
                                        if(cuttingPoints.contains(index))
                                            copyFromFirstParent = (!copyFromFirstParent);
                                    }
                    i_NextGeneration.addSolutionToList(childOne);
                    copyFromFirstParent=false;
                    for(int i=1;i<=amounts.getAmountOfDays();i++)
                        for(int j=1;j<=amounts.getAmountOfHours();j++)
                            for(int k=1;k<= amounts.getAmountOfClasses();k++)
                                for(int m=1;m<= amounts.getAmountOfTeachers();m++)
                                    for(int n=1;n<= amounts.getAmountOfSubjects();n++) {
                                        Lesson checkLesson=new Lesson(i,j,k,m,n);
                                        if (copyFromFirstParent) {
                                            if (p1.isContain(checkLesson))
                                                childTwo.addLessonToParent(checkLesson);
                                        }
                                        else {
                                            if(p2.isContain(checkLesson))
                                                childTwo.addLessonToParent(checkLesson);
                                        }
                                        index++;
                                        if(cuttingPoints.contains(index))
                                            copyFromFirstParent = (!copyFromFirstParent);
                                    }
                    i_NextGeneration.addSolutionToList(childTwo);
                }

            };
    public abstract void activate(Parent p1, Parent p2, AmountOfObjectsCalc amounts, Collection<Integer> cuttingPoints,Generation i_NextGeneration);
}
