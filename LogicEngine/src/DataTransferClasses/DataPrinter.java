package DataTransferClasses;

import java.util.*;

public class DataPrinter {
    private Map<Integer,String> m_SubjectMap;
    private Map<Integer,Map<Integer,String>> m_TeachersMap;
    private Map<Integer,Map<Integer,String>> m_ClassesMap;
    private Map<Integer,Map<Integer,Integer>> m_ClassReqHoursMap;
    private Map<String,String> m_RulesMap;

    public DataPrinter()
    {
        m_SubjectMap=new TreeMap<>();
        m_TeachersMap=new TreeMap<>();
        m_ClassesMap=new TreeMap<>();
        m_ClassReqHoursMap=new TreeMap<>();
        m_RulesMap=new HashMap<>();
    }

    public Map<Integer, String> getSubjectMap() {
        return m_SubjectMap;
    }

    public void AddToSubjectMap(Integer i_Key, String i_Value) {
        m_SubjectMap.put(i_Key,i_Value);
    }

    public Map<Integer, Map<Integer, String>> getTeachersMap() {
        return m_TeachersMap;
    }

    public void AddToTeachersMap(Integer i_Key, Map<Integer,String> i_Value) {
        m_TeachersMap.put(i_Key,i_Value);
    }

    public Map<Integer, Map<Integer, String>> getClassesMap() {
        return m_ClassesMap;
    }

    public void AddToClassesMap(Integer i_Key, Map<Integer,String> i_Value) {
        m_ClassesMap.put(i_Key,i_Value);
    }

    public Map<Integer, Map<Integer, Integer>> getClassReqHoursMap() {
        return m_ClassReqHoursMap;
    }

    public void AddToReqHoursMap(Integer i_Key, Map<Integer,Integer> i_Value) {
        m_ClassReqHoursMap.put(i_Key,i_Value);}

    public Map<String, String> getRulesMap() {
        return m_RulesMap;
    }

    public void AddToRulesMap(String i_Key, String i_Value) {
        m_RulesMap.put(i_Key,i_Value);
    }
}
