package org.json;

/**
 * Exception lanc�e lorsqu�un �l�ment ne peut �tre trouv� dans une liste ou un
 * objet JSON
 */
public class ElementJSONInexistantException extends RuntimeException {
    /**
     * Identificateur
     */
    private static final long serialVersionUID = -5111879986405518533L;

    /**
     * Cr�e une exception
     *
     * @param message message � afficher
     */
    public ElementJSONInexistantException( final String message ) {
        super( message );
    }

    /**
     * Cr�e une exception
     *
     * @param element nom de l��l�ment JSON
     * @param type    type de valeur attendue
     */
    public ElementJSONInexistantException( final String element, final String type ) {
        super( "�l�ment <" + element + "> du type � " + type + " � introuvable" ); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }

    /**
     * Cr�e une exception
     *
     * @param message message � afficher
     * @param cause   cause de l�exception
     */
    public ElementJSONInexistantException( final String message, final Throwable cause ) {
        super( message, cause );
    }
}
