import AlgorithmClasses.Descriptor;
import DataClasses.TimeTable;
import ParsedClasses.ETTDescriptor;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

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

    public int PrintFileData() {
        return 2;
    }

    public void LoadFile(String i_FileName) {
        try {
            File file=new File(i_FileName);
            JAXBContext jaxbContext = JAXBContext.newInstance("ParsedClasses");
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            ETTDescriptor ettDescriptor = (ETTDescriptor) jaxbUnmarshaller.unmarshal(file);
            m_Descriptor=new Descriptor(ettDescriptor);
        }
        catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
