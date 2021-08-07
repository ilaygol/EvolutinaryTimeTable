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

                    Integer numOfTeachers = i_TimeTable.getTeachers().getTeacherListSize();
                    Set<Integer> badTeachersIDSet = new HashSet<>();
                    List<Lesson> lessonsList = i_Parent.getLessonsList();
                    for (int i = 1; i <= numOfTeachers; i++) {
                        int teacherID = i;
                        Teacher currTeacher = i_TimeTable.getTeachers().getTeacherById(teacherID);
                        List<Integer> currTeacherSubjectsIDList = currTeacher.getSubjectsIDList();
                        List<Lesson> lessonsOfTeacherList = lessonsList.stream().filter(l1 -> l1.getTeacherID().equals(teacherID)).collect(Collectors.toList());
                        for (Lesson lesson : lessonsOfTeacherList) {
                            if(!currTeacherSubjectsIDList.contains(lesson.getSubjectID()))
                            {
                                badTeachersIDSet.add(teacherID);
                                break;
                            }
                        }
                    }
                    fitness = 100-(badTeachersIDSet.size() * 100 / numOfTeachers);
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
                        lessonsOfClassList.forEach(lesson -> sumMap.replace(lesson.getSubjectID(),sumMap.get(lesson.getSubjectID())+1));
                        for(Map.Entry<Integer,Integer> entry:sumMap.entrySet())
                        {
                            if(entry.getValue()<subjectId2ReqHoursMap.get(entry.getKey()))
                            {
                                badSubjects++;
                            }
                        }
                        classesGrades.add(100-(badSubjects*100/numOfClassSubjects));
                    }
                    return classesGrades.stream().mapToInt(i->i).sum()/classesGrades.size();
                }
            };


    public abstract Integer CheckRule(Parent i_Parent, TimeTable i_TimeTable);
}
