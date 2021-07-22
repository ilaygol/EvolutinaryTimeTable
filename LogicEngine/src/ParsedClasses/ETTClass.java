
package ParsedClasses;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element ref="{}ETT-Name"/>
 *         &lt;element ref="{}ETT-Requirements"/>
 *       &lt;/all>
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {

})
@XmlRootElement(name = "ETT-Class")
public class ETTClass {

    @XmlList
    @XmlElement(name = "ETT-Name", required = true)
    protected List<String> ettName;
    @XmlElement(name = "ETT-Requirements", required = true)
    protected ETTRequirements ettRequirements;
    @XmlAttribute(name = "id", required = true)
    protected int id;

    /**
     * Gets the value of the ettName property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ettName property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getETTName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getETTName() {
        if (ettName == null) {
            ettName = new ArrayList<String>();
        }
        return this.ettName;
    }

    /**
     * Gets the value of the ettRequirements property.
     * 
     * @return
     *     possible object is
     *     {@link ETTRequirements }
     *     
     */
    public ETTRequirements getETTRequirements() {
        return ettRequirements;
    }

    /**
     * Sets the value of the ettRequirements property.
     * 
     * @param value
     *     allowed object is
     *     {@link ETTRequirements }
     *     
     */
    public void setETTRequirements(ETTRequirements value) {
        this.ettRequirements = value;
    }

    /**
     * Gets the value of the id property.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

}