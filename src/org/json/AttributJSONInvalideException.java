package org.json;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Exception lancée lorsqu’un attribut JSON possède une valeur invalide
 */
public class AttributJSONInvalideException extends JSONException {
    /**
     * Identificateur
     */
    private static final long serialVersionUID = -6162169607413860984L;

    /**
     * Crée une exception
     *
     * @param message message à afficher
     */
    public AttributJSONInvalideException( final String message ) {
        super( message );
    }

    /**
     * Crée une exception
     *
     * @param attribut attribut
     * @param objet    objet JSON ayant lancé l’exception
     */
    public AttributJSONInvalideException( final String attribut, final JSONObject objet ) {
        this( attribut, objet.get( attribut ) );
    }

    /**
     * Crée une exception
     *
     * @param attribut attribut
     * @param valeur   valeur de l’attribut
     */
    public AttributJSONInvalideException( final String attribut, final Object valeur ) {
        this( attribut, valeur.toString() );
    }

    /**
     * Crée une exception
     *
     * @param attribut attribut
     * @param valeur   valeur de l’attribut
     */
    public AttributJSONInvalideException( final String attribut, final String valeur ) {
        super( "valeur de l’attribut JSON « " + attribut + " » invalide : " + valeur ); //$NON-NLS-1$ //$NON-NLS-2$
    }

    /**
     * Crée une exception
     *
     * @param message message à afficher
     * @param cause   cause de l’exception
     */
    public AttributJSONInvalideException( final String message, final Throwable cause ) {
        super( message, cause );
    }
}
