import DataClasses.AlgorithmData.AmountOfObjectsCalc;
import DataTransferClasses.BestSolutionsData;
import DataTransferClasses.LessonData;
import DataTransferClasses.RuleData;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public enum ePrintOptions {
    RAW
            {
                @Override
                public String ToString()
                {
                    return "By Raw";
                }

                @Override
                public void printSolution(BestSolutionsData i_BestSolution, AmountOfObjectsCalc i_MaxAmounts)
                {
                    i_BestSolution.getLessonsDataList().sort(Comparator
                            .comparing(LessonData::getDay)
                            .thenComparing(LessonData::getHour)
                            .thenComparing(LessonData::getClassID)
                            .thenComparing(LessonData::getTeacherID));
                    i_BestSolution.getLessonsDataList().forEach(lesson->System.out.println(
                            "<"+lesson.getDay()+","+lesson.getHour()+","+lesson.getClassID()+","
                                    +lesson.getTeacherID()+","+lesson.getSubjectID()+">"));
                    ePrintOptions.printExtra(i_BestSolution);
                }
            },
    TEACHER
            {
                @Override
                public String ToString()
                {
                    return "By Teacher";
                }

                @Override
                public void printSolution(BestSolutionsData i_BestSolution, AmountOfObjectsCalc i_MaxAmounts)
                {
                    Integer numOfTeachers=i_MaxAmounts.getAmountOfTeachers();
                    for(int i=1;i<=numOfTeachers;i++)
                    {
                        Integer teacherId=i;
                        System.out.println("Teacher "+i+" time table:");
                        List<LessonData> lessonsOfTeacherList = i_BestSolution.getLessonsDataList().stream().filter(lesson -> lesson.getTeacherID().equals(teacherId)).collect(Collectors.toList());

                        for(int d=1;d<=i_MaxAmounts.getAmountOfDays();d++)
                        {
                            System.out.print("Day "+d+":\t");
                            for (int h=1;h<=i_MaxAmounts.getAmountOfHours();h++)
                            {
                                Integer day=d;
                                Integer hour=h;
                                List<LessonData> dayHourLessons = lessonsOfTeacherList.stream().filter(
                                        lesson -> lesson.getDay().equals(day) && lesson.getHour().equals(hour)).collect(Collectors.toList());
                                System.out.print("Hour "+hour+":\t");
                                if(dayHourLessons.isEmpty())
                                {
                                    System.out.print("none\t");
                                }
                                else {
                                    for (LessonData lesson : dayHourLessons) {
                                        System.out.print("<C=" + lesson.getClassID() + " S=" + lesson.getSubjectID() + "> ");
                                    }
                                    System.out.print("\t");
                                }
                            }
                            System.out.println();
                        }
                    }
                    ePrintOptions.printExtra(i_BestSolution);
                }
            },
    CLASS
            {
                @Override
                public String ToString()
                {
                    return "By Class";
                }

                @Override
                public void printSolution(BestSolutionsData i_BestSolution, AmountOfObjectsCalc i_MaxAmounts)
                {
                    Integer numOfClasses=i_MaxAmounts.getAmountOfClasses();
                    for(int i=1;i<=numOfClasses;i++)
                    {
                        Integer classId=i;
                        System.out.println("Class "+i+" time table:");
                        List<LessonData> lessonsOfClassList = i_BestSolution.getLessonsDataList().stream().filter(lesson -> lesson.getClassID().equals(classId)).collect(Collectors.toList());

                        for(int d=1;d<=i_MaxAmounts.getAmountOfDays();d++)
                        {
                            System.out.print("Day "+d+":\t");
                            for (int h=1;h<=i_MaxAmounts.getAmountOfHours();h++)
                            {
                                Integer day=d;
                                Integer hour=h;
                                List<LessonData> dayHourLessons = lessonsOfClassList.stream().filter(
                                        lesson -> lesson.getDay().equals(day) && lesson.getHour().equals(hour)).collect(Collectors.toList());
                                System.out.print("Hour "+hour+":\t");
                                if(dayHourLessons.isEmpty())
                                {
                                    System.out.print("none\t");
                                }
                                else {
                                    for (LessonData lesson : dayHourLessons) {
                                        System.out.print("<T=" + lesson.getTeacherID() + " S=" + lesson.getSubjectID() + "> ");
                                    }
                                    System.out.print("\t");
                                }
                            }
                            System.out.println();
                        }
                    }
                    ePrintOptions.printExtra(i_BestSolution);
                }
            };

    public abstract String ToString();

    public static void PrintMenu()
    {
        ePrintOptions[] values = ePrintOptions.values();
        for (int i=1; i<=values.length;i++) {
            System.out.println(i + ". " + values[i - 1].ToString());
        }
    }

    public abstract void printSolution(BestSolutionsData i_BestSolution, AmountOfObjectsCalc i_MaxAmounts);

    private static void printExtra(BestSolutionsData i_BestSolution)
    {
        System.out.println(System.lineSeparator()+"This solution's fitness: "+i_BestSolution.getFitness());
        System.out.println(System.lineSeparator()+"Rules description:");
        for(RuleData rule:i_BestSolution.getRulesDataList())
        {
            System.out.println("Name: "+rule.getName());
            System.out.println("Type: "+rule.getType());
            System.out.println("Grade: "+rule.getGrade());
        }
        System.out.println("Hard rules average: "+i_BestSolution.getHardRulesAverage());
        System.out.println("Soft rules average: "+i_BestSolution.getSoftRulesAverage());
    }
}
