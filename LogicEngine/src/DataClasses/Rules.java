package DataClasses;

import ParsedClasses.ETTRule;
import ParsedClasses.ETTRules;

import java.util.ArrayList;
import java.util.List;

public class Rules {
    private List<Rule> m_RulesList;
    private Integer m_HardRulesWeight;

    public Rules(ETTRules i_ETTRules)
    {
        m_HardRulesWeight=i_ETTRules.getHardRulesWeight();
        m_RulesList=new ArrayList<>();
        List<ETTRule> ettRules = i_ETTRules.getETTRule();
        for(ETTRule rule:ettRules)
        {
            m_RulesList.add(new Rule(rule));
        }
    }

    @Override
    public String toString() {
        return "Rules{" +
                "m_RulesList=" + m_RulesList +
                ", m_HardRulesWeight=" + m_HardRulesWeight +
                '}';
    }
}
