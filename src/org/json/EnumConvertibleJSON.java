package org.json;

import java.util.Objects;

import org.json.JSONString;

/**
 * Fournit des m�thodes pour convertir une �num�ration en �l�ment JSON, en plus
 * d�une cha�ne de caract�res
 *
 * @param <E> type d��num�ration
 */
public interface EnumConvertibleJSON<E extends Enum<E>> extends JSONString {
    /**
     * Convertit un texte JSON en constante d��num�ration
     *
     * @param  <E>                  type d��num�ration
     * @param  enumeration          �num�ration
     * @param  valeur               texte JSON
     * @return                      la constante d��num�ration g�n�r�e
     * @throws NullPointerException si l��num�ration ou le texte JSON est nul
     */
    static <E extends Enum<E>> E fromJSONString( final Class<E> enumeration, final String valeur ) {
        return Enum.valueOf( Objects.requireNonNull( enumeration ), Objects.requireNonNull( valeur )
                                                                           .toUpperCase()
                                                                           .replace( ' ', '_' ) );
    }

    /**
     * Convertit la constante d��num�ration en texte JSON
     *
     * @return le texte JSON g�n�r�
     */
    @Override
    default String toJSONString() {
        return Objects.toString( this )
                      .toLowerCase()
                      .replace( '_', ' ' );
    }
}
