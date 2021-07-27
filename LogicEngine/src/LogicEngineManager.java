import AlgorithmClasses.Descriptor;
import DataClasses.*;
import DataTransferClasses.DataPrinter;
import ParsedClasses.ETTDescriptor;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
        dataPrinter.setID2SubjectMap(m_Descriptor.getTimeTable().getSubjects().getID2SubjNameMap());
        //building Teachers Map
        dataPrinter.setTeachersID2SubjMap(m_Descriptor.getTimeTable().getTeachers().getTeachersID2SubjMap(dataPrinter.getID2SubjectMap()));

        //building Class map
        dataPrinter.setClassesID2SubjMap(m_Descriptor.getTimeTable().getClazzes().getClassesID2SubjMap(dataPrinter.getID2SubjectMap()));
        dataPrinter.setClassID2ReqHoursMap(m_Descriptor.getTimeTable().getClazzes().getClassesID2ReqSubjHoursMap());
        //building Rules map
        dataPrinter.setRulesNames2TypeMap(m_Descriptor.getTimeTable().getRules().getRulesNames2TypeMap());


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
