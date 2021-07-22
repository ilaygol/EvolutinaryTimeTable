package DataClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Subject {
    private List<String> m_FullName;
    private final Integer m_Id;

    public Subject(Integer i_Id)
    {
        m_FullName=new ArrayList<>();
        m_Id=i_Id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return m_Id.equals(subject.m_Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_Id);
    }
}
