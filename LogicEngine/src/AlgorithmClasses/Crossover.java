package AlgorithmClasses;

public class Crossover {

    protected String configuration;
    protected String name;
    protected int cuttingPoints;

    /**
     * Gets the value of the configuration property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getConfiguration() {
        return configuration;
    }

    /**
     * Sets the value of the configuration property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConfiguration(String value) {
        this.configuration = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the cuttingPoints property.
     * 
     */
    public int getCuttingPoints() {
        return cuttingPoints;
    }

    /**
     * Sets the value of the cuttingPoints property.
     * 
     */
    public void setCuttingPoints(int value) {
        this.cuttingPoints = value;
    }

}
