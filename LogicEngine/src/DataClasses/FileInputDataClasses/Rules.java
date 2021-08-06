package DataClasses.FileInputDataClasses;

import DataClasses.AlgorithmData.Generation;
import DataClasses.AlgorithmData.Parent;
import ParsedClasses.ETTRule;
import ParsedClasses.ETTRules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<Rule> getRulesList() {
        return m_RulesList;
    }
    public Map<String, String> getRulesNames2TypeMap(){
        Map<String,String> retMap=new HashMap<>();
        for(Rule r:m_RulesList)
        {
            retMap.put(r.getId().toString(),r.getType().toString());
        }
        return retMap;
    }
    public Integer getHardRulesWeight() {
        return m_HardRulesWeight;
    }

    public void calculateFitnesses(Generation i_Generation, TimeTable i_TimeTable)
    {
        List<Parent> parentsList = i_Generation.getParentsList();
        for(Parent parent:parentsList)
        {
            if(parent.getFitness()==-1)
            {
                List<Integer> hardRulesScores=new ArrayList<>();
                List<Integer> softRulesScores=new ArrayList<>();
                for(Rule rule:m_RulesList)
                {
                    if(rule.getType()== Rule.eType.HARD)
                    {
                        hardRulesScores.add(rule.getId().CheckRule(parent,i_TimeTable));
                    }
                    else
                    {
                        softRulesScores.add(rule.getId().CheckRule(parent,i_TimeTable));
                    }
                }
                int hardAverage =hardRulesScores.stream().mapToInt(i->i).sum()/hardRulesScores.size();
                int softAverage =softRulesScores.stream().mapToInt(i->i).sum()/softRulesScores.size();
                int fitness=(hardAverage*m_HardRulesWeight/100)+(softAverage*(100-m_HardRulesWeight)/100);
                parent.setFitness((int)fitness);
            }
        }

    }
}
