
package ch.bfh.ti.sed.patmon1.ws;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebFault(name = "EntityNotFoundException", targetNamespace = "http://ch/bfh/ti/sed/patmon1/ws/")
public class EntityNotFoundException
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private FaultBean faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public EntityNotFoundException(String message, FaultBean faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public EntityNotFoundException(String message, FaultBean faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: ch.bfh.ti.sed.patmon1.ws.FaultBean
     */
    public FaultBean getFaultInfo() {
        return faultInfo;
    }

}
