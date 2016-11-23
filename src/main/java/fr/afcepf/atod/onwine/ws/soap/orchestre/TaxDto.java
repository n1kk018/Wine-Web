
package fr.afcepf.atod.onwine.ws.soap.orchestre;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for taxDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="taxDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codePays" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nomPays" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tva" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "taxDto", namespace = "http://dto.biz.tax.ws.al28.afcepf.fr", propOrder = {
    "codePays",
    "nomPays",
    "tva"
})
public class TaxDto {

    protected String codePays;
    protected String nomPays;
    protected Double tva;

    /**
     * Gets the value of the codePays property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodePays() {
        return codePays;
    }

    /**
     * Sets the value of the codePays property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodePays(String value) {
        this.codePays = value;
    }

    /**
     * Gets the value of the nomPays property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomPays() {
        return nomPays;
    }

    /**
     * Sets the value of the nomPays property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomPays(String value) {
        this.nomPays = value;
    }

    /**
     * Gets the value of the tva property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTva() {
        return tva;
    }

    /**
     * Sets the value of the tva property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTva(Double value) {
        this.tva = value;
    }

}
