package DataClasses;

import java.util.ArrayList;
import java.util.List;

public class Rules {
    private List<Rule> m_RulesList;
    private Integer m_HardRulesWeight;

    public Rules(Integer i_HardRulesWeight)
    {
        m_RulesList=new ArrayList<>();
        m_HardRulesWeight=i_HardRulesWeight;
    }
}
