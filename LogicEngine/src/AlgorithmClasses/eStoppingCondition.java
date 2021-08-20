package AlgorithmClasses;

//enum class to check stopping conditions
//each instance of this enum check different stopping condition
//instance will return true if according to the condition we have to stop
public enum eStoppingCondition {
    GENERATIONS
            {
                @Override
                public boolean checkStoppingCondition(Integer i_ReqGenerations,Integer i_GenerationsMade,
                                                      Integer i_ReqFitness,Integer i_CurrFitness,
                                                      Long i_ReqMillis,Long i_MillisPassed) {
                    if(i_GenerationsMade<i_ReqGenerations)
                        return false;
                    return true;
                }
            },
    FITNESS
            {
                @Override
                public boolean checkStoppingCondition(Integer i_ReqGenerations,Integer i_GenerationsMade,
                                                      Integer i_ReqFitness,Integer i_CurrFitness,
                                                      Long i_ReqMillis,Long i_MillisPassed) {
                    if(i_CurrFitness<i_ReqFitness)
                        return false;
                    return true;
                }
            },
    TIME
            {
                @Override
                public boolean checkStoppingCondition(Integer i_ReqGenerations,Integer i_GenerationsMade,
                                                      Integer i_ReqFitness,Integer i_CurrFitness,
                                                      Long i_ReqMillis,Long i_MillisPassed) {
                    if(i_MillisPassed<i_ReqMillis)
                        return false;
                    return true;
                }
            };
    public abstract boolean checkStoppingCondition(Integer i_ReqGenerations,Integer i_GenerationsMade,
                                                   Integer i_ReqFitness,Integer i_CurrFitness,
                                                   Long i_ReqMillis,Long i_MillisPassed);
}
