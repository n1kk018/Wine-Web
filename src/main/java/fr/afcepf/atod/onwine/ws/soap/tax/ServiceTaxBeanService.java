
package fr.afcepf.atod.onwine.ws.soap.tax;

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
@WebServiceClient(name = "ServiceTaxBeanService", targetNamespace = "http://tax.afcepf.fr", wsdlLocation = "http://192.168.102.42:8180/OnWine-TaxWS-Biz/ServiceTaxBean?wsdl")
public class ServiceTaxBeanService
    extends Service
{

    private final static URL SERVICETAXBEANSERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(fr.afcepf.atod.onwine.ws.soap.tax.ServiceTaxBeanService.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = fr.afcepf.atod.onwine.ws.soap.tax.ServiceTaxBeanService.class.getResource(".");
            url = new URL(baseUrl, "http://192.168.102.42:8180/OnWine-TaxWS-Biz/ServiceTaxBean?wsdl");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'http://192.168.102.42:8180/OnWine-TaxWS-Biz/ServiceTaxBean?wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        SERVICETAXBEANSERVICE_WSDL_LOCATION = url;
    }

    public ServiceTaxBeanService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ServiceTaxBeanService() {
        super(SERVICETAXBEANSERVICE_WSDL_LOCATION, new QName("http://tax.afcepf.fr", "ServiceTaxBeanService"));
    }

    /**
     * 
     * @return
     *     returns ServiceTax
     */
    @WebEndpoint(name = "ServiceTaxBeanPort")
    public ServiceTax getServiceTaxBeanPort() {
        return super.getPort(new QName("http://tax.afcepf.fr", "ServiceTaxBeanPort"), ServiceTax.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ServiceTax
     */
    @WebEndpoint(name = "ServiceTaxBeanPort")
    public ServiceTax getServiceTaxBeanPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://tax.afcepf.fr", "ServiceTaxBeanPort"), ServiceTax.class, features);
    }

}