package DataClasses;

import ParsedClasses.ETTTeacher;
import ParsedClasses.ETTTeachers;

import java.util.ArrayList;
import java.util.List;

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
}
