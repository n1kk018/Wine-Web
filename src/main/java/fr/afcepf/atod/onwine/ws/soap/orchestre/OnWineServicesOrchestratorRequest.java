
package fr.afcepf.atod.onwine.ws.soap.orchestre;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="quantity" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="countryCodeShipping" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="countryCodeBilling" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="transporterName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="srcCurrency" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="trgtCurrency" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "amount",
    "quantity",
    "countryCodeShipping",
    "countryCodeBilling",
    "transporterName",
    "srcCurrency",
    "trgtCurrency"
})
@XmlRootElement(name = "OnWineServicesOrchestratorRequest", namespace = "http://orchestrateur.onwine.atod.afcepf.fr")
public class OnWineServicesOrchestratorRequest {

    @XmlElement(namespace = "http://orchestrateur.onwine.atod.afcepf.fr")
    protected double amount;
    @XmlElement(namespace = "http://orchestrateur.onwine.atod.afcepf.fr", required = true)
    protected BigInteger quantity;
    @XmlElement(namespace = "http://orchestrateur.onwine.atod.afcepf.fr", required = true)
    protected String countryCodeShipping;
    @XmlElement(namespace = "http://orchestrateur.onwine.atod.afcepf.fr", required = true)
    protected String countryCodeBilling;
    @XmlElement(namespace = "http://orchestrateur.onwine.atod.afcepf.fr", required = true)
    protected String transporterName;
    @XmlElement(namespace = "http://orchestrateur.onwine.atod.afcepf.fr", required = true)
    protected String srcCurrency;
    @XmlElement(namespace = "http://orchestrateur.onwine.atod.afcepf.fr", required = true)
    protected String trgtCurrency;

    /**
     * Gets the value of the amount property.
     * 
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     */
    public void setAmount(double value) {
        this.amount = value;
    }

    /**
     * Gets the value of the quantity property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getQuantity() {
        return quantity;
    }

    /**
     * Sets the value of the quantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setQuantity(BigInteger value) {
        this.quantity = value;
    }

    /**
     * Gets the value of the countryCodeShipping property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountryCodeShipping() {
        return countryCodeShipping;
    }

    /**
     * Sets the value of the countryCodeShipping property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountryCodeShipping(String value) {
        this.countryCodeShipping = value;
    }

    /**
     * Gets the value of the countryCodeBilling property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountryCodeBilling() {
        return countryCodeBilling;
    }

    /**
     * Sets the value of the countryCodeBilling property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountryCodeBilling(String value) {
        this.countryCodeBilling = value;
    }

    /**
     * Gets the value of the transporterName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransporterName() {
        return transporterName;
    }

    /**
     * Sets the value of the transporterName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransporterName(String value) {
        this.transporterName = value;
    }

    /**
     * Gets the value of the srcCurrency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSrcCurrency() {
        return srcCurrency;
    }

    /**
     * Sets the value of the srcCurrency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSrcCurrency(String value) {
        this.srcCurrency = value;
    }

    /**
     * Gets the value of the trgtCurrency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrgtCurrency() {
        return trgtCurrency;
    }

    /**
     * Sets the value of the trgtCurrency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrgtCurrency(String value) {
        this.trgtCurrency = value;
    }

}
