package DataClasses.FileInputDataClasses;

import DataTransferClasses.SubjectData;
import ParsedClasses.ETTTeacher;
import ParsedClasses.ETTTeachers;

import java.util.*;

public class Teachers {
    private List<Teacher> m_TeachersList;

    public Teachers(ETTTeachers i_ETTTeachers)
    {
        m_TeachersList=new ArrayList<>();
        List<ETTTeacher> ettTeachers = i_ETTTeachers.getETTTeacher();
        for(ETTTeacher teacher:ettTeachers)
        {
            m_TeachersList.add(new Teacher(teacher));
        }
    }

    @Override
    public String toString() {
        return "Teachers{" +
                "m_TeachersList=" + m_TeachersList +
                '}';
    }

    public List<Teacher> getTeachersList() {
        return m_TeachersList;
    }

    public Integer getTeacherListSize(){return m_TeachersList.size();}


    public Map<Integer, Set<SubjectData>> getTeacherID2SubjectsMap(Collection<SubjectData> i_SubjectSet)
    {
        Map<Integer, Set<SubjectData>> retMap=new TreeMap<>();
        for(Teacher t:m_TeachersList)
        {
            Set<SubjectData> teacherSubjectsSet=new TreeSet<>();
            List<Integer> teacherSubjectsIDList=t.getSubjectsIDList();
            for(Integer i:teacherSubjectsIDList) {
                teacherSubjectsSet.add(i_SubjectSet.stream().filter(subject -> subject.getSubjectID() == i).findFirst().get());
            }
            retMap.put(t.getId(),teacherSubjectsSet);
        }
        return  retMap;
    }

    public Teacher getTeacherById(Integer i_ID)
    {
        return m_TeachersList.get(i_ID-1);
    }
}
