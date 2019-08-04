package org.json;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;

/**
 * Fournit des m�thodes pour convertir en �l�ment JSON, en plus d�une cha�ne de
 * caract�res
 */
public interface ConvertibleJSON extends JSONString {
    /**
     * Indique si l�objet est convertible en objet JSON
     *
     * @return vrai si l�objet est convertible en objet JSON et faux sinon
     */
    default boolean estJSONObject() {
        return false;
    }

    /**
     * Convertit l�objet en liste JSON
     *
     * @return la liste JSON g�n�r�e
     */
    default JSONArray toJSONArray() {
        return new JSONArray().put( toJSONObject() );
    }

    /**
     * Convertit l�objet en objet JSON
     *
     * @return l�objet JSON g�n�r� ou nul si la conversion n�est pas possible
     */
    default JSONObject toJSONObject() {
        return null;
    }

    @Override
    default String toJSONString() {
        return ( estJSONObject() ? toJSONObject() : toJSONArray() ).toString();
    }
}
