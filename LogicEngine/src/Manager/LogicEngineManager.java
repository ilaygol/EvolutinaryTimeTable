package Manager;

import AlgorithmClasses.*;
import DataClasses.FileInputDataClasses.*;
import DataClasses.AlgorithmData.*;
import DataTransferClasses.*;
import ParsedClasses.ETTDescriptor;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;


public class LogicEngineManager {
    private Descriptor m_Descriptor;
    private EvolutionEngineData m_EvolutionEngineData;
    private AmountOfObjectsCalc m_MaxAmountOfObjects;
    private Integer m_ProblemIndex;
    private boolean m_IsFileLoaded=false;
    private boolean m_IsAlgoActivated=false;

    public LogicEngineManager() { m_ProblemIndex=0; }

    public LogicEngineManager(LogicEngineManager i_LogicEngineManager)
    {
        m_Descriptor=new Descriptor(i_LogicEngineManager.getDescriptor());
    }
    public Map<Integer,Integer> PrintAlgorithmProcess() {
        if(m_IsFileLoaded) {
            if(m_IsAlgoActivated) {
                return m_EvolutionEngineData.getGeneration2BestFitnessMap();
            }
            else {
                throw new RuntimeException("ERROR: Please activate the algorithm first");
            }
        }
        else {
            throw new RuntimeException("ERROR: Please load a file, then activate the algorithm before choosing this option");
        }
    }

    public void ActivateAlgorithm(Integer i_ReqGenerations, Integer i_PrintingReq,Integer i_ReqFitness,Integer i_ReqTimeInMinutes, Consumer<ProgressData> i_ProgressDataConsumer,  Collection<eStoppingCondition> i_StopConditions) {
        if(m_IsFileLoaded)
        {
            m_EvolutionEngineData=new EvolutionEngineData();
            m_Descriptor.getEvolutionEngine().setNumOfGenerations(i_ReqGenerations);
            m_Descriptor.getEvolutionEngine().setPrintingReq(i_PrintingReq);
            m_Descriptor.getEvolutionEngine().setReqFitness(i_ReqFitness);
            m_Descriptor.getEvolutionEngine().setReqMinutes(i_ReqTimeInMinutes);
            m_IsAlgoActivated=true;
            m_Descriptor.getEvolutionEngine().activateAlgorithm(m_Descriptor.getTimeTable(),m_MaxAmountOfObjects,m_EvolutionEngineData,i_ProgressDataConsumer,i_StopConditions);
        }
        else {
            throw new RuntimeException("ERROR: No file has been loaded, Please load a file before choosing this option.");
        }
    }

    public void ActivateAlgorithm(String i_ReqGenerations, String i_PrintingReq,String i_ReqFitness,String i_ReqTimeInMinutes, Consumer<ProgressData> i_ProgressDataConsumer,  Collection<eStoppingCondition> i_StopConditions) {
        if(m_IsFileLoaded)
        {
            checkArguments(i_ReqGenerations,i_PrintingReq,i_ReqFitness,i_ReqTimeInMinutes);
            m_EvolutionEngineData=new EvolutionEngineData();
            m_Descriptor.getEvolutionEngine().setNumOfGenerations(Integer.parseInt(i_ReqGenerations));
            m_Descriptor.getEvolutionEngine().setPrintingReq(Integer.parseInt(i_PrintingReq));
            m_Descriptor.getEvolutionEngine().setReqFitness(Integer.parseInt(i_ReqFitness));
            m_Descriptor.getEvolutionEngine().setReqMinutes(Integer.parseInt(i_ReqTimeInMinutes));
            m_IsAlgoActivated=true;
            m_Descriptor.getEvolutionEngine().activateAlgorithm(m_Descriptor.getTimeTable(),m_MaxAmountOfObjects,m_EvolutionEngineData,i_ProgressDataConsumer,i_StopConditions);
        }
        else {
            throw new RuntimeException("ERROR: No file has been loaded, Please load a file before choosing this option.");
        }
    }

    public void resumeAlgo()
    {
        m_Descriptor.getEvolutionEngine().resumeAlgo();
    }

    private void checkArguments(String i_reqGenerations, String i_printingReq, String i_reqFitness, String i_reqTimeInMinutes) {
        try {
            if(i_reqGenerations!=null) {
                int reqGeneraions = Integer.parseInt(i_reqGenerations);
                if (reqGeneraions <= 0)
                    throw new RuntimeException("Error, Number of generations must be positive");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error, Generations to make must be a number.");
        }
        try {
            if (i_printingReq != null) {
                int printingReq = Integer.parseInt(i_printingReq);
                if (printingReq <= 0)
                    throw new RuntimeException("Error, Show every must be a positive number.");
                if (printingReq > Integer.parseInt(i_reqGenerations))
                    throw new RuntimeException("Error: Show every parameter cant be bigger than Generations number");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error, Show Every must be a number.");
        }
        try {
            int reqFitness = Integer.parseInt(i_reqFitness);
            if(reqFitness<0 || reqFitness>100)
                throw new RuntimeException("Error: Req fitness must be between 1-100");
        } catch (Exception e) {
            throw new RuntimeException("Error, Req fitness must be a number.");
        }
        try {
            if(i_reqFitness!=null) {
                int reqTime = Integer.parseInt(i_reqTimeInMinutes);
                if (reqTime <= 0)
                    throw new RuntimeException("Error, Req time must be a positive number.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error, Req time must be an Integer.");
        }

    }

    public void LoadFile(File i_File) throws  JAXBException {
        try {

            JAXBContext jaxbContext = JAXBContext.newInstance("ParsedClasses");
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            ETTDescriptor ettDescriptor = (ETTDescriptor) jaxbUnmarshaller.unmarshal(i_File);
            CheckValidData checker=new CheckValidData(ettDescriptor);
            checker.checkFile();
            m_Descriptor=new Descriptor(ettDescriptor);
            m_MaxAmountOfObjects =getAmountOfData();
            m_IsFileLoaded=true;
            m_IsAlgoActivated=false;
        }
        catch (JAXBException e) {
            throw new JAXBException("An error with unmarshalling the file");
        }
    }

    public void LoadFile(InputStream i_InputStream) throws  JAXBException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance("ParsedClasses");
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            ETTDescriptor ettDescriptor = (ETTDescriptor) jaxbUnmarshaller.unmarshal(i_InputStream);
            CheckValidData checker=new CheckValidData(ettDescriptor);
            checker.checkFile();
            m_Descriptor=new Descriptor(ettDescriptor);
            m_MaxAmountOfObjects =getAmountOfData();
            m_IsFileLoaded=true;
            m_IsAlgoActivated=false;
        }
        catch (JAXBException e) {
            throw new JAXBException("Please choose XML file");
        }
    }

    public BestSolutionsData getBestSolutionData() {
        if(m_IsFileLoaded) {
            if(m_IsAlgoActivated) {
                return m_EvolutionEngineData.getBestSolutionData();
            }
            else {
                throw new RuntimeException("ERROR: Please activate the algorithm first");
            }
        }
        else {
            throw new RuntimeException("ERROR: Please load a file, then activate the algorithm before choosing this option");
        }
    }

    public Descriptor getDescriptor() {
        return m_Descriptor;
    }

    public Integer getProblemIndex() {
        return m_ProblemIndex;
    }

    public DataPrinter getFileData() {
        if(m_IsFileLoaded) {
            DataPrinter dataPrinter = new DataPrinter();
            //building Subject map
            dataPrinter.SetSubjectsSet(m_Descriptor.getTimeTable().getSubjects().getSubjectSet());
            //building Teachers Map

            dataPrinter.setTeacherID2SubjectsMap(m_Descriptor.getTimeTable().getTeachers().getTeacherID2SubjectsMap(dataPrinter.getSubjectsSet()));

            //building Class map
            dataPrinter.setClassesID2SubjMap(m_Descriptor.getTimeTable().getClazzes().getClassID2SubjectsMap(dataPrinter.getSubjectsSet()));
            //building Rules map
            dataPrinter.setRulesNames2TypeMap(m_Descriptor.getTimeTable().getRules().getRulesNames2TypeMap());

            dataPrinter.setInitialPopulation(m_Descriptor.getEvolutionEngine().getInitialPopulation());
            dataPrinter.setSelectionData(m_Descriptor.getEvolutionEngine().getSelection().getSelectionData());
            dataPrinter.setCrossoverData(m_Descriptor.getEvolutionEngine().getCrossover().getCrossoverData());
            dataPrinter.setMutationsDataList(m_Descriptor.getEvolutionEngine().getMutations().getMutationsDataList());

            return dataPrinter;
        }
        else
        {
            throw new RuntimeException("ERROR: No file has been loaded, Please load a file before choosing this option");
        }
    }

    public WebFileData getWebFileData() {
        WebFileData webFileData=new WebFileData();
        webFileData.setSubjectsData(m_Descriptor.getTimeTable().getSubjects().getSubjectSet());
        webFileData.setTeacherData(m_Descriptor.getTimeTable().getTeachers().getTeachersData(m_Descriptor.getTimeTable().getSubjects()));
        webFileData.setClassData(m_Descriptor.getTimeTable().getClazzes().getClassesData(m_Descriptor.getTimeTable().getSubjects()));
        webFileData.setRuleData(m_Descriptor.getTimeTable().getRules().getRulesData());
        return webFileData;
    }

    public List<MutationData> getMutationDataList()
    {
        return m_Descriptor.getEvolutionEngine().getMutations().getMutationsDataList();
    }

    public AmountOfObjectsCalc getAmountOfData() {
        TimeTable table= m_Descriptor.getTimeTable();
        Integer lessonInSolution=0,hardRolesCount=0,softRolesCount=0;
        List<Clazz> classesList = table.getClazzes().getClassesList();
        for(Clazz c:classesList)
        {
            int sum=c.getRequirements().getStudyList().stream().mapToInt(study-> study.getHours()).sum();
            lessonInSolution+=sum;
        }
        List<Rule> rulesList=table.getRules().getRulesList();
        for(Rule rule:rulesList)
        {
            if(rule.getType().equals(Rule.eType.HARD)) {
                hardRolesCount++;
            }
            else {
                softRolesCount++;
            }
        }

        AmountOfObjectsCalc maxValues=new AmountOfObjectsCalc(table.getDays(), table.getHours(),
                table.getTeachers().getTeachersList().size(),
                table.getClazzes().getClassesList().size(),
                table.getSubjects().getSubjectsList().size(),
                hardRolesCount,softRolesCount,
                lessonInSolution);
        return maxValues;
    }

    public boolean getIsFileLoaded() {
        return m_IsFileLoaded;
    }

    public boolean getIsAlgoActivated() {
        return m_IsAlgoActivated;
    }

    public Boolean getStopBoolean()
    {
        return m_Descriptor.getEvolutionEngine().getStopBoolean();
    }

    public String getTeacherNameById(Integer i_ID)
    {
        return m_Descriptor.getTimeTable().getTeachers().getTeacherNameById(i_ID);
    }

    public String getClassNameById(Integer i_ID)
    {
        return m_Descriptor.getTimeTable().getClazzes().getClassNameById(i_ID);
    }

    public String getSubjectNameById(Integer i_ID)
    {
        return m_Descriptor.getTimeTable().getSubjects().getSubjectNameById(i_ID);
    }

    public void setProblemIndex(Integer i_ProblemIndex) {
        this.m_ProblemIndex = i_ProblemIndex;
    }

    public void updateAlgoData(DataPrinter i_DataPrinter)
    {
        setNewCrossover(i_DataPrinter.getCrossoverData());
        setNewSelection(i_DataPrinter.getSelectionData());
        setNewMutations(i_DataPrinter.getMutationsDataList());
    }

    private void setNewCrossover(CrossoverData i_CrossoverData)
    {
        Crossover crossover=new Crossover(i_CrossoverData);
        m_Descriptor.getEvolutionEngine().setCrossover(crossover);
    }

    private void setNewSelection(SelectionData i_SelectionData)
    {
        Selection selection=new Selection(i_SelectionData);
        m_Descriptor.getEvolutionEngine().setSelection(selection);
    }

    private void setNewMutations(List<MutationData> i_MutationsDataList)
    {
        Mutations mutations=new Mutations(i_MutationsDataList);
        m_Descriptor.getEvolutionEngine().setMutations(mutations);
    }

    public Integer getMaxLessons()
    {
        if(m_MaxAmountOfObjects==null)
            return 0;
        else
            return m_MaxAmountOfObjects.getMaxAmountOfLessons();
    }

    public void setStopBoolean(Boolean i_Boolean)
    {
        m_Descriptor.getEvolutionEngine().setStopBoolean(i_Boolean);
    }

    public void addNewMutationToList(String i_Name,String i_Tupples,String i_Char,String i_Probability)
    {
        m_Descriptor.getEvolutionEngine().getMutations().createAndAddMutationToList(i_Name,i_Tupples,i_Char,i_Probability);
    }

    public void deleteMutationByIndex(Integer i_MutationIndex)
    {
        m_Descriptor.getEvolutionEngine().getMutations().deleteMutationByIndex(i_MutationIndex);
    }



}
