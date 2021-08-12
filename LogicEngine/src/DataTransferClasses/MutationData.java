package DataTransferClasses;

import AlgorithmClasses.Mutation;

import java.util.List;

public class MutationData {
    private double m_Probability;
    private String m_Name;
    private String m_Configuration;

    public MutationData(Mutation i_Mutation)
    {
        m_Name=i_Mutation.getName();
        m_Probability=i_Mutation.getProbability();
        m_Configuration= i_Mutation.getConfiguration();
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
}
