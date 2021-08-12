package DataClasses.FileInputDataClasses;

import DataClasses.AlgorithmData.Generation;
import DataClasses.AlgorithmData.Lesson;
import DataClasses.AlgorithmData.Parent;
import DataTransferClasses.SubjectData;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum eRules {
    TEACHERISHUMAN
            {
                @Override
                public Integer CheckRule(Parent i_Parent, TimeTable i_TimeTable) {
                    Integer fitness;
                    Integer numOfTeachers = i_TimeTable.getTeachers().getTeacherListSize();
                    Set<Integer> badTeachersIDSet = new HashSet<>();
                    List<Lesson> lessonsList = i_Parent.getLessonsList();
                    for (int i = 1; i <= numOfTeachers; i++) {
                        int teacherID = i;
                        List<Lesson> lessonsOfTeacherList = lessonsList.stream().filter(l1 -> l1.getTeacherID().equals(teacherID)).collect(Collectors.toList());
                        for (Lesson lesson : lessonsOfTeacherList) {
                            long count = lessonsOfTeacherList.stream().filter(l1 -> l1.getDay().equals(lesson.getDay())
                                    && l1.getHour().equals(lesson.getHour())).count();
                            if (count > 1) {
                                badTeachersIDSet.add(teacherID);
                                break;
                            }
                        }
                    }
                    fitness = 100-(badTeachersIDSet.size() * 100 / numOfTeachers);
                    return fitness;
                }
            },
    SINGULARITY
            {
                @Override
                public Integer CheckRule(Parent i_Parent, TimeTable i_TimeTable)
                {
                    Integer fitness;
                    Integer numOfClasses = i_TimeTable.getClazzes().getClassesListSize();
                    Set<Integer> badClassesIDSet = new HashSet<>();
                    List<Lesson> lessonsList = i_Parent.getLessonsList();
                    for (int i = 1; i <= numOfClasses; i++) {
                        int classID = i;
                        List<Lesson> lessonsOfClassList = lessonsList.stream().filter(l1 -> l1.getClassID().equals(classID)).collect(Collectors.toList());
                        for (Lesson lesson : lessonsOfClassList) {
                            long count = lessonsOfClassList.stream().filter(l1 -> l1.getDay().equals(lesson.getDay())
                                    && l1.getHour().equals(lesson.getHour())).count();
                            if (count > 1) {
                                badClassesIDSet.add(classID);
                                break;
                            }
                        }
                    }
                    fitness = 100-(badClassesIDSet.size() * 100 / numOfClasses);
                    return fitness;
                }
            },
    KNOWLEDGEABLE
            {
                @Override
                public Integer CheckRule(Parent i_Parent, TimeTable i_TimeTable)
                {
                    Integer fitness;
                    List<Integer> teacherGradesInRule=new ArrayList<>();
                    Teachers teachers = i_TimeTable.getTeachers();
                    int amountOfSubjects=i_TimeTable.getSubjects().getSubjectsListSize();
                    for(Teacher t:teachers.getTeachersList())
                    {
                        List<Lesson> LessonsTeacherTeachInSolution=i_Parent.getLessonsList().stream()
                                .filter(lesson->lesson.getTeacherID().equals(t.getId()))
                                .collect(Collectors.toList());
                        List<Integer> badSubjectsID=new ArrayList<>();
                        List<Integer> canTeachSubjects=t.getSubjectsIDList();
                        int allCantTeachAmount=amountOfSubjects-t.getAmountOfSubjectsTeacherTeach();
                        for(Lesson lesson:LessonsTeacherTeachInSolution)
                        {
                            if(!canTeachSubjects.contains(lesson.getSubjectID()))
                            {
                                if(!badSubjectsID.contains(lesson.getSubjectID()))
                                {
                                    badSubjectsID.add(lesson.getSubjectID());
                                    if(badSubjectsID.size()==allCantTeachAmount)
                                        break;
                                }
                            }
                        }
                        int alreadyTeachAndCantTeachAmount= badSubjectsID.size();
                        int teacherGrade=((allCantTeachAmount-alreadyTeachAndCantTeachAmount)/allCantTeachAmount)*100;
                        teacherGradesInRule.add(teacherGrade);

                    }
                    fitness=(teacherGradesInRule.stream().mapToInt(grade->grade).sum())/(teacherGradesInRule.size());
                    return fitness;
                }
            },
    SATISFACTORY
            {
                @Override
                public Integer CheckRule(Parent i_Parent, TimeTable i_TimeTable)
                {
                    Integer numOfClasses = i_TimeTable.getClazzes().getClassesListSize();
                    List<Integer> classesGrades=new ArrayList<>();
                    List<Lesson> lessonsList = i_Parent.getLessonsList();
                    for (int i = 1; i <= numOfClasses; i++) {
                        int classID = i;
                        int badSubjects=0;
                        int numOfClassSubjects=i_TimeTable.getClazzes().getClassById(classID).getRequirements().getStudyList().size();
                        List<Lesson> lessonsOfClassList = lessonsList.stream().filter(l1 -> l1.getClassID().equals(classID)).collect(Collectors.toList());
                        Map<Integer, Integer> subjectId2ReqHoursMap = i_TimeTable.getClazzes().getClassById(classID).getRequirements().getSubjectId2ReqHoursMap();
                        Map<Integer,Integer> sumMap=new HashMap<>();
                        subjectId2ReqHoursMap.keySet().forEach(key->sumMap.put(key,0));
                        List<Lesson> alreadyCounted=new ArrayList<>();
                        for(Lesson lesson:lessonsOfClassList)
                        {
                            long count = alreadyCounted.stream().filter(subLesson -> subLesson.getDay().equals(lesson.getDay()) &&
                                    subLesson.getHour().equals(lesson.getHour()) &&
                                    subLesson.getSubjectID().equals(lesson.getSubjectID())).count();
                            if(count==0) {
                                if (sumMap.containsKey(lesson.getSubjectID())) {
                                    Integer currentValue = sumMap.get(lesson.getSubjectID()) + 1;
                                    sumMap.put(lesson.getSubjectID(), currentValue);
                                    alreadyCounted.add(lesson);
                                }
                            }
                        }
                        for(Map.Entry<Integer,Integer> entry:sumMap.entrySet())
                        {
                            if(entry.getValue()!=subjectId2ReqHoursMap.get(entry.getKey()))
                            {
                                badSubjects++;
                            }
                        }
                        classesGrades.add(100-(badSubjects*100/numOfClassSubjects));
                    }
                    return classesGrades.stream().mapToInt(i->i).sum()/classesGrades.size();
                }
            },
    DAYOFFTEACHER
            {
                @Override
                public Integer CheckRule(Parent i_Parent, TimeTable i_TimeTable) {
                    return 0;
                }
            },
    SEQUENTIALITY
            {
                @Override
                public Integer CheckRule(Parent i_Parent, TimeTable i_TimeTable) {
                    return 0;
                }
            };

    public abstract Integer CheckRule(Parent i_Parent, TimeTable i_TimeTable);
}
