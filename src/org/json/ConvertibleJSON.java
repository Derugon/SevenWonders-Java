package org.json;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;

/**
 * Fournit des méthodes pour convertir en élément JSON, en plus d’une chaîne de
 * caractères
 */
public interface ConvertibleJSON extends JSONString {
    /**
     * Indique si l’objet est convertible en objet JSON
     *
     * @return vrai si l’objet est convertible en objet JSON et faux sinon
     */
    default boolean estJSONObject() {
        return false;
    }

    /**
     * Convertit l’objet en liste JSON
     *
     * @return la liste JSON générée
     */
    default JSONArray toJSONArray() {
        return new JSONArray().put( toJSONObject() );
    }

    /**
     * Convertit l’objet en objet JSON
     *
     * @return l’objet JSON généré ou nul si la conversion n’est pas possible
     */
    default JSONObject toJSONObject() {
        return null;
    }

    @Override
    default String toJSONString() {
        return ( estJSONObject() ? toJSONObject() : toJSONArray() ).toString();
    }
}
