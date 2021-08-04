package AlgorithmClasses;

import DataClasses.AlgorithmData.Generation;

public enum eSelection {
    TRUNCATION
            {
                @Override
                public Generation activate(Integer i_Percent,Generation i_GenerationBeforeSelection) {
                    int size=i_GenerationBeforeSelection.getGenerationSize();
                    int sizeAfterSelection= (size*i_Percent)/100; //auto casting to complete value
                    Generation generationAfterSelection=new Generation();
                    i_GenerationBeforeSelection.sortGenerationByFitness();
                    for(int i=0;i<sizeAfterSelection;i++)
                        generationAfterSelection.addParentToGeneration(i_GenerationBeforeSelection.getParentByIndex(i));
                    return generationAfterSelection;

                }
            };
    public abstract Generation activate(Integer i_Percent, Generation i_GenerationBeforeSelection);
}