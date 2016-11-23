
package fr.afcepf.atod.onwine.ws.soap.currency;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.7-b01-
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "CurrencyConverterService", targetNamespace = "http://soap.currency.ws.atod.afcepf.fr/", wsdlLocation = "http://192.168.102.42:8180/OnWine-CurrenciesWS-Biz/CurrencyConverter?wsdl")
public class CurrencyConverterService
    extends Service
{

    private final static URL CURRENCYCONVERTERSERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(fr.afcepf.atod.onwine.ws.soap.currency.CurrencyConverterService.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = fr.afcepf.atod.onwine.ws.soap.currency.CurrencyConverterService.class.getResource(".");
            url = new URL(baseUrl, "http://192.168.102.42:8180/OnWine-CurrenciesWS-Biz/CurrencyConverter?wsdl");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'http://192.168.102.42:8180/OnWine-CurrenciesWS-Biz/CurrencyConverter?wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        CURRENCYCONVERTERSERVICE_WSDL_LOCATION = url;
    }

    public CurrencyConverterService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public CurrencyConverterService() {
        super(CURRENCYCONVERTERSERVICE_WSDL_LOCATION, new QName("http://soap.currency.ws.atod.afcepf.fr/", "CurrencyConverterService"));
    }

    /**
     * 
     * @return
     *     returns ICurrencyConverter
     */
    @WebEndpoint(name = "CurrencyConverterPort")
    public ICurrencyConverter getCurrencyConverterPort() {
        return super.getPort(new QName("http://soap.currency.ws.atod.afcepf.fr/", "CurrencyConverterPort"), ICurrencyConverter.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ICurrencyConverter
     */
    @WebEndpoint(name = "CurrencyConverterPort")
    public ICurrencyConverter getCurrencyConverterPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://soap.currency.ws.atod.afcepf.fr/", "CurrencyConverterPort"), ICurrencyConverter.class, features);
    }

}
