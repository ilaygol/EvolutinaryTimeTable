package DataTransferClasses;

import DataClasses.FileInputDataClasses.Rule;

public class RuleFileData {
    private String m_RuleName;
    private String m_RuleType;

    public RuleFileData(Rule i_Rule)
    {
        m_RuleName=i_Rule.getId().name();
        m_RuleType=i_Rule.getType().name();
    }
}
