package org.json;

/**
 * Exception lancée lorsqu’un élément ne peut être trouvé dans une liste ou un
 * objet JSON
 */
public class ElementJSONInexistantException extends RuntimeException {
    /**
     * Identificateur
     */
    private static final long serialVersionUID = -5111879986405518533L;

    /**
     * Crée une exception
     *
     * @param message message à afficher
     */
    public ElementJSONInexistantException( final String message ) {
        super( message );
    }

    /**
     * Crée une exception
     *
     * @param element nom de l’élément JSON
     * @param type    type de valeur attendue
     */
    public ElementJSONInexistantException( final String element, final String type ) {
        super( "élément <" + element + "> du type « " + type + " » introuvable" ); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }

    /**
     * Crée une exception
     *
     * @param message message à afficher
     * @param cause   cause de l’exception
     */
    public ElementJSONInexistantException( final String message, final Throwable cause ) {
        super( message, cause );
    }
}
