import AlgorithmClasses.Descriptor;
import DataClasses.FileInputDataClasses.*;
import DataClasses.AlgorithmData.*;
import DataTransferClasses.DataPrinter;
import DataTransferClasses.EvolutionEngineData;
import ParsedClasses.ETTDescriptor;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;


public class LogicEngineManager {
    private Descriptor m_Descriptor;
    private EvolutionEngineData m_EvolutionEngineData;



    public int PrintAlgorithmProcess() {
        return 5;
    }

    public int PrintBestSolution() {
        return 4;
    }

    public void ActivateAlgorithm(Integer i_AmountOfGeneration,Integer i_PrintingReq,Integer i_ReqFitness) {
        m_Descriptor.getEvolutionEngine().setNumOfGenerations(i_AmountOfGeneration);
        m_Descriptor.getEvolutionEngine().setPrintingReq(i_PrintingReq);
        m_Descriptor.getEvolutionEngine().setReqFitness(i_ReqFitness);
        AmountOfObjectsCalc amountOfObjects =getAmountOfData();
        m_EvolutionEngineData=m_Descriptor.getEvolutionEngine().activateAlgorithm(m_Descriptor.getTimeTable(),amountOfObjects);
    }

    public DataPrinter PrintFileData() {
        DataPrinter dataPrinter=new DataPrinter();
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
        }
        catch (JAXBException e) {
            throw new JAXBException("Error: an error with unmarshalling the file");
        }
    }


    private AmountOfObjectsCalc getAmountOfData()
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
}
