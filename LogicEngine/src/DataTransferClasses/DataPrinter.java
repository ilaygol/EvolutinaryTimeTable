package DataTransferClasses;

import AlgorithmClasses.Crossover;

import java.util.*;

public class DataPrinter {
    private Map<Integer, String> m_ID2SubjectMap;
    private Map<Integer, Map<Integer, String>> m_TeachersID2SubjMap;
    private Map<Integer, Map<Integer, String>> m_ClassesID2SubjMap;
    private Map<Integer, Map<Integer, Integer>> m_ClassID2ReqHoursMap;
    private Map<String, String> m_RulesNames2TypeMap;

    private Integer m_InitialPopulation;
    private SelectionData m_SelectionData;
    private CrossoverData m_CrossoverData;
    private List<MutationData> m_MutationsDataList;


    public Map<Integer, String> getID2SubjectMap() {
        return m_ID2SubjectMap;
    }
    public void setID2SubjectMap(Map<Integer, String> i_ID2SubjectMap) {
        this.m_ID2SubjectMap = i_ID2SubjectMap;
    }

    public Map<Integer, Map<Integer, String>> getTeachersID2SubjMap() {
        return m_TeachersID2SubjMap;
    }
    public void setTeachersID2SubjMap(Map<Integer, Map<Integer, String>> i_TeachersID2SubjMap) {
        this.m_TeachersID2SubjMap = i_TeachersID2SubjMap;
    }

    public Map<Integer, Map<Integer, String>> getClassesID2SubjMap() {
        return m_ClassesID2SubjMap;
    }
    public void setClassesID2SubjMap(Map<Integer, Map<Integer, String>> i_ClassesID2SubjMap) {
        this.m_ClassesID2SubjMap = i_ClassesID2SubjMap;
    }

    public Map<Integer, Map<Integer, Integer>> getClassID2ReqHoursMap() {
        return m_ClassID2ReqHoursMap;
    }
    public void setClassID2ReqHoursMap(Map<Integer, Map<Integer, Integer>> i_ClassID2ReqHoursMap) {
        this.m_ClassID2ReqHoursMap = i_ClassID2ReqHoursMap;
    }

    public Map<String, String> getRulesNames2TypeMap() {
        return m_RulesNames2TypeMap;
    }
    public void setRulesNames2TypeMap(Map<String, String> i_RulesNames2TypeMap) {
        this.m_RulesNames2TypeMap = i_RulesNames2TypeMap;
    }

    public Integer getInitialPopulation() {
        return m_InitialPopulation;
    }

    public void setInitialPopulation(Integer i_InitialPopulation) {
        this.m_InitialPopulation = i_InitialPopulation;
    }

    public SelectionData getSelectionData() {
        return m_SelectionData;
    }

    public void setSelectionData(SelectionData i_SelectionData) {
        this.m_SelectionData = i_SelectionData;
    }

    public CrossoverData getCrossoverData() {
        return m_CrossoverData;
    }

    public void setCrossoverData(CrossoverData i_CrossoverData) {
        this.m_CrossoverData = i_CrossoverData;
    }

    public List<MutationData> getMutationsDataList() {
        return m_MutationsDataList;
    }

    public void setMutationsDataList(List<MutationData> i_MutationsDataList) {
        this.m_MutationsDataList = i_MutationsDataList;
    }
}