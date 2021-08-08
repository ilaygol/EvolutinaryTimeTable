import AlgorithmClasses.Descriptor;
import DataClasses.FileInputDataClasses.*;
import DataClasses.AlgorithmData.*;
import DataTransferClasses.BestSolutionsData;
import DataTransferClasses.DataPrinter;
import DataTransferClasses.EvolutionEngineData;
import DataTransferClasses.ProgressData;
import ParsedClasses.ETTDescriptor;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;


public class LogicEngineManager {
    private Descriptor m_Descriptor;
    private EvolutionEngineData m_EvolutionEngineData;
    private boolean m_IsFileLoaded=false;
    private boolean m_IsAlgoActivated=false;


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

    public void ActivateAlgorithm(Integer i_AmountOfGeneration,Integer i_PrintingReq, Consumer<ProgressData> i_ProgressDataConsumer,Integer i_ReqFitness) {
        if(m_IsFileLoaded)
        {
            m_Descriptor.getEvolutionEngine().setNumOfGenerations(i_AmountOfGeneration);
            m_Descriptor.getEvolutionEngine().setPrintingReq(i_PrintingReq);
            m_Descriptor.getEvolutionEngine().setReqFitness(i_ReqFitness);
            AmountOfObjectsCalc amountOfObjects =getAmountOfData();
            m_EvolutionEngineData=m_Descriptor.getEvolutionEngine().activateAlgorithm(m_Descriptor.getTimeTable(),amountOfObjects,i_ProgressDataConsumer);
            m_IsAlgoActivated=true;
        }
        else {
            throw new RuntimeException("ERROR: No file has been loaded, Please load a file before choosing this option.");
        }
    }

    public DataPrinter PrintFileData() {
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

    public void LoadFile(String i_FileName) throws FileNotFoundException, JAXBException {
        try {
            File file=new File(i_FileName);
            if (!file.exists())
                throw new FileNotFoundException("Error: the file has not found");
            JAXBContext jaxbContext = JAXBContext.newInstance("ParsedClasses");
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            ETTDescriptor ettDescriptor = (ETTDescriptor) jaxbUnmarshaller.unmarshal(file);
            CheckValidData checker=new CheckValidData(ettDescriptor);
            checker.checkFile();
            m_Descriptor=new Descriptor(ettDescriptor);
            m_IsFileLoaded=true;
        }
        catch (JAXBException e) {
            throw new JAXBException("Error: an error with unmarshalling the file");
        }
    }


    public AmountOfObjectsCalc getAmountOfData()
    {
        TimeTable table= m_Descriptor.getTimeTable();
        Integer lessonInSolution=0;
        List<Clazz> classesList = table.getClazzes().getClassesList();
        for(Clazz c:classesList)
        {
            int sum=c.getRequirements().getStudyList().stream().mapToInt(study-> study.getHours()).sum();
            lessonInSolution+=sum;
        }

        AmountOfObjectsCalc maxValues=new AmountOfObjectsCalc(table.getDays(), table.getHours(),
                table.getTeachers().getTeachersList().size(),
                table.getSubjects().getSubjectsList().size(),
                table.getSubjects().getSubjectsList().size(),
                lessonInSolution);

        return maxValues;
    }

    public boolean getIsFileLoaded() {
        return m_IsFileLoaded;
    }

    public boolean getIsAlgoActivated() {
        return m_IsAlgoActivated;
    }
}
