
package AlgorithmClasses;

import DataClasses.AlgorithmData.AmountOfObjectsCalc;
import DataClasses.AlgorithmData.Generation;
import DataTransferClasses.MutationData;
import ParsedClasses.ETTMutation;

import java.util.*;

public class Mutation {

    private String m_Name;
    private String m_Configuration;
    private Integer m_Tupples;
    private Character m_Char;
    private double m_Probability;
    private eMutation m_eType;
    private Random m_Roller;

    public Mutation(ETTMutation i_ETTMutation)
    {
        m_Name=i_ETTMutation.getName();
        m_Configuration=i_ETTMutation.getConfiguration();
        extractConfiguration();
        m_Probability=i_ETTMutation.getProbability();
        m_eType=eMutation.valueOf(m_Name.toUpperCase());
        m_Roller=new Random();
    }

    public Mutation(MutationData i_MutationData)
    {
        m_Name=i_MutationData.getName();
        m_Configuration="";
        m_Tupples=i_MutationData.getTupples();
        m_Char=i_MutationData.getComponent();
        m_Probability=i_MutationData.getProbability();
        m_eType=eMutation.valueOf(m_Name.toUpperCase());
        m_Roller=new Random();
    }

    public double getProbability() {
        return m_Probability;
    }

    public String getName() {
        return m_Name;
    }

    public String getConfiguration() {
        return m_Configuration;
    }

    public eMutation getEType() { return m_eType; }

    public Integer getMaxTupples() {
        return m_Tupples;
    }

    public Character getChar() {
        return m_Char;
    }

    public void setTupples(Integer i_Tupples) {
        m_Tupples = i_Tupples;
    }

    public void setChar(Character i_Char) {
        m_Char = i_Char;
    }

    public void setProbability(double i_Probability) {
        m_Probability = i_Probability;
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

    private void extractConfiguration() {
        if(m_Configuration!=null) {
            Scanner scanner = new Scanner(m_Configuration.toString()).useDelimiter("[^0-9]+");
            m_Tupples = scanner.nextInt();
            int size = m_Configuration.length();
            if(m_Name.toUpperCase().equals("FLIPPING")) {
                m_Char = m_Configuration.charAt(size - 1);
            }
            else if(m_Name.toUpperCase().equals("SIZER")) {
                m_Char = ' ';
            }

            scanner.close();
        }
    }
}
