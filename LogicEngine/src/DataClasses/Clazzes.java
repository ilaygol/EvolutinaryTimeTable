package DataClasses;

import ParsedClasses.ETTClass;
import ParsedClasses.ETTClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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

    public Map<Integer, Map<Integer, String>> getClassesID2SubjMap(Map<Integer, String> i_ID2SubjectMap)
    {
        Map<Integer, Map<Integer, String>> retMap=new TreeMap<>();
        for(Clazz c:m_ClassesList)
        {
            Map<Integer,String> classesSubjectID2NameMap=new TreeMap<>();
            List<Study> classesSubjectsIDList=c.getRequirements().getStudyList();
            for(Study i:classesSubjectsIDList)
            {
                classesSubjectID2NameMap.put(i.getSubjectId(),i_ID2SubjectMap.get(i.getSubjectId()));
            }
            retMap.put(c.getId(),classesSubjectID2NameMap);
        }
        return retMap;
    }

    public Map<Integer, Map<Integer, Integer>> getClassesID2ReqSubjHoursMap()
    {
        Map<Integer, Map<Integer, Integer>> retMap=new TreeMap<>();
        for(Clazz c:m_ClassesList)
        {
            Map<Integer,Integer> classesSubjectID2ReqHoursMap=new TreeMap<>();
            List<Study> classesSubjectsIDList=c.getRequirements().getStudyList();
            for(Study i:classesSubjectsIDList)
            {
                classesSubjectID2ReqHoursMap.put(i.getSubjectId(),i.getHours());
            }
            retMap.put(c.getId(),classesSubjectID2ReqHoursMap);
        }
        return retMap;
    }

}
