import AlgorithmClasses.Descriptor;
import DataClasses.FileInputDataClasses.*;
import DataTransferClasses.DataPrinter;
import ParsedClasses.ETTDescriptor;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;


public class LogicEngineManager {
    private Descriptor m_Descriptor;



    public int PrintAlgorithmProcess() {
        return 5;
    }

    public int PrintBestSolution() {
        return 4;
    }

    public int ActivateAlgorithm() {
        return 3;
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
}
