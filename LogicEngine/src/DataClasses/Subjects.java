package DataClasses;

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

    @Override
    public String toString() {
        return "Subjects{" +
                "m_SubjectsList=" + m_SubjectsList +
                '}';
    }

    public Subject getSubjectById(Integer i_Id)
    {
        Optional<Subject> subj = m_SubjectsList.stream().filter(subject -> subject.getId().equals(i_Id)).findFirst();
        return subj.get();
    }

    public List<Subject> getSubjectsList() {
        return m_SubjectsList;
    }
    public Map<Integer,String> getID2SubjNameMap(){
        Map<Integer,String> retMap =new TreeMap<>();
        for(Subject s:m_SubjectsList)
        {
            retMap.put(s.getId(),s.getFullName().toString());
        }
        return retMap;
    }
}
