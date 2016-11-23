
package fr.afcepf.atod.onwine.ws.soap.orchestre;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for multiplication complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="multiplication">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="v1" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="v2" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "multiplication", namespace = "http://service.al28.afcepf.fr/", propOrder = {
    "v1",
    "v2"
})
public class Multiplication {

    protected double v1;
    protected double v2;

    /**
     * Gets the value of the v1 property.
     * 
     */
    public double getV1() {
        return v1;
    }

    /**
     * Sets the value of the v1 property.
     * 
     */
    public void setV1(double value) {
        this.v1 = value;
    }

    /**
     * Gets the value of the v2 property.
     * 
     */
    public double getV2() {
        return v2;
    }

    /**
     * Sets the value of the v2 property.
     * 
     */
    public void setV2(double value) {
        this.v2 = value;
    }

}
