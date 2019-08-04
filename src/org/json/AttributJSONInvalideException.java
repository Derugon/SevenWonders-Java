package org.json;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Exception lanc�e lorsqu�un attribut JSON poss�de une valeur invalide
 */
public class AttributJSONInvalideException extends JSONException {
    /**
     * Identificateur
     */
    private static final long serialVersionUID = -6162169607413860984L;

    /**
     * Cr�e une exception
     *
     * @param message message � afficher
     */
    public AttributJSONInvalideException( final String message ) {
        super( message );
    }

    /**
     * Cr�e une exception
     *
     * @param attribut attribut
     * @param objet    objet JSON ayant lanc� l�exception
     */
    public AttributJSONInvalideException( final String attribut, final JSONObject objet ) {
        this( attribut, objet.get( attribut ) );
    }

    /**
     * Cr�e une exception
     *
     * @param attribut attribut
     * @param valeur   valeur de l�attribut
     */
    public AttributJSONInvalideException( final String attribut, final Object valeur ) {
        this( attribut, valeur.toString() );
    }

    /**
     * Cr�e une exception
     *
     * @param attribut attribut
     * @param valeur   valeur de l�attribut
     */
    public AttributJSONInvalideException( final String attribut, final String valeur ) {
        super( "valeur de l�attribut JSON � " + attribut + " � invalide : " + valeur ); //$NON-NLS-1$ //$NON-NLS-2$
    }

    /**
     * Cr�e une exception
     *
     * @param message message � afficher
     * @param cause   cause de l�exception
     */
    public AttributJSONInvalideException( final String message, final Throwable cause ) {
        super( message, cause );
    }
}
