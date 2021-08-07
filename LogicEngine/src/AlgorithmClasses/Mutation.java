
package AlgorithmClasses;

import DataClasses.AlgorithmData.AmountOfObjectsCalc;
import DataClasses.AlgorithmData.Generation;
import ParsedClasses.ETTMutation;

import java.util.*;

public class Mutation {

    private double m_Probability;
    private String m_Name;
    private List<String> m_Configuration;
    private Integer m_Tupples;
    private Character m_Char;
    private eMutation m_eType;
    private Random m_Roller;


    public Mutation(ETTMutation i_ETTMutation)
    {
        m_Probability=i_ETTMutation.getProbability();
        m_Name=i_ETTMutation.getName();
        m_Configuration=i_ETTMutation.getConfiguration();
        extractConfiguration();
        m_eType=eMutation.valueOf(m_Name.toUpperCase(Locale.ROOT));
        m_Roller=new Random();
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

    public Integer getMaxTupples() {
        return m_Tupples;
    }

    public Character getChar() {
        return m_Char;
    }

    private void extractConfiguration() {
        Scanner scanner = new Scanner(m_Configuration.toString()).useDelimiter("[^0-9]+");
        m_Tupples = scanner.nextInt();
        int size = m_Configuration.get(0).length();
        m_Char = m_Configuration.get(0).charAt(size - 1);
        scanner.close();
    }

    public void activateMutation(Generation i_Generation, AmountOfObjectsCalc i_AmountOfObj) {
        m_eType.activate(m_Tupples, i_Generation, i_AmountOfObj, m_Char, m_Roller);
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
