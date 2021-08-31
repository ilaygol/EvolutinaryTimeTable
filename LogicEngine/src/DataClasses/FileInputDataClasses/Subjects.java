package DataClasses.FileInputDataClasses;

import DataTransferClasses.SubjectData;
import ParsedClasses.ETTSubject;
import ParsedClasses.ETTSubjects;

import java.util.*;

public class Subjects {
    private List<Subject> m_SubjectsList;

    public Subjects(ETTSubjects i_ETTSubjects)
    {
        m_SubjectsList=new ArrayList<>();
        List<ETTSubject> ettSubjects = i_ETTSubjects.getETTSubject();
        for(ETTSubject subject:ettSubjects)
        {
            m_SubjectsList.add(new Subject(subject));
        }
    }

    public List<Subject> getSubjectsList() {
        return m_SubjectsList;
    }

    public Integer getSubjectsListSize(){return m_SubjectsList.size();}

    public Set<SubjectData> getSubjectSet(){
        Set<SubjectData> retSet=new TreeSet<>();
        m_SubjectsList.forEach(subj-> retSet.add(new SubjectData(subj)));
        return retSet;
    }

    public String getSubjectNameById(Integer i_ID)
    {
        return m_SubjectsList.stream().filter(subject->subject.getId().equals(i_ID)).findFirst().get().getFullName();
    }

    @Override
    public String toString() {
        return "Subjects{" +
                "m_SubjectsList=" + m_SubjectsList +
                '}';
    }
}
