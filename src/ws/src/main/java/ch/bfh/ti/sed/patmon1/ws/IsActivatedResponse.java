
package ch.bfh.ti.sed.patmon1.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for isActivatedResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="isActivatedResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="activated" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "isActivatedResponse", propOrder = {
    "activated"
})
public class IsActivatedResponse {

    protected boolean activated;

    /**
     * Gets the value of the activated property.
     * 
     */
    public boolean isActivated() {
        return activated;
    }

    /**
     * Sets the value of the activated property.
     * 
     */
    public void setActivated(boolean value) {
        this.activated = value;
    }

}
