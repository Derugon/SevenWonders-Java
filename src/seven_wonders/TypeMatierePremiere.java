package seven_wonders;

import org.json.EnumConvertibleJSON;

/**
 * Type de matière première
 */
public enum TypeMatierePremiere implements TypeRessource<TypeMatierePremiere> {
    /**
     * Bois
     */
    BOIS,
    /**
     * Pierre
     */
    PIERRE,
    /**
     * Minerai
     */
    MINERAI,
    /**
     * Argile
     */
    ARGILE;

    /**
     * Convertit un texte JSON en constante d’énumération
     *
     * @param  valeur               texte JSON
     * @return                      la constante d’énumération générée
     * @throws NullPointerException si le texte JSON est nul
     */
    public static TypeMatierePremiere fromJSONString( final String valeur ) {
        return EnumConvertibleJSON.fromJSONString( TypeMatierePremiere.class, valeur );
    }
}
