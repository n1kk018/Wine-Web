
package fr.afcepf.atod.onwine.ws.soap.orchestre;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fr.afcepf.atod.onwine.ws.soap.orchestre package. 
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
    private final static QName _GetRateDeliveryTotalResponse_QNAME = new QName("http://delivery.afcepf.fr", "getRateDeliveryTotalResponse");
    private final static QName _CalculTax_QNAME = new QName("http://tax.afcepf.fr", "calculTax");
    private final static QName _CalculTaxResponse_QNAME = new QName("http://tax.afcepf.fr", "calculTaxResponse");
    private final static QName _DtCurrency_QNAME = new QName("http://soap.currency.ws.atod.afcepf.fr/", "dtCurrency");
    private final static QName _GetAllDeliveriesResponse_QNAME = new QName("http://delivery.afcepf.fr", "getAllDeliveriesResponse");
    private final static QName _ConvertAndFormatResponse_QNAME = new QName("http://soap.currency.ws.atod.afcepf.fr/", "convertAndFormatResponse");
    private final static QName _GetAllCurrenciesResponse_QNAME = new QName("http://soap.currency.ws.atod.afcepf.fr/", "getAllCurrenciesResponse");
    private final static QName _Addition_QNAME = new QName("http://service.al28.afcepf.fr/", "addition");
    private final static QName _CurrenciesWSException_QNAME = new QName("http://soap.currency.ws.atod.afcepf.fr/", "CurrenciesWSException");
    private final static QName _Multiplication_QNAME = new QName("http://service.al28.afcepf.fr/", "multiplication");
    private final static QName _Convert_QNAME = new QName("http://soap.currency.ws.atod.afcepf.fr/", "convert");
    private final static QName _GetAllDeliveries_QNAME = new QName("http://delivery.afcepf.fr", "getAllDeliveries");
    private final static QName _TaxDto_QNAME = new QName("http://tax.afcepf.fr", "taxDto");
    private final static QName _MultiplicationResponse_QNAME = new QName("http://service.al28.afcepf.fr/", "multiplicationResponse");
    private final static QName _ConvertAndFormat_QNAME = new QName("http://soap.currency.ws.atod.afcepf.fr/", "convertAndFormat");
    private final static QName _DeliveriesWSException_QNAME = new QName("http://delivery.afcepf.fr", "DeliveriesWSException");
    private final static QName _GetAllCountryTaxResponse_QNAME = new QName("http://tax.afcepf.fr", "getAllCountryTaxResponse");
    private final static QName _AdditionResponse_QNAME = new QName("http://service.al28.afcepf.fr/", "additionResponse");
    private final static QName _GetInternationalRateDeliveryResponse_QNAME = new QName("http://delivery.afcepf.fr", "getInternationalRateDeliveryResponse");
    private final static QName _ConvertResponse_QNAME = new QName("http://soap.currency.ws.atod.afcepf.fr/", "convertResponse");
    private final static QName _GetAllCountryTax_QNAME = new QName("http://tax.afcepf.fr", "getAllCountryTax");
    private final static QName _TaxWSException_QNAME = new QName("http://tax.afcepf.fr", "TaxWSException");
    private final static QName _GetRateDeliveryTotal_QNAME = new QName("http://delivery.afcepf.fr", "getRateDeliveryTotal");
    private final static QName _GetAllCurrencies_QNAME = new QName("http://soap.currency.ws.atod.afcepf.fr/", "getAllCurrencies");
    private final static QName _GetInternationalRateDelivery_QNAME = new QName("http://delivery.afcepf.fr", "getInternationalRateDelivery");
    private final static QName _RechercherTax_QNAME = new QName("http://tax.afcepf.fr", "rechercherTax");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fr.afcepf.atod.onwine.ws.soap.orchestre
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
     * Create an instance of {@link TaxDto }
     * 
     */
    public TaxDto createTaxDto() {
        return new TaxDto();
    }

    /**
     * Create an instance of {@link GetAllCountryTaxResponse }
     * 
     */
    public GetAllCountryTaxResponse createGetAllCountryTaxResponse() {
        return new GetAllCountryTaxResponse();
    }

    /**
     * Create an instance of {@link GetAllDeliveries }
     * 
     */
    public GetAllDeliveries createGetAllDeliveries() {
        return new GetAllDeliveries();
    }

    /**
     * Create an instance of {@link MultiplicationResponse }
     * 
     */
    public MultiplicationResponse createMultiplicationResponse() {
        return new MultiplicationResponse();
    }

    /**
     * Create an instance of {@link GetAllCurrencies }
     * 
     */
    public GetAllCurrencies createGetAllCurrencies() {
        return new GetAllCurrencies();
    }

    /**
     * Create an instance of {@link RechercherTaxResponse }
     * 
     */
    public RechercherTaxResponse createRechercherTaxResponse() {
        return new RechercherTaxResponse();
    }

    /**
     * Create an instance of {@link GetRateDeliveryTotal }
     * 
     */
    public GetRateDeliveryTotal createGetRateDeliveryTotal() {
        return new GetRateDeliveryTotal();
    }

    /**
     * Create an instance of {@link CalculTax }
     * 
     */
    public CalculTax createCalculTax() {
        return new CalculTax();
    }

    /**
     * Create an instance of {@link Multiplication }
     * 
     */
    public Multiplication createMultiplication() {
        return new Multiplication();
    }

    /**
     * Create an instance of {@link GetAllDeliveriesResponse }
     * 
     */
    public GetAllDeliveriesResponse createGetAllDeliveriesResponse() {
        return new GetAllDeliveriesResponse();
    }

    /**
     * Create an instance of {@link ConvertResponse }
     * 
     */
    public ConvertResponse createConvertResponse() {
        return new ConvertResponse();
    }

    /**
     * Create an instance of {@link GetRateDeliveryTotalResponse }
     * 
     */
    public GetRateDeliveryTotalResponse createGetRateDeliveryTotalResponse() {
        return new GetRateDeliveryTotalResponse();
    }

    /**
     * Create an instance of {@link ConvertAndFormat }
     * 
     */
    public ConvertAndFormat createConvertAndFormat() {
        return new ConvertAndFormat();
    }

    /**
     * Create an instance of {@link Wrapper }
     * 
     */
    public Wrapper createWrapper() {
        return new Wrapper();
    }

    /**
     * Create an instance of {@link ConvertAndFormatResponse }
     * 
     */
    public ConvertAndFormatResponse createConvertAndFormatResponse() {
        return new ConvertAndFormatResponse();
    }

    /**
     * Create an instance of {@link OnWineServicesOrchestratorResponse }
     * 
     */
    public OnWineServicesOrchestratorResponse createOnWineServicesOrchestratorResponse() {
        return new OnWineServicesOrchestratorResponse();
    }

    /**
     * Create an instance of {@link AdditionResponse }
     * 
     */
    public AdditionResponse createAdditionResponse() {
        return new AdditionResponse();
    }

    /**
     * Create an instance of {@link GetInternationalRateDeliveryResponse }
     * 
     */
    public GetInternationalRateDeliveryResponse createGetInternationalRateDeliveryResponse() {
        return new GetInternationalRateDeliveryResponse();
    }

    /**
     * Create an instance of {@link CalculTaxResponse }
     * 
     */
    public CalculTaxResponse createCalculTaxResponse() {
        return new CalculTaxResponse();
    }

    /**
     * Create an instance of {@link OnWineServicesOrchestratorRequest }
     * 
     */
    public OnWineServicesOrchestratorRequest createOnWineServicesOrchestratorRequest() {
        return new OnWineServicesOrchestratorRequest();
    }

    /**
     * Create an instance of {@link GetInternationalRateDelivery }
     * 
     */
    public GetInternationalRateDelivery createGetInternationalRateDelivery() {
        return new GetInternationalRateDelivery();
    }

    /**
     * Create an instance of {@link CurrenciesWSException }
     * 
     */
    public CurrenciesWSException createCurrenciesWSException() {
        return new CurrenciesWSException();
    }

    /**
     * Create an instance of {@link GetAllCountryTax }
     * 
     */
    public GetAllCountryTax createGetAllCountryTax() {
        return new GetAllCountryTax();
    }

    /**
     * Create an instance of {@link DtCurrency }
     * 
     */
    public DtCurrency createDtCurrency() {
        return new DtCurrency();
    }

    /**
     * Create an instance of {@link DeliveriesWSException }
     * 
     */
    public DeliveriesWSException createDeliveriesWSException() {
        return new DeliveriesWSException();
    }

    /**
     * Create an instance of {@link TaxWSException }
     * 
     */
    public TaxWSException createTaxWSException() {
        return new TaxWSException();
    }

    /**
     * Create an instance of {@link Convert }
     * 
     */
    public Convert createConvert() {
        return new Convert();
    }

    /**
     * Create an instance of {@link Addition }
     * 
     */
    public Addition createAddition() {
        return new Addition();
    }

    /**
     * Create an instance of {@link DtDelivery }
     * 
     */
    public DtDelivery createDtDelivery() {
        return new DtDelivery();
    }

    /**
     * Create an instance of {@link GetAllCurrenciesResponse }
     * 
     */
    public GetAllCurrenciesResponse createGetAllCurrenciesResponse() {
        return new GetAllCurrenciesResponse();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRateDeliveryTotalResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://delivery.afcepf.fr", name = "getRateDeliveryTotalResponse")
    public JAXBElement<GetRateDeliveryTotalResponse> createGetRateDeliveryTotalResponse(GetRateDeliveryTotalResponse value) {
        return new JAXBElement<GetRateDeliveryTotalResponse>(_GetRateDeliveryTotalResponse_QNAME, GetRateDeliveryTotalResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link DtCurrency }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.currency.ws.atod.afcepf.fr/", name = "dtCurrency")
    public JAXBElement<DtCurrency> createDtCurrency(DtCurrency value) {
        return new JAXBElement<DtCurrency>(_DtCurrency_QNAME, DtCurrency.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link ConvertAndFormatResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.currency.ws.atod.afcepf.fr/", name = "convertAndFormatResponse")
    public JAXBElement<ConvertAndFormatResponse> createConvertAndFormatResponse(ConvertAndFormatResponse value) {
        return new JAXBElement<ConvertAndFormatResponse>(_ConvertAndFormatResponse_QNAME, ConvertAndFormatResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link Addition }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.al28.afcepf.fr/", name = "addition")
    public JAXBElement<Addition> createAddition(Addition value) {
        return new JAXBElement<Addition>(_Addition_QNAME, Addition.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link Multiplication }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.al28.afcepf.fr/", name = "multiplication")
    public JAXBElement<Multiplication> createMultiplication(Multiplication value) {
        return new JAXBElement<Multiplication>(_Multiplication_QNAME, Multiplication.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllDeliveries }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://delivery.afcepf.fr", name = "getAllDeliveries")
    public JAXBElement<GetAllDeliveries> createGetAllDeliveries(GetAllDeliveries value) {
        return new JAXBElement<GetAllDeliveries>(_GetAllDeliveries_QNAME, GetAllDeliveries.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link MultiplicationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.al28.afcepf.fr/", name = "multiplicationResponse")
    public JAXBElement<MultiplicationResponse> createMultiplicationResponse(MultiplicationResponse value) {
        return new JAXBElement<MultiplicationResponse>(_MultiplicationResponse_QNAME, MultiplicationResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link DeliveriesWSException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://delivery.afcepf.fr", name = "DeliveriesWSException")
    public JAXBElement<DeliveriesWSException> createDeliveriesWSException(DeliveriesWSException value) {
        return new JAXBElement<DeliveriesWSException>(_DeliveriesWSException_QNAME, DeliveriesWSException.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link AdditionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.al28.afcepf.fr/", name = "additionResponse")
    public JAXBElement<AdditionResponse> createAdditionResponse(AdditionResponse value) {
        return new JAXBElement<AdditionResponse>(_AdditionResponse_QNAME, AdditionResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link ConvertResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.currency.ws.atod.afcepf.fr/", name = "convertResponse")
    public JAXBElement<ConvertResponse> createConvertResponse(ConvertResponse value) {
        return new JAXBElement<ConvertResponse>(_ConvertResponse_QNAME, ConvertResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link TaxWSException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tax.afcepf.fr", name = "TaxWSException")
    public JAXBElement<TaxWSException> createTaxWSException(TaxWSException value) {
        return new JAXBElement<TaxWSException>(_TaxWSException_QNAME, TaxWSException.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllCurrencies }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.currency.ws.atod.afcepf.fr/", name = "getAllCurrencies")
    public JAXBElement<GetAllCurrencies> createGetAllCurrencies(GetAllCurrencies value) {
        return new JAXBElement<GetAllCurrencies>(_GetAllCurrencies_QNAME, GetAllCurrencies.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetInternationalRateDelivery }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://delivery.afcepf.fr", name = "getInternationalRateDelivery")
    public JAXBElement<GetInternationalRateDelivery> createGetInternationalRateDelivery(GetInternationalRateDelivery value) {
        return new JAXBElement<GetInternationalRateDelivery>(_GetInternationalRateDelivery_QNAME, GetInternationalRateDelivery.class, null, value);
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
