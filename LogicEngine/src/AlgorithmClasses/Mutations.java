package AlgorithmClasses;

import DataTransferClasses.MutationData;
import ParsedClasses.ETTMutation;
import ParsedClasses.ETTMutations;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Mutations {

    private List<Mutation> m_MutationsList;

    public Mutations(ETTMutations i_ETTMutations)
    {
        m_MutationsList=new ArrayList<>();
        List<ETTMutation> ettMutations = i_ETTMutations.getETTMutation();
        for(ETTMutation mutation:ettMutations)
        {
            m_MutationsList.add(new Mutation(mutation));
        }
    }

    public List<MutationData> getMutationsDataList()
    {
        List<MutationData> retLst=new ArrayList<>();
        m_MutationsList.forEach(mutation->retLst.add(new MutationData(mutation)));
        return retLst;
    }
}
