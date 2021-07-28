package DataClasses;

import ParsedClasses.ETTTeacher;
import ParsedClasses.ETTTeachers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
    public Map<Integer, Map<Integer, String>> getTeachersID2SubjMap(Map<Integer, String> ID2SubjectMap)
    {
        Map<Integer, Map<Integer, String>> retMap=new TreeMap<>();
        for(Teacher t:m_TeachersList)
        {
            Map<Integer,String> teachersSubjectID2NameMap=new TreeMap<>();
            List<Integer> teacherSubjectsIDList=t.getSubjectsIDList();
            for(Integer i:teacherSubjectsIDList) {
                teachersSubjectID2NameMap.put(i,ID2SubjectMap.get(i));
            }
            retMap.put(t.getId(),teachersSubjectID2NameMap);
        }
        return  retMap;

    }
}
