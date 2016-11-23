
package fr.afcepf.atod.onwine.ws.soap.tax;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fr.afcepf.atod.onwine.ws.soap.tax package. 
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

    private final static QName _RechercherTaxResponse_QNAME = new QName("http://tax.afcepf.fr", "rechercherTaxResponse");
    private final static QName _GetAllCountryTax_QNAME = new QName("http://tax.afcepf.fr", "getAllCountryTax");
    private final static QName _GetAllCountryTaxResponse_QNAME = new QName("http://tax.afcepf.fr", "getAllCountryTaxResponse");
    private final static QName _CalculTax_QNAME = new QName("http://tax.afcepf.fr", "calculTax");
    private final static QName _CalculTaxResponse_QNAME = new QName("http://tax.afcepf.fr", "calculTaxResponse");
    private final static QName _TaxWSException_QNAME = new QName("http://tax.afcepf.fr", "TaxWSException");
    private final static QName _TaxDto_QNAME = new QName("http://tax.afcepf.fr", "taxDto");
    private final static QName _RechercherTax_QNAME = new QName("http://tax.afcepf.fr", "rechercherTax");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fr.afcepf.atod.onwine.ws.soap.tax
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RechercherTax }
     * 
     */
    public RechercherTax createRechercherTax() {
        return new RechercherTax();
    }

    /**
     * Create an instance of {@link CalculTax }
     * 
     */
    public CalculTax createCalculTax() {
        return new CalculTax();
    }

    /**
     * Create an instance of {@link TaxDto }
     * 
     */
    public TaxDto createTaxDto() {
        return new TaxDto();
    }

    /**
     * Create an instance of {@link TaxWSException }
     * 
     */
    public TaxWSException createTaxWSException() {
        return new TaxWSException();
    }

    /**
     * Create an instance of {@link RechercherTaxResponse }
     * 
     */
    public RechercherTaxResponse createRechercherTaxResponse() {
        return new RechercherTaxResponse();
    }

    /**
     * Create an instance of {@link GetAllCountryTax }
     * 
     */
    public GetAllCountryTax createGetAllCountryTax() {
        return new GetAllCountryTax();
    }

    /**
     * Create an instance of {@link CalculTaxResponse }
     * 
     */
    public CalculTaxResponse createCalculTaxResponse() {
        return new CalculTaxResponse();
    }

    /**
     * Create an instance of {@link GetAllCountryTaxResponse }
     * 
     */
    public GetAllCountryTaxResponse createGetAllCountryTaxResponse() {
        return new GetAllCountryTaxResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RechercherTaxResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tax.afcepf.fr", name = "rechercherTaxResponse")
    public JAXBElement<RechercherTaxResponse> createRechercherTaxResponse(RechercherTaxResponse value) {
        return new JAXBElement<RechercherTaxResponse>(_RechercherTaxResponse_QNAME, RechercherTaxResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllCountryTax }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tax.afcepf.fr", name = "getAllCountryTax")
    public JAXBElement<GetAllCountryTax> createGetAllCountryTax(GetAllCountryTax value) {
        return new JAXBElement<GetAllCountryTax>(_GetAllCountryTax_QNAME, GetAllCountryTax.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllCountryTaxResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tax.afcepf.fr", name = "getAllCountryTaxResponse")
    public JAXBElement<GetAllCountryTaxResponse> createGetAllCountryTaxResponse(GetAllCountryTaxResponse value) {
        return new JAXBElement<GetAllCountryTaxResponse>(_GetAllCountryTaxResponse_QNAME, GetAllCountryTaxResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CalculTax }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tax.afcepf.fr", name = "calculTax")
    public JAXBElement<CalculTax> createCalculTax(CalculTax value) {
        return new JAXBElement<CalculTax>(_CalculTax_QNAME, CalculTax.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CalculTaxResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tax.afcepf.fr", name = "calculTaxResponse")
    public JAXBElement<CalculTaxResponse> createCalculTaxResponse(CalculTaxResponse value) {
        return new JAXBElement<CalculTaxResponse>(_CalculTaxResponse_QNAME, CalculTaxResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TaxWSException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tax.afcepf.fr", name = "TaxWSException")
    public JAXBElement<TaxWSException> createTaxWSException(TaxWSException value) {
        return new JAXBElement<TaxWSException>(_TaxWSException_QNAME, TaxWSException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TaxDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tax.afcepf.fr", name = "taxDto")
    public JAXBElement<TaxDto> createTaxDto(TaxDto value) {
        return new JAXBElement<TaxDto>(_TaxDto_QNAME, TaxDto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RechercherTax }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tax.afcepf.fr", name = "rechercherTax")
    public JAXBElement<RechercherTax> createRechercherTax(RechercherTax value) {
        return new JAXBElement<RechercherTax>(_RechercherTax_QNAME, RechercherTax.class, null, value);
    }

}
