
package ch.bfh.ti.sed.patmon1.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getPatientsResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getPatientsResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="patients" type="{http://ch/bfh/ti/sed/patmon1/ws/}patientList" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getPatientsResponse", propOrder = {
    "patients"
})
public class GetPatientsResponse {

    protected PatientList patients;

    /**
     * Gets the value of the patients property.
     * 
     * @return
     *     possible object is
     *     {@link PatientList }
     *     
     */
    public PatientList getPatients() {
        return patients;
    }

    /**
     * Sets the value of the patients property.
     * 
     * @param value
     *     allowed object is
     *     {@link PatientList }
     *     
     */
    public void setPatients(PatientList value) {
        this.patients = value;
    }

}
