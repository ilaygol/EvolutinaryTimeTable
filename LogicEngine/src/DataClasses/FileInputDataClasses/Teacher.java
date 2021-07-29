package DataClasses.FileInputDataClasses;

import ParsedClasses.ETTTeacher;
import ParsedClasses.ETTTeaches;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Teacher {
    private List<String> m_FullName;
    private List<Integer> m_SubjectsIDList;
    private final Integer m_Id;

    public Teacher(ETTTeacher i_ETTTeacher)
    {
        m_Id=i_ETTTeacher.getId();
        m_FullName=i_ETTTeacher.getETTName();
        m_SubjectsIDList=new ArrayList<>();
        List<ETTTeaches> ettSubjects = i_ETTTeacher.getETTTeaching().getETTTeaches();
        for(ETTTeaches subject:ettSubjects)
        {
            m_SubjectsIDList.add(subject.getSubjectId());
        }
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "m_FullName=" + m_FullName +
                ", m_SubjectsIDList=" + m_SubjectsIDList +
                ", m_Id=" + m_Id +
                '}';
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

    public List<String> getFullName() {
        return m_FullName;
    }

    public List<Integer> getSubjectsIDList() {
        return m_SubjectsIDList;
    }

    public Integer getId() {
        return m_Id;
    }
}
