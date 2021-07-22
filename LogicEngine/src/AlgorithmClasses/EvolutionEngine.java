
package AlgorithmClasses;

public class EvolutionEngine {

    protected InitialPopulation initialPopulation;
    protected Selection selection;
    protected Crossover crossover;
    protected Mutations mutations;

    /**
     * Gets the value of the InitialPopulation property.
     * 
     * @return
     *     possible object is
     *     {@link InitialPopulation }
     *     
     */
    public InitialPopulation getInitialPopulation() {
        return initialPopulation;
    }

    /**
     * Sets the value of the InitialPopulation property.
     * 
     * @param value
     *     allowed object is
     *     {@link InitialPopulation }
     *     
     */
    public void setInitialPopulation(InitialPopulation value) {
        this.initialPopulation = value;
    }

    /**
     * Gets the value of the Selection property.
     * 
     * @return
     *     possible object is
     *     {@link Selection }
     *     
     */
    public Selection getSelection() {
        return selection;
    }

    /**
     * Sets the value of the Selection property.
     * 
     * @param value
     *     allowed object is
     *     {@link Selection }
     *     
     */
    public void setSelection(Selection value) {
        this.selection = value;
    }

    /**
     * Gets the value of the Crossover property.
     * 
     * @return
     *     possible object is
     *     {@link Crossover }
     *     
     */
    public Crossover getCrossover() {
        return crossover;
    }

    /**
     * Sets the value of the Crossover property.
     * 
     * @param value
     *     allowed object is
     *     {@link Crossover }
     *     
     */
    public void setCrossover(Crossover value) {
        this.crossover = value;
    }

    /**
     * Gets the value of the Mutations property.
     * 
     * @return
     *     possible object is
     *     {@link Mutations }
     *     
     */
    public Mutations getMutations() {
        return mutations;
    }

    /**
     * Sets the value of the Mutations property.
     * 
     * @param value
     *     allowed object is
     *     {@link Mutations }
     *     
     */
    public void setMutations(Mutations value) {
        this.mutations = value;
    }

}
