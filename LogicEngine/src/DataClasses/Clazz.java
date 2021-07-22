package DataClasses;

import ParsedClasses.ETTClass;

import java.util.List;

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
}
