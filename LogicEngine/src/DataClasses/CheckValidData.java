package DataClasses;

import ParsedClasses.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


public class CheckValidData {
    private ETTDescriptor m_Descriptor;
    private Set<Integer> m_SubjIDSet;
    private Set<Integer> m_TeachersIDSet;
    private Set<Integer> m_ClassIDSet;

    //constructor
    public CheckValidData(ETTDescriptor i_descriptor)
    {
        m_Descriptor=i_descriptor;
        m_SubjIDSet=new TreeSet<>();
        m_TeachersIDSet=new TreeSet<>();
        m_ClassIDSet=new TreeSet<>();
    }


    //main checking method
    public void checkFile() throws Exception{
        checkSubjectsId();
        checkTeachersId();
        checkClassId();
        checkRuleAppearOnce();




    }


    //a method to check subjects id (if they appear in order and no Duplication)
    private void checkSubjectsId() throws Exception {
        List<ETTSubject> subjList= m_Descriptor.getETTTimeTable().getETTSubjects().getETTSubject();
        int subListLength=subjList.toArray().length;

        for(ETTSubject s:subjList) {
            if(m_SubjIDSet.contains(s.getId()))
                throw new Exception("Error: There is more than one Subject with same id!");
            m_SubjIDSet.add(s.getId());
        }

        for(int i=1;i<=subListLength;i++) {
            if (!m_SubjIDSet.contains(i))
                throw new Exception("Error: Subject's ID doesnt appear in serial order!");
        }

    }

    //a method to check teachers id (if they appear in order and no Duplication)
    private void checkTeachersId() throws Exception {
        List<ETTTeacher> teachersList= m_Descriptor.getETTTimeTable().getETTTeachers().getETTTeacher();
        int teachersListLength=teachersList.toArray().length;

        for(ETTTeacher t:teachersList) {
            if(m_TeachersIDSet.contains(t.getId()))
                throw new Exception("Error: There is more than one Teacher with same id!");
            m_TeachersIDSet.add(t.getId());
        }

        for(int i=1;i<=teachersListLength;i++) {
            if (!m_TeachersIDSet.contains(i))
                throw new Exception("Error: Teacher's ID doesnt appear in serial order!");
        }
    }

    //a method to check class id (if they appear in order and no Duplication)
    private void checkClassId() throws Exception {
        List<ETTClass> classList= m_Descriptor.getETTTimeTable().getETTClasses().getETTClass();
        int classListLength=classList.toArray().length;

        for(ETTClass c:classList) {
            if(m_ClassIDSet.contains(c.getId()))
                throw new Exception("Error: There is more than one Class with same id!");
            m_ClassIDSet.add(c.getId());
        }

        for(int i=1;i<=classListLength;i++) {
            if (!m_ClassIDSet.contains(i))
                throw new Exception("Error: Classes ID doesnt appear in serial order!");
        }
    }

    //a method to check if same rule doesnt appear Twice
    private void checkRuleAppearOnce() throws Exception
    {
        List<ETTRule> ruleList= m_Descriptor.getETTTimeTable().getETTRules().getETTRule();
        Set<ETTRule> rulesSet=new HashSet<>();
        for(ETTRule r:ruleList) {
            if(rulesSet.contains(r))
                throw new Exception("Error: Same rule appear twice!");
            rulesSet.add(r);
        }

    }






}
