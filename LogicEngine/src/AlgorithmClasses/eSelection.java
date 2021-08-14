package AlgorithmClasses;

import DataClasses.AlgorithmData.AmountOfObjectsCalc;
import DataClasses.AlgorithmData.Generation;
import DataClasses.AlgorithmData.Parent;

import java.util.Random;

public enum eSelection {
    TRUNCATION
            {
                @Override
                public void activate(Integer i_Percent, Random i_Roller,Generation i_PrevGeneration,Generation i_nextGeneration,Integer i_InitialPopulation
                        ,AmountOfObjectsCalc i_AmountOfObj,Crossover i_Crossover)
                {

                    int size=i_PrevGeneration.getGenerationSize();
                    int sizeAfterSelection= (size*i_Percent)/100; //auto casting to complete value
                    Generation generationAfterSelectingPercent=new Generation();
                    for(int i=0;i<sizeAfterSelection;i++)
                        generationAfterSelectingPercent.addParentToGeneration(i_PrevGeneration.getParentByIndex(i));
                    while(i_nextGeneration.getGenerationSize()<i_InitialPopulation) {
                        i_Crossover.createNewGenerationFromGroupOfParents(generationAfterSelectingPercent, i_nextGeneration, i_AmountOfObj);
                    }
                }
            },
    ROULETTEWHEEL
            {
                @Override
                public void activate(Integer i_Percent, Random i_Roller,Generation i_PrevGeneration,Generation i_nextGeneration,Integer i_InitialPopulation
                        ,AmountOfObjectsCalc i_AmountOfObj,Crossover i_Crossover) {
                    Parent p1;
                    Parent p2;
                    while(i_nextGeneration.getGenerationSize()<i_InitialPopulation)
                    {
                        p1=activateRouletteWheel(i_PrevGeneration,i_Roller);
                        p2=activateRouletteWheel(i_PrevGeneration,i_Roller);
                        i_Crossover.createTwoChildren(i_nextGeneration,p1,p2,i_AmountOfObj);
                    }
                }
            };
    public abstract void activate(Integer i_Percent, Random i_Roller, Generation i_PrevGeneration, Generation i_nextGeneration, Integer i_InitialPopulation
            , AmountOfObjectsCalc i_AmountOfObj, Crossover i_Crossover);

    public Parent activateRouletteWheel(Generation i_Generation,Random i_Roller)
    {
        int scanner=0;
        int index=0;
        int fitness=i_Generation.getSumOfFitness();
        int rouletteWheelResult=i_Roller.nextInt(fitness);
        if(rouletteWheelResult==0)
            return i_Generation.getParentByIndex(0);
        while(scanner<rouletteWheelResult)
        {
            scanner+=i_Generation.getParentByIndex(index).getFitness();
            index++;
        }
        return i_Generation.getParentByIndex(index-1);
    }
}
