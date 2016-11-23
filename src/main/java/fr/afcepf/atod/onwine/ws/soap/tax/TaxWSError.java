
package fr.afcepf.atod.onwine.ws.soap.tax;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for taxWSError.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="taxWSError">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CA_NE_FONCTIONNE_PAS"/>
 *     &lt;enumeration value="IMPOSSIBLE_AJOUT_DANS_BASE"/>
 *     &lt;enumeration value="IMPOSSIBLE_SUPPRESSION_DANS_BASE"/>
 *     &lt;enumeration value="RECHERCHE_NON_PRESENTE_EN_BASE"/>
 *     &lt;enumeration value="UPDATE_NON_EFFECTUE_EN_BASE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "taxWSError")
@XmlEnum
public enum TaxWSError {

    CA_NE_FONCTIONNE_PAS,
    IMPOSSIBLE_AJOUT_DANS_BASE,
    IMPOSSIBLE_SUPPRESSION_DANS_BASE,
    RECHERCHE_NON_PRESENTE_EN_BASE,
    UPDATE_NON_EFFECTUE_EN_BASE;

    public String value() {
        return name();
    }

    public static TaxWSError fromValue(String v) {
        return valueOf(v);
    }

}
