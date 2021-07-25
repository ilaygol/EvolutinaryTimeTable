
package AlgorithmClasses;

import ParsedClasses.ETTMutation;

import java.util.ArrayList;
import java.util.List;

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

}
