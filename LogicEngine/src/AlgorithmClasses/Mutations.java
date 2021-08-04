package AlgorithmClasses;

import DataTransferClasses.MutationData;
import ParsedClasses.ETTMutation;
import ParsedClasses.ETTMutations;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Mutations {

    private List<Mutation> m_MutationsList;
    private Random m_Roller;

    public Mutations(ETTMutations i_ETTMutations)
    {
        m_MutationsList=new ArrayList<>();
        List<ETTMutation> ettMutations = i_ETTMutations.getETTMutation();
        for(ETTMutation mutation:ettMutations)
        {
            m_MutationsList.add(new Mutation(mutation));
            m_Roller=new Random();
        }
    }

    public List<MutationData> getMutationsDataList()
    {
        List<MutationData> retLst=new ArrayList<>();
        m_MutationsList.forEach(mutation->retLst.add(new MutationData(mutation)));
        return retLst;
    }

    public Mutation getMutationByIndex(Integer i_Index){
        return m_MutationsList.get(i_Index);
    }

    public Integer getWhichMutationToActivateByIndex()
    {
        Integer mutationListSize=m_MutationsList.size();
        double mutationPicker=m_Roller.nextDouble();
        double probability=m_MutationsList.get(0).getProbability();
        for(int i=0;i<mutationListSize;i++)
        {
            if(mutationPicker<=probability)
                return i;
            probability=m_MutationsList.get(i+1).getProbability();
        }
        return (mutationListSize-1);

    }
}
