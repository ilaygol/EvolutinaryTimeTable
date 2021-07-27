package DataTransferClasses;

import java.sql.SQLOutput;
import java.util.*;

public class DataPrinter {
    private Map<Integer, String> m_SubjectMap;
    private Map<Integer, Map<Integer, String>> m_TeachersMap;
    private Map<Integer, Map<Integer, String>> m_ClassesMap;
    private Map<Integer, Map<Integer, Integer>> m_ClassReqHoursMap;
    private Map<String, String> m_RulesMap;

    public DataPrinter() {
        m_SubjectMap = new TreeMap<>();
        m_TeachersMap = new TreeMap<>();
        m_ClassesMap = new TreeMap<>();
        m_ClassReqHoursMap = new TreeMap<>();
        m_RulesMap = new HashMap<>();
    }

    public Map<Integer, String> getSubjectMap() {
        return m_SubjectMap;
    }

    public void AddToSubjectMap(Integer i_Key, String i_Value) {
        m_SubjectMap.put(i_Key, i_Value);
    }

    public Map<Integer, Map<Integer, String>> getTeachersMap() {
        return m_TeachersMap;
    }

    public void AddToTeachersMap(Integer i_Key, Map<Integer, String> i_Value) {
        m_TeachersMap.put(i_Key, i_Value);
    }

    public Map<Integer, Map<Integer, String>> getClassesMap() {
        return m_ClassesMap;
    }

    public void AddToClassesMap(Integer i_Key, Map<Integer, String> i_Value) {
        m_ClassesMap.put(i_Key, i_Value);
    }

    public Map<Integer, Map<Integer, Integer>> getClassReqHoursMap() {
        return m_ClassReqHoursMap;
    }

    public void AddToReqHoursMap(Integer i_Key, Map<Integer, Integer> i_Value) {
        m_ClassReqHoursMap.put(i_Key, i_Value);
    }

    public Map<String, String> getRulesMap() {
        return m_RulesMap;
    }

    public void AddToRulesMap(String i_Key, String i_Value) {
        m_RulesMap.put(i_Key, i_Value);
    }

    public void Print()
    {
        System.out.println("File information -"+System.lineSeparator()+"The Subjects are:");
        m_SubjectMap.forEach((subjCode, subjName) -> System.out.println("Code:"+subjCode + " Name:" + subjName));
        System.out.println("The Teachers are:");
        for(Integer teacherID:m_TeachersMap.keySet())
        {
            Map<Integer, String> teacherSubjects = m_TeachersMap.get(teacherID);
            System.out.println("Teacher ID:"+teacherID+" The Subjects he teaches are:");
            teacherSubjects.forEach((subjCode,subjName)-> System.out.println("Code:"+subjCode + " Name:" + subjName));
        }
        System.out.println("The Classes are:");
        for(Integer classID:m_ClassesMap.keySet())
        {
            Map<Integer, String> classSubjects = m_ClassesMap.get(classID);
            Map<Integer, Integer> classRequirements = m_ClassReqHoursMap.get(classID);
            System.out.println("Class ID:"+classID+" The Subjects Students learn in this class are:");
            for(Integer subjID:classSubjects.keySet())
            {
                System.out.println("Subject ID:"+subjID+" subject name:"+classSubjects.get(subjID)
                +" Requirement hours:"+ classRequirements.get(subjID));
            }
        }
        System.out.println("The rules are: ");
        m_RulesMap.forEach((ruleName,ruleType)-> System.out.println("Name:"+ruleName+" Type:"+ruleType.toString()));
    }
}


