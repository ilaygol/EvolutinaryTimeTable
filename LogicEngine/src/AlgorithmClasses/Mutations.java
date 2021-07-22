
package AlgorithmClasses;

import java.util.ArrayList;
import java.util.List;

public class Mutations {

    protected List<Mutation> mutation;

    /**
     * Gets the value of the Mutation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ettMutation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getETTMutation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Mutation }
     * 
     * 
     */
    public List<Mutation> getMutation() {
        if (mutation == null) {
            mutation = new ArrayList<Mutation>();
        }
        return this.mutation;
    }

}
