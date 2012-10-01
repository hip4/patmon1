
package ch.bfh.ti.sed.patmon1.ws;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import ch.bfh.ti.sed.patmon1.ws.bindings.Adapter1;


/**
 * <p>Java class for consultObservationPeriodMeasurements complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="consultObservationPeriodMeasurements">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="observationPeriodId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="startFilter" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="endFilter" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="session" type="{http://ch/bfh/ti/sed/patmon1/ws/}session" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultObservationPeriodMeasurements", propOrder = {
    "observationPeriodId",
    "startFilter",
    "endFilter",
    "session"
})
public class ConsultObservationPeriodMeasurements {

    protected Integer observationPeriodId;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date startFilter;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date endFilter;
    protected Session session;

    /**
     * Gets the value of the observationPeriodId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getObservationPeriodId() {
        return observationPeriodId;
    }

    /**
     * Sets the value of the observationPeriodId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setObservationPeriodId(Integer value) {
        this.observationPeriodId = value;
    }

    /**
     * Gets the value of the startFilter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getStartFilter() {
        return startFilter;
    }

    /**
     * Sets the value of the startFilter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartFilter(Date value) {
        this.startFilter = value;
    }

    /**
     * Gets the value of the endFilter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getEndFilter() {
        return endFilter;
    }

    /**
     * Sets the value of the endFilter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndFilter(Date value) {
        this.endFilter = value;
    }

    /**
     * Gets the value of the session property.
     * 
     * @return
     *     possible object is
     *     {@link Session }
     *     
     */
    public Session getSession() {
        return session;
    }

    /**
     * Sets the value of the session property.
     * 
     * @param value
     *     allowed object is
     *     {@link Session }
     *     
     */
    public void setSession(Session value) {
        this.session = value;
    }

}
