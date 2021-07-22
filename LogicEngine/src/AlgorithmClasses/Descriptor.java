
package AlgorithmClasses;

import DataClasses.TimeTable;

public class Descriptor {

    protected TimeTable timeTable;
    protected EvolutionEngine evolutionEngine;

    /**
     * Gets the value of the TimeTable property.
     * 
     * @return
     *     possible object is
     *     {@link TimeTable }
     *     
     */
    public TimeTable getTimeTable() {
        return timeTable;
    }

    /**
     * Sets the value of the TimeTable property.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeTable }
     *     
     */
    public void setTimeTable(TimeTable value) {
        this.timeTable = value;
    }

    /**
     * Gets the value of the EvolutionEngine property.
     * 
     * @return
     *     possible object is
     *     {@link EvolutionEngine }
     *     
     */
    public EvolutionEngine getEvolutionEngine() {
        return evolutionEngine;
    }

    /**
     * Sets the value of the EvolutionEngine property.
     * 
     * @param value
     *     allowed object is
     *     {@link EvolutionEngine }
     *     
     */
    public void setEvolutionEngine(EvolutionEngine value) {
        this.evolutionEngine = value;
    }

}
