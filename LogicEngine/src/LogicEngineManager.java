import DataClasses.TimeTable;
import ParsedClasses.ETTDescriptor;
import ParsedClasses.ETTTimeTable;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class LogicEngineManager {
    private TimeTable m_TimeTable;



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
            m_TimeTable=new TimeTable(ettDescriptor.getETTTimeTable());
            System.out.println(m_TimeTable);
        }
        catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
