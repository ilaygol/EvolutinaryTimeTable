package DataClasses;

import ParsedClasses.ETTClass;
import ParsedClasses.ETTClasses;

import java.util.ArrayList;
import java.util.List;

public class Clazzes {
    private List<Clazz> m_ClassesList;

    public Clazzes(ETTClasses i_ETTClasses)
    {
        m_ClassesList=new ArrayList<>();
        List<ETTClass> ettClasses = i_ETTClasses.getETTClass();
        for(ETTClass ettClass:ettClasses)
        {
            m_ClassesList.add(new Clazz(ettClass));
        }
    }

    @Override
    public String toString() {
        return "Clazzes{" +
                "m_ClassesList=" + m_ClassesList +
                '}';
    }

    public List<Clazz> getClassesList() {
        return m_ClassesList;
    }
}
