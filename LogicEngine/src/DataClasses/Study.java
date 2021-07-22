package DataClasses;

import ParsedClasses.ETTStudy;

public class Study {
    private final Integer m_SubjectId;
    private Integer m_Hours;

    public Study(ETTStudy i_ETTStudy)
    {
        m_SubjectId=i_ETTStudy.getSubjectId();
        m_Hours=i_ETTStudy.getHours();
    }

}
