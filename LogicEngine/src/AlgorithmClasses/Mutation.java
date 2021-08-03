
package AlgorithmClasses;

import ParsedClasses.ETTMutation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Mutation {

    private double m_Probability;
    private String m_Name;
    private List<String> m_Configuration;
    private Integer m_MaxTupples;
    private Character m_Char;
    private eMutation m_eType;


    public Mutation(ETTMutation i_ETTMutation)
    {
        m_Probability=i_ETTMutation.getProbability();
        m_Name=i_ETTMutation.getName();
        m_Configuration=i_ETTMutation.getConfiguration();
    }



    public double getProbability() {
        return m_Probability;
    }

    public String getName() {
        return m_Name;
    }

    public List<String> getConfiguration() {
        return m_Configuration;
    }


    private void extractConfiguration() {
        Scanner scanner = new Scanner(m_Configuration.toString()).useDelimiter("[^0-9]+");
        m_MaxTupples = scanner.nextInt();
        int size = m_Configuration.get(0).length();
        m_Char = m_Configuration.get(0).charAt(size - 1);
        scanner.close();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mutation mutation = (Mutation) o;
        return m_Name.equals(mutation.m_Name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_Name);
    }
}
