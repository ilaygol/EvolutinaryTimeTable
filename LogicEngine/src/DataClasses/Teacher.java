package DataClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Teacher {
    private List<String> m_FullName;
    private List<Subject> m_SubjectsList;
    private final Integer m_Id;

    public Teacher(Integer i_Id)
    {
        m_FullName=new ArrayList<>();
        m_SubjectsList=new ArrayList<>();
        m_Id=i_Id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return m_Id.equals(teacher.m_Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_Id);
    }
}
