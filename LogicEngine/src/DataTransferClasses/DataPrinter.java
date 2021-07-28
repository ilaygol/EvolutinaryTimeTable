package DataTransferClasses;

import java.util.*;

public class DataPrinter {
    private Map<Integer, String> m_ID2SubjectMap;
    private Map<Integer, Map<Integer, String>> m_TeachersID2SubjMap;
    private Map<Integer, Map<Integer, String>> m_ClassesID2SubjMap;
    private Map<Integer, Map<Integer, Integer>> m_ClassID2ReqHoursMap;
    private Map<String, String> m_RulesNames2TypeMap;

    public DataPrinter() {
        m_ID2SubjectMap = new TreeMap<>();
        m_TeachersID2SubjMap = new TreeMap<>();
        m_ClassesID2SubjMap = new TreeMap<>();
        m_ClassID2ReqHoursMap = new TreeMap<>();
        m_RulesNames2TypeMap = new HashMap<>();
    }

    public Map<Integer, String> getID2SubjectMap() {
        return m_ID2SubjectMap;
    }
    public void setID2SubjectMap(Map<Integer, String> i_ID2SubjectMap) {
        this.m_ID2SubjectMap = i_ID2SubjectMap;
    }
    public void AddToSubjectMap(Integer i_Key, String i_Value) {
        m_ID2SubjectMap.put(i_Key, i_Value);
    }

    public Map<Integer, Map<Integer, String>> getTeachersID2SubjMap() {
        return m_TeachersID2SubjMap;
    }
    public void setTeachersID2SubjMap(Map<Integer, Map<Integer, String>> i_TeachersID2SubjMap) {
        this.m_TeachersID2SubjMap = i_TeachersID2SubjMap;
    }
    public void AddToTeachersID2SubjMap(Integer i_Key, Map<Integer, String> i_Value) {
        m_TeachersID2SubjMap.put(i_Key, i_Value);
    }

    public Map<Integer, Map<Integer, String>> getClassesID2SubjMap() {
        return m_ClassesID2SubjMap;
    }
    public void setClassesID2SubjMap(Map<Integer, Map<Integer, String>> i_ClassesID2SubjMap) {
        this.m_ClassesID2SubjMap = i_ClassesID2SubjMap;
    }
    public void AddToClassesID2SubjMap(Integer i_Key, Map<Integer, String> i_Value) {
        m_ClassesID2SubjMap.put(i_Key, i_Value);
    }

    public Map<Integer, Map<Integer, Integer>> getClassID2ReqHoursMap() {
        return m_ClassID2ReqHoursMap;
    }
    public void setClassID2ReqHoursMap(Map<Integer, Map<Integer, Integer>> i_ClassID2ReqHoursMap) {
        this.m_ClassID2ReqHoursMap = i_ClassID2ReqHoursMap;
    }
    public void AddToClassID2ReqHoursMap(Integer i_Key, Map<Integer, Integer> i_Value) {
        m_ClassID2ReqHoursMap.put(i_Key, i_Value);
    }

    public Map<String, String> getRulesNames2TypeMap() {
        return m_RulesNames2TypeMap;
    }
    public void setRulesNames2TypeMap(Map<String, String> i_RulesNames2TypeMap) {
        this.m_RulesNames2TypeMap = i_RulesNames2TypeMap;
    }
    public void AddToRulesNames2TypeMap(String i_Key, String i_Value) {
        m_RulesNames2TypeMap.put(i_Key, i_Value);
    }
}