import AlgorithmClasses.Descriptor;
import DataClasses.CheckValidData;
import DataClasses.Subject;
import DataClasses.Teacher;
import DataClasses.Teachers;
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
        //Subject map
        List<Subject> subjectsList = m_Descriptor.getTimeTable().getSubjects().getSubjectsList();
        for(Subject s:subjectsList)
        {
            dataPrinter.AddToSubjectMap(s.getId(),s.getFullName().toString());
        }

        //Teachers Map
        List<Teacher> teachersList = m_Descriptor.getTimeTable().getTeachers().getTeachersList();
        for(Teacher t:teachersList)
        {
            Map<Integer,String> subjectsMap=new TreeMap<>();
            List<Integer> subjectsIDList = t.getSubjectsIDList();
            for(Integer i:subjectsIDList)
            {
                subjectsMap.put(i,dataPrinter.getSubjectMap().get(i));
            }
            dataPrinter.AddToTeachersMap(t.getId(),subjectsMap);
        }

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
