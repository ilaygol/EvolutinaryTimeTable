package AlgorithmClasses;

import DataClasses.AlgorithmData.AmountOfObjectsCalc;
import DataClasses.AlgorithmData.Generation;

public enum eSelection {
    TRUNCATION
            {
                @Override
                public Generation activate(Integer i_Percent,Generation i_PrevGeneration,Generation i_nextGeneration,Integer i_InitialPopulation
                        ,AmountOfObjectsCalc i_AmountOfObj,Crossover i_Crossover)
                {
                    int count=0;
                    int size=i_PrevGeneration.getGenerationSize();
                    int sizeAfterSelection= (size*i_Percent)/100; //auto casting to complete value
                    Generation generationAfterSelection=new Generation();
                    for(int i=0;i<sizeAfterSelection;i++)
                        generationAfterSelection.addParentToGeneration(i_PrevGeneration.getParentByIndex(i));
                    while(i_nextGeneration.getGenerationSize()<i_InitialPopulation)
                    {
                        if(count==0) {
                            i_Crossover.createNewGenerationFromGroupOfParents(generationAfterSelection, i_nextGeneration, i_AmountOfObj);
                            count++;
                        }
                        else
                        {
                            i_Crossover.createNewGenerationFromGroupOfParents(i_nextGeneration,i_nextGeneration,i_AmountOfObj);
                        }
                    }
                    return i_nextGeneration;
                }
            },
    ROULETTEWHEEL
            {
                @Override
                public Generation activate(Integer i_Percent,Generation i_PrevGeneration,Generation i_nextGeneration,Integer i_InitialPopulation
                        ,AmountOfObjectsCalc i_AmountOfObj,Crossover i_Crossover) {
                    return new Generation();
                }
            };
    public abstract Generation activate(Integer i_Percent, Generation i_PrevGeneration, Generation i_nextGeneration, Integer i_InitialPopulation
            , AmountOfObjectsCalc i_AmountOfObj, Crossover i_Crossover);
}
