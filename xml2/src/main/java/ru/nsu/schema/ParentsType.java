
package ru.nsu.schema;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for parents-type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="parents-type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="mother" type="{}person-ref" minOccurs="0"/&gt;
 *         &lt;element name="father" type="{}person-ref" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "parents-type", propOrder = {
    "mother",
    "father"
})
public class ParentsType {

    protected PersonRef mother;
    protected PersonRef father;

    /**
     * Gets the value of the mother property.
     * 
     * @return
     *     possible object is
     *     {@link PersonRef }
     *     
     */
    public PersonRef getMother() {
        return mother;
    }

    /**
     * Sets the value of the mother property.
     * 
     * @param value
     *     allowed object is
     *     {@link PersonRef }
     *     
     */
    public void setMother(PersonRef value) {
        this.mother = value;
    }

    /**
     * Gets the value of the father property.
     * 
     * @return
     *     possible object is
     *     {@link PersonRef }
     *     
     */
    public PersonRef getFather() {
        return father;
    }

    /**
     * Sets the value of the father property.
     * 
     * @param value
     *     allowed object is
     *     {@link PersonRef }
     *     
     */
    public void setFather(PersonRef value) {
        this.father = value;
    }

}
