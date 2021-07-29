package DataClasses;

import DataTransferClasses.ClassSubjectData;
import DataTransferClasses.SubjectData;
import ParsedClasses.ETTClass;
import ParsedClasses.ETTClasses;

import java.util.*;

public class Clazzes {
    private List<Clazz> m_ClassesList;

    public Clazzes(ETTClasses i_ETTClasses)
    {
        m_ClassesList=new ArrayList<>();
        List<ETTClass> ettClasses = i_ETTClasses.getETTClass();
        for(ETTClass ettClass:ettClasses)
        {
            m_ClassesList.add(new Clazz(ettClass));
        }
    }

    @Override
    public String toString() {
        return "Clazzes{" +
                "m_ClassesList=" + m_ClassesList +
                '}';
    }

    public List<Clazz> getClassesList() {
        return m_ClassesList;
    }

    public Map<Integer, Set<ClassSubjectData>> getClassID2SubjectsMap(Set<SubjectData> i_Subjects)
    {
        Map<Integer, Set<ClassSubjectData>> retMap=new TreeMap<>();
        for(Clazz c:m_ClassesList)
        {
            Set<ClassSubjectData> classSubjectsSet=new TreeSet<>();
            List<Study> classesSubjectsIDList=c.getRequirements().getStudyList();
            for(Study i:classesSubjectsIDList)
            {
                SubjectData subject = i_Subjects.stream().filter(subj -> subj.getSubjectID() == i.getSubjectID()).findFirst().get();
                classSubjectsSet.add(new ClassSubjectData(i,subject));
            }
            retMap.put(c.getId(),classSubjectsSet);
        }
        return retMap;
    }

}
