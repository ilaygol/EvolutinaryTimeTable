package DataClasses.FileInputDataClasses;

import ParsedClasses.ETTRule;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class Rule {
    public enum eType{HARD,SOFT}

    private eRules m_eRule;
    private eType m_Type;
    private String m_Configuration;

    public Rule(ETTRule i_ETTRule)
    {
        m_eRule=eRules.valueOf(i_ETTRule.getETTRuleId().toUpperCase());
        m_Type=eType.valueOf(i_ETTRule.getType().toUpperCase());
        m_Configuration=i_ETTRule.getETTConfiguration();
    }

    @Override
    public String toString() {
        return "Rule{" +
                "m_Id=" + m_eRule.toString() +
                ", m_Type=" + m_Type +
                ", m_Configuration=" + m_Configuration +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rule rule = (Rule) o;
        return m_eRule.equals(rule.m_eRule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_eRule);
    }

    public eRules getId() {
        return m_eRule;
    }

    public eType getType() {
        return m_Type;
    }

    public String getConfiguration() {
        return m_Configuration;
    }
}
