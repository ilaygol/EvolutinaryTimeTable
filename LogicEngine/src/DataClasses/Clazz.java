package DataClasses;

import ParsedClasses.ETTClass;

import java.util.List;
import java.util.Objects;

public class Clazz {
    private final Integer m_Id;
    private List<String> m_FullName;
    private Requirements m_Requirements;

    public Clazz(ETTClass i_ETTClass)
    {
        m_Id=i_ETTClass.getId();
        m_Requirements=new Requirements(i_ETTClass.getETTRequirements());
        m_FullName=i_ETTClass.getETTName();
    }

    @Override
    public String toString() {
        return "Clazz{" +
                "m_Id=" + m_Id +
                ", m_FullName=" + m_FullName +
                ", m_Requirements=" + m_Requirements +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clazz clazz = (Clazz) o;
        return m_Id.equals(clazz.m_Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_Id);
    }
}
