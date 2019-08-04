package org.json;

import java.util.Objects;

import org.json.JSONString;

/**
 * Fournit des méthodes pour convertir une énumération en élément JSON, en plus
 * d’une chaîne de caractères
 *
 * @param <E> type d’énumération
 */
public interface EnumConvertibleJSON<E extends Enum<E>> extends JSONString {
    /**
     * Convertit un texte JSON en constante d’énumération
     *
     * @param  <E>                  type d’énumération
     * @param  enumeration          énumération
     * @param  valeur               texte JSON
     * @return                      la constante d’énumération générée
     * @throws NullPointerException si l’énumération ou le texte JSON est nul
     */
    static <E extends Enum<E>> E fromJSONString( final Class<E> enumeration, final String valeur ) {
        return Enum.valueOf( Objects.requireNonNull( enumeration ), Objects.requireNonNull( valeur )
                                                                           .toUpperCase()
                                                                           .replace( ' ', '_' ) );
    }

    /**
     * Convertit la constante d’énumération en texte JSON
     *
     * @return le texte JSON généré
     */
    @Override
    default String toJSONString() {
        return Objects.toString( this )
                      .toLowerCase()
                      .replace( '_', ' ' );
    }
}
