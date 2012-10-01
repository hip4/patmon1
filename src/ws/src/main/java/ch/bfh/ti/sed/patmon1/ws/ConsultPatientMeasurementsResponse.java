
package ch.bfh.ti.sed.patmon1.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for consultPatientMeasurementsResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="consultPatientMeasurementsResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="measurements" type="{http://ch/bfh/ti/sed/patmon1/ws/}measurementList" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultPatientMeasurementsResponse", propOrder = {
    "measurements"
})
public class ConsultPatientMeasurementsResponse {

    protected MeasurementList measurements;

    /**
     * Gets the value of the measurements property.
     * 
     * @return
     *     possible object is
     *     {@link MeasurementList }
     *     
     */
    public MeasurementList getMeasurements() {
        return measurements;
    }

    /**
     * Sets the value of the measurements property.
     * 
     * @param value
     *     allowed object is
     *     {@link MeasurementList }
     *     
     */
    public void setMeasurements(MeasurementList value) {
        this.measurements = value;
    }

}
