package DataClasses;

import ParsedClasses.ETTRule;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class Rule {
    public enum eType{HARD,SOFT}

    private final Integer m_Id;
    private eType m_Type;
    private List<String> m_Configuration;

    public Rule(ETTRule i_ETTRule)
    {
        m_Id=Integer.getInteger(i_ETTRule.getETTRuleId());
        m_Type=eType.valueOf(i_ETTRule.getType().toUpperCase());
        m_Configuration=i_ETTRule.getETTConfiguration();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rule rule = (Rule) o;
        return m_Id.equals(rule.m_Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_Id);
    }
}
