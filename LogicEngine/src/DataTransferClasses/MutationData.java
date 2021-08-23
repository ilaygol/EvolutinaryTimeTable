package DataTransferClasses;

import AlgorithmClasses.Mutation;
import AlgorithmClasses.eMutation;

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

    public void setProbability(double i_Probability) {
        m_Probability = i_Probability;
    }

    public void setTupples(Integer i_Tupples) {
        m_Tupples = i_Tupples;
    }

    public void setComponent(Character i_Component) {
        m_Component = i_Component;
    }

    @Override
    public String toString() {
        eMutation eType=eMutation.valueOf(m_Name.toUpperCase());
        return eType.toString(m_Tupples,m_Component);
    }
}
