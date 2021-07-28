
package AlgorithmClasses;

import ParsedClasses.ETTMutation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Mutation {

    private double m_Probability;
    private String m_Name;
    private List<String> m_Configuration;

    public Mutation(ETTMutation i_ETTMutation)
    {
        m_Probability=i_ETTMutation.getProbability();
        m_Name=i_ETTMutation.getName();
        m_Configuration=i_ETTMutation.getConfiguration();
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

    public double getProbability() {
        return m_Probability;
    }

    public String getName() {
        return m_Name;
    }

    public List<String> getConfiguration() {
        return m_Configuration;
    }
}
