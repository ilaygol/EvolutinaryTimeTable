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
            Map<Integer,String> teachersSubjectsMap=new TreeMap<>();
            List<Integer> teacherSubjectsIDList = t.getSubjectsIDList();
            for(Integer i:teacherSubjectsIDList)
            {
                teachersSubjectsMap.put(i,dataPrinter.getSubjectMap().get(i));
            }
            dataPrinter.AddToTeachersMap(t.getId(),teachersSubjectsMap);
        }

        //Class map
        List<Clazz> classesList = m_Descriptor.getTimeTable().getClazzes().getClassesList();
        for(Clazz c:classesList)
        {
            Map<Integer,String> classesSubjectsMap=new TreeMap<>();
            Map<Integer,Integer> classesReqHoursMap=new TreeMap<>();
            List<Study> classesSubjectsIDList=c.getRequirements().getStudyList();
            for(Study i:classesSubjectsIDList)
            {
                classesSubjectsMap.put(i.getSubjectId(),dataPrinter.getSubjectMap().get(i.getSubjectId()));
                classesReqHoursMap.put(i.getSubjectId(),i.getHours());
            }
            dataPrinter.AddToClassesMap(c.getId(),classesSubjectsMap);
            dataPrinter.AddToReqHoursMap(c.getId(),classesReqHoursMap);
        }

        //Rules map
        List<Rule> rulesList=m_Descriptor.getTimeTable().getRules().getRulesList();
        for(Rule r:rulesList)
        {
            dataPrinter.AddToRulesMap(r.getId().toString(),r.getType().toString());
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
