package DataTransferClasses;

import AlgorithmClasses.Mutation;

import java.util.List;

public class MutationData {
    private double m_Probability;
    private String m_Name;
    private Integer m_Tupples;
    private Character m_Component;

    public MutationData(Mutation i_Mutation)
    {
        m_Name=i_Mutation.getName();
        m_Probability=i_Mutation.getProbability();
        m_Tupples=i_Mutation.getMaxTupples();
        m_Component=i_Mutation.getChar();
    }

    public double getProbability() {
        return m_Probability;
    }

    public String getName() {
        return m_Name;
    }

    public Integer getTupples() {
        return m_Tupples;
    }

    public Character getComponent() {
        return m_Component;
    }

    @Override
    public String toString() {
        return "Name="+m_Name+", Tupples="+m_Tupples+" ("+m_Component+")";
    }
}
