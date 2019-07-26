package seven_wonders;

import seven_wonders.utils.Messages;

/**
 * Exception lancée lorsqu’un élément ne peut être trouvé dans une liste XML
 */
public class ElementXMLInexistantException extends RuntimeException {
    /**
     * Identificateur
     */
    private static final long serialVersionUID = -5111879986405518533L;

    /**
     * Crée une exception
     *
     * @param message message à afficher
     */
    public ElementXMLInexistantException( final String message ) {
        super( message );
    }

    /**
     * Crée une exception
     *
     * @param balise balise
     * @param valeur valeur recherchée
     */
    public ElementXMLInexistantException( final String balise, final String valeur ) {
        super( Messages.getString( "ElementXMLInexistantException.message", balise, valeur ) ); //$NON-NLS-1$
    }
}
