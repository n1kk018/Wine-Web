
package fr.afcepf.atod.onwine.ws.soap.currency;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fr.afcepf.atod.onwine.ws.soap.currency package. 
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

    private final static QName _ConvertResponse_QNAME = new QName("http://soap.currency.ws.atod.afcepf.fr/", "convertResponse");
    private final static QName _GetAllCurrenciesResponse_QNAME = new QName("http://soap.currency.ws.atod.afcepf.fr/", "getAllCurrenciesResponse");
    private final static QName _ConvertAndFormat_QNAME = new QName("http://soap.currency.ws.atod.afcepf.fr/", "convertAndFormat");
    private final static QName _CurrenciesWSException_QNAME = new QName("http://soap.currency.ws.atod.afcepf.fr/", "CurrenciesWSException");
    private final static QName _DtCurrency_QNAME = new QName("http://soap.currency.ws.atod.afcepf.fr/", "dtCurrency");
    private final static QName _Convert_QNAME = new QName("http://soap.currency.ws.atod.afcepf.fr/", "convert");
    private final static QName _GetAllCurrencies_QNAME = new QName("http://soap.currency.ws.atod.afcepf.fr/", "getAllCurrencies");
    private final static QName _ConvertAndFormatResponse_QNAME = new QName("http://soap.currency.ws.atod.afcepf.fr/", "convertAndFormatResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fr.afcepf.atod.onwine.ws.soap.currency
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Wrapper }
     * 
     */
    public Wrapper createWrapper() {
        return new Wrapper();
    }

    /**
     * Create an instance of {@link GetAllCurrenciesResponse }
     * 
     */
    public GetAllCurrenciesResponse createGetAllCurrenciesResponse() {
        return new GetAllCurrenciesResponse();
    }

    /**
     * Create an instance of {@link Convert }
     * 
     */
    public Convert createConvert() {
        return new Convert();
    }

    /**
     * Create an instance of {@link ConvertResponse }
     * 
     */
    public ConvertResponse createConvertResponse() {
        return new ConvertResponse();
    }

    /**
     * Create an instance of {@link CurrenciesWSException }
     * 
     */
    public CurrenciesWSException createCurrenciesWSException() {
        return new CurrenciesWSException();
    }

    /**
     * Create an instance of {@link DtCurrency }
     * 
     */
    public DtCurrency createDtCurrency() {
        return new DtCurrency();
    }

    /**
     * Create an instance of {@link GetAllCurrencies }
     * 
     */
    public GetAllCurrencies createGetAllCurrencies() {
        return new GetAllCurrencies();
    }

    /**
     * Create an instance of {@link ConvertAndFormatResponse }
     * 
     */
    public ConvertAndFormatResponse createConvertAndFormatResponse() {
        return new ConvertAndFormatResponse();
    }

    /**
     * Create an instance of {@link ConvertAndFormat }
     * 
     */
    public ConvertAndFormat createConvertAndFormat() {
        return new ConvertAndFormat();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConvertResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.currency.ws.atod.afcepf.fr/", name = "convertResponse")
    public JAXBElement<ConvertResponse> createConvertResponse(ConvertResponse value) {
        return new JAXBElement<ConvertResponse>(_ConvertResponse_QNAME, ConvertResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllCurrenciesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.currency.ws.atod.afcepf.fr/", name = "getAllCurrenciesResponse")
    public JAXBElement<GetAllCurrenciesResponse> createGetAllCurrenciesResponse(GetAllCurrenciesResponse value) {
        return new JAXBElement<GetAllCurrenciesResponse>(_GetAllCurrenciesResponse_QNAME, GetAllCurrenciesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConvertAndFormat }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.currency.ws.atod.afcepf.fr/", name = "convertAndFormat")
    public JAXBElement<ConvertAndFormat> createConvertAndFormat(ConvertAndFormat value) {
        return new JAXBElement<ConvertAndFormat>(_ConvertAndFormat_QNAME, ConvertAndFormat.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CurrenciesWSException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.currency.ws.atod.afcepf.fr/", name = "CurrenciesWSException")
    public JAXBElement<CurrenciesWSException> createCurrenciesWSException(CurrenciesWSException value) {
        return new JAXBElement<CurrenciesWSException>(_CurrenciesWSException_QNAME, CurrenciesWSException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DtCurrency }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.currency.ws.atod.afcepf.fr/", name = "dtCurrency")
    public JAXBElement<DtCurrency> createDtCurrency(DtCurrency value) {
        return new JAXBElement<DtCurrency>(_DtCurrency_QNAME, DtCurrency.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Convert }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.currency.ws.atod.afcepf.fr/", name = "convert")
    public JAXBElement<Convert> createConvert(Convert value) {
        return new JAXBElement<Convert>(_Convert_QNAME, Convert.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllCurrencies }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.currency.ws.atod.afcepf.fr/", name = "getAllCurrencies")
    public JAXBElement<GetAllCurrencies> createGetAllCurrencies(GetAllCurrencies value) {
        return new JAXBElement<GetAllCurrencies>(_GetAllCurrencies_QNAME, GetAllCurrencies.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConvertAndFormatResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.currency.ws.atod.afcepf.fr/", name = "convertAndFormatResponse")
    public JAXBElement<ConvertAndFormatResponse> createConvertAndFormatResponse(ConvertAndFormatResponse value) {
        return new JAXBElement<ConvertAndFormatResponse>(_ConvertAndFormatResponse_QNAME, ConvertAndFormatResponse.class, null, value);
    }

}
