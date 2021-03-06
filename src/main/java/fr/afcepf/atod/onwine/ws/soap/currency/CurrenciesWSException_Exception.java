
package fr.afcepf.atod.onwine.ws.soap.currency;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.7-b01-
 * Generated source version: 2.1
 * 
 */
@WebFault(name = "CurrenciesWSException", targetNamespace = "http://soap.currency.ws.atod.afcepf.fr/")
public class CurrenciesWSException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private CurrenciesWSException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public CurrenciesWSException_Exception(String message, CurrenciesWSException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public CurrenciesWSException_Exception(String message, CurrenciesWSException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: fr.afcepf.atod.onwine.ws.soap.currency.CurrenciesWSException
     */
    public CurrenciesWSException getFaultInfo() {
        return faultInfo;
    }

}
