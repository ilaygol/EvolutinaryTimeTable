package DataClasses;

import java.util.ArrayList;
import java.util.List;

public class Teachers {
    private List<Teacher> m_TeachersList;

    public Teachers()
    {
        m_TeachersList=new ArrayList<>();
    }

    public List<Teacher> getTeachersList()
    {
        return m_TeachersList;
    }

    public void addTeacherToList(Teacher i_Teacher)
    {
        m_TeachersList.add(i_Teacher);
    }
}
