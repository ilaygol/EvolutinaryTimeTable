package DataClasses;

import ParsedClasses.*;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


public class CheckValidData {
    private ETTDescriptor m_Descriptor;
    private Set<Integer> checkingSet;


    //constructor
    public CheckValidData(ETTDescriptor i_descriptor)
    {
        m_Descriptor=i_descriptor;
        checkingSet=new TreeSet<>();
    }


    //main checking method
    public void checkFile() throws Exception{
        checkSubjectsId();
        checkTeachersId();
        checkClassId();



    }


    //a method to check subjects id (if they appear in order and no Duplication)
    private void checkSubjectsId() throws Exception {
        List<ETTSubject> subjList= m_Descriptor.getETTTimeTable().getETTSubjects().getETTSubject();
        int subListLength=subjList.toArray().length;

        for(ETTSubject s:subjList) {
            if(checkingSet.contains(s.getId()))
                throw new Exception("Error: There is more than one Subject with same id!");
            checkingSet.add(s.getId());
        }

        for(int i=1;i<=subListLength;i++) {
            if (!checkingSet.contains(i))
                throw new Exception("Error: Subject's ID doesnt appear in serial order!");
        }
        checkingSet.clear();

    }

    //a method to check teachers id (if they appear in order and no Duplication)
    private void checkTeachersId() throws Exception {
        List<ETTTeacher> teachersList= m_Descriptor.getETTTimeTable().getETTTeachers().getETTTeacher();
        int teachersListLength=teachersList.toArray().length;

        for(ETTTeacher t:teachersList) {
            if(checkingSet.contains(t.getId()))
                throw new Exception("Error: There is more than one Teacher with same id!");
            checkingSet.add(t.getId());
        }

        for(int i=1;i<=teachersListLength;i++) {
            if (!checkingSet.contains(i))
                throw new Exception("Error: Teacher's ID doesnt appear in serial order!");
        }

        checkingSet.clear();
    }

    //a method to check class id (if they appear in order and no Duplication)
    private void checkClassId() throws Exception {
        List<ETTClass> classList= m_Descriptor.getETTTimeTable().getETTClasses().getETTClass();
        int classListLength=classList.toArray().length;

        for(ETTClass c:classList) {
            if(checkingSet.contains(c.getId()))
                throw new Exception("Error: There is more than one Class with same id!");
            checkingSet.add(c.getId());
        }

        for(int i=1;i<=classListLength;i++) {
            if (!checkingSet.contains(i))
                throw new Exception("Error: Classes ID doesnt appear in serial order!");
        }

        checkingSet.clear();
    }






}
