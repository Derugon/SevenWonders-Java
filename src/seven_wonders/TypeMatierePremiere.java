package seven_wonders;

import org.json.EnumConvertibleJSON;

/**
 * Type de mati�re premi�re
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
     * Convertit un texte JSON en constante d��num�ration
     *
     * @param  valeur               texte JSON
     * @return                      la constante d��num�ration g�n�r�e
     * @throws NullPointerException si le texte JSON est nul
     */
    public static TypeMatierePremiere fromJSONString( final String valeur ) {
        return EnumConvertibleJSON.fromJSONString( TypeMatierePremiere.class, valeur );
    }
}
