package seven_wonders;

import org.json.EnumConvertibleJSON;

/**
 * Type de produit manufactur�
 */
public enum TypeProduitManufacture implements TypeRessource<TypeProduitManufacture> {
    /**
     * Papier
     */
    PAPIER,
    /**
     * Tissu
     */
    TISSU,
    /**
     * Verre
     */
    VERRE;

    /**
     * Convertit un texte JSON en constante d��num�ration
     *
     * @param  valeur               texte JSON
     * @return                      la constante d��num�ration g�n�r�e
     * @throws NullPointerException si le texte JSON est nul
     */
    public static TypeProduitManufacture fromJSONString( final String valeur ) {
        return EnumConvertibleJSON.fromJSONString( TypeProduitManufacture.class, valeur );
    }
}
