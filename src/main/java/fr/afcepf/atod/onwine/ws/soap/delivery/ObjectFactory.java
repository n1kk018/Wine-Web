
package fr.afcepf.atod.onwine.ws.soap.delivery;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fr.afcepf.atod.onwine.ws.soap.delivery package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetRateDeliveryTotalResponse_QNAME = new QName("http://delivery.afcepf.fr", "getRateDeliveryTotalResponse");
    private final static QName _DeliveriesWSException_QNAME = new QName("http://delivery.afcepf.fr", "DeliveriesWSException");
    private final static QName _GetAllDeliveriesResponse_QNAME = new QName("http://delivery.afcepf.fr", "getAllDeliveriesResponse");
    private final static QName _GetAllDeliveries_QNAME = new QName("http://delivery.afcepf.fr", "getAllDeliveries");
    private final static QName _GetInternationalRateDeliveryResponse_QNAME = new QName("http://delivery.afcepf.fr", "getInternationalRateDeliveryResponse");
    private final static QName _GetRateDeliveryTotal_QNAME = new QName("http://delivery.afcepf.fr", "getRateDeliveryTotal");
    private final static QName _GetInternationalRateDelivery_QNAME = new QName("http://delivery.afcepf.fr", "getInternationalRateDelivery");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fr.afcepf.atod.onwine.ws.soap.delivery
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetRateDeliveryTotalResponse }
     * 
     */
    public GetRateDeliveryTotalResponse createGetRateDeliveryTotalResponse() {
        return new GetRateDeliveryTotalResponse();
    }

    /**
     * Create an instance of {@link GetAllDeliveries }
     * 
     */
    public GetAllDeliveries createGetAllDeliveries() {
        return new GetAllDeliveries();
    }

    /**
     * Create an instance of {@link DtDelivery }
     * 
     */
    public DtDelivery createDtDelivery() {
        return new DtDelivery();
    }

    /**
     * Create an instance of {@link GetAllDeliveriesResponse }
     * 
     */
    public GetAllDeliveriesResponse createGetAllDeliveriesResponse() {
        return new GetAllDeliveriesResponse();
    }

    /**
     * Create an instance of {@link GetRateDeliveryTotal }
     * 
     */
    public GetRateDeliveryTotal createGetRateDeliveryTotal() {
        return new GetRateDeliveryTotal();
    }

    /**
     * Create an instance of {@link DeliveriesWSException }
     * 
     */
    public DeliveriesWSException createDeliveriesWSException() {
        return new DeliveriesWSException();
    }

    /**
     * Create an instance of {@link GetInternationalRateDelivery }
     * 
     */
    public GetInternationalRateDelivery createGetInternationalRateDelivery() {
        return new GetInternationalRateDelivery();
    }

    /**
     * Create an instance of {@link GetInternationalRateDeliveryResponse }
     * 
     */
    public GetInternationalRateDeliveryResponse createGetInternationalRateDeliveryResponse() {
        return new GetInternationalRateDeliveryResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRateDeliveryTotalResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://delivery.afcepf.fr", name = "getRateDeliveryTotalResponse")
    public JAXBElement<GetRateDeliveryTotalResponse> createGetRateDeliveryTotalResponse(GetRateDeliveryTotalResponse value) {
        return new JAXBElement<GetRateDeliveryTotalResponse>(_GetRateDeliveryTotalResponse_QNAME, GetRateDeliveryTotalResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeliveriesWSException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://delivery.afcepf.fr", name = "DeliveriesWSException")
    public JAXBElement<DeliveriesWSException> createDeliveriesWSException(DeliveriesWSException value) {
        return new JAXBElement<DeliveriesWSException>(_DeliveriesWSException_QNAME, DeliveriesWSException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllDeliveriesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://delivery.afcepf.fr", name = "getAllDeliveriesResponse")
    public JAXBElement<GetAllDeliveriesResponse> createGetAllDeliveriesResponse(GetAllDeliveriesResponse value) {
        return new JAXBElement<GetAllDeliveriesResponse>(_GetAllDeliveriesResponse_QNAME, GetAllDeliveriesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllDeliveries }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://delivery.afcepf.fr", name = "getAllDeliveries")
    public JAXBElement<GetAllDeliveries> createGetAllDeliveries(GetAllDeliveries value) {
        return new JAXBElement<GetAllDeliveries>(_GetAllDeliveries_QNAME, GetAllDeliveries.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetInternationalRateDeliveryResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://delivery.afcepf.fr", name = "getInternationalRateDeliveryResponse")
    public JAXBElement<GetInternationalRateDeliveryResponse> createGetInternationalRateDeliveryResponse(GetInternationalRateDeliveryResponse value) {
        return new JAXBElement<GetInternationalRateDeliveryResponse>(_GetInternationalRateDeliveryResponse_QNAME, GetInternationalRateDeliveryResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRateDeliveryTotal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://delivery.afcepf.fr", name = "getRateDeliveryTotal")
    public JAXBElement<GetRateDeliveryTotal> createGetRateDeliveryTotal(GetRateDeliveryTotal value) {
        return new JAXBElement<GetRateDeliveryTotal>(_GetRateDeliveryTotal_QNAME, GetRateDeliveryTotal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetInternationalRateDelivery }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://delivery.afcepf.fr", name = "getInternationalRateDelivery")
    public JAXBElement<GetInternationalRateDelivery> createGetInternationalRateDelivery(GetInternationalRateDelivery value) {
        return new JAXBElement<GetInternationalRateDelivery>(_GetInternationalRateDelivery_QNAME, GetInternationalRateDelivery.class, null, value);
    }

}
