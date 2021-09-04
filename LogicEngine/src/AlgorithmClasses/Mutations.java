package AlgorithmClasses;

import DataClasses.AlgorithmData.AmountOfObjectsCalc;
import DataClasses.AlgorithmData.Generation;
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
        }
        m_Roller=new Random();
    }

    public Mutations(List<MutationData> i_MutationDataList)
    {
        m_MutationsList=new ArrayList<>();
        for(MutationData mutation:i_MutationDataList)
        {
            m_MutationsList.add(new Mutation(mutation));
        }
        m_Roller=new Random();
    }

    public List<Mutation> getMutationsList(){return m_MutationsList;}
    public List<MutationData> getMutationsDataList()
    {
        List<MutationData> retLst=new ArrayList<>();
        m_MutationsList.forEach(mutation->retLst.add(new MutationData(mutation)));
        return retLst;
    }

    public Mutation getMutationByIndex(Integer i_Index){
        return m_MutationsList.get(i_Index);
    }
    public Mutation getMutationByString(String i_String)
    {
        Mutation retMutation=null;
        for(Mutation m:m_MutationsList)
        {
            String mutationString;
            mutationString=m.getEType().toString(m.getMaxTupples(),m.getChar());
            if(mutationString.equals(i_String)) {
                retMutation = m;
                break;
            }
        }
        return retMutation;
    }

    public void scanAndActivateMutations(Generation i_Generation, AmountOfObjectsCalc i_Amounts)
    {
        for(Mutation m:m_MutationsList)
        {
            double chooseIFToActivate=m_Roller.nextDouble();
            if(chooseIFToActivate<=m.getProbability())
                m.activateMutation(i_Generation,i_Amounts);
        }
    }

    public void createAndAddMutationToList(String i_Name,String i_Tupples,String i_Char,String i_Probability)
    {
        Mutation mutation=new Mutation(i_Name,i_Tupples,i_Char,i_Probability);
        m_MutationsList.add(mutation);
    }

    public void addMutationToList(Mutation i_Mutation){m_MutationsList.add(i_Mutation);}


}
