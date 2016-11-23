
package fr.afcepf.atod.onwine.ws.soap.orchestre;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for currenciesWSError.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="currenciesWSError">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CA_NE_FONCTIONNE_PAS"/>
 *     &lt;enumeration value="RECHERCHE_NON_PRESENTE_EN_BASE"/>
 *     &lt;enumeration value="IMPOSSIBLE_AJOUT_DANS_BASE"/>
 *     &lt;enumeration value="IMPOSSIBLE_SUPPRESSION_DANS_BASE"/>
 *     &lt;enumeration value="UPDATE_NON_EFFECTUE_EN_BASE"/>
 *     &lt;enumeration value="EMPTY_DB"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "currenciesWSError")
@XmlEnum
public enum CurrenciesWSError {

    CA_NE_FONCTIONNE_PAS,
    RECHERCHE_NON_PRESENTE_EN_BASE,
    IMPOSSIBLE_AJOUT_DANS_BASE,
    IMPOSSIBLE_SUPPRESSION_DANS_BASE,
    UPDATE_NON_EFFECTUE_EN_BASE,
    EMPTY_DB;

    public String value() {
        return name();
    }

    public static CurrenciesWSError fromValue(String v) {
        return valueOf(v);
    }

}
