
package ch.bfh.ti.sed.patmon1.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebService(name = "LoginControllerPort", targetNamespace = "http://ch/bfh/ti/sed/patmon1/ws/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface LoginControllerPort {


    /**
     * 
     * @param username
     * @param password
     * @return
     *     returns ch.bfh.ti.sed.patmon1.ws.Session
     * @throws NotLoggedInException
     */
    @WebMethod
    @WebResult(name = "session", targetNamespace = "")
    @RequestWrapper(localName = "login", targetNamespace = "http://ch/bfh/ti/sed/patmon1/ws/", className = "ch.bfh.ti.sed.patmon1.ws.Login")
    @ResponseWrapper(localName = "loginResponse", targetNamespace = "http://ch/bfh/ti/sed/patmon1/ws/", className = "ch.bfh.ti.sed.patmon1.ws.LoginResponse")
    public Session login(
        @WebParam(name = "username", targetNamespace = "")
        String username,
        @WebParam(name = "password", targetNamespace = "")
        String password)
        throws NotLoggedInException
    ;

}
