package DataClasses;

import ParsedClasses.ETTRequirements;
import ParsedClasses.ETTStudy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Requirements {
    private List<Study> m_StudyList;

    public Requirements(ETTRequirements i_ETTRequirements)
    {
        m_StudyList=new ArrayList<>();
        List<ETTStudy> ettStudies = i_ETTRequirements.getETTStudy();
        for(ETTStudy ettStudy:ettStudies)
        {
            m_StudyList.add(new Study(ettStudy));
        }
    }

    public Integer getReqHoursBySubjId(Integer i_Id)
    {
        Optional<Study> study = m_StudyList.stream().filter(lesson -> lesson.getHours().equals(i_Id)).findFirst();
        return study.get().getHours();
    }


    @Override
    public String toString() {
        return "Requirements{" +
                "m_StudyList=" + m_StudyList +
                '}';
    }

    public List<Study> getStudyList() {
        return m_StudyList;
    }


}
