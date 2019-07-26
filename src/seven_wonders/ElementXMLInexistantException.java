package seven_wonders;

import seven_wonders.utils.Messages;

/**
 * Exception lanc�e lorsqu�un �l�ment ne peut �tre trouv� dans une liste XML
 */
public class ElementXMLInexistantException extends RuntimeException {
    /**
     * Identificateur
     */
    private static final long serialVersionUID = -5111879986405518533L;

    /**
     * Cr�e une exception
     *
     * @param message message � afficher
     */
    public ElementXMLInexistantException( final String message ) {
        super( message );
    }

    /**
     * Cr�e une exception
     *
     * @param balise balise
     * @param valeur valeur recherch�e
     */
    public ElementXMLInexistantException( final String balise, final String valeur ) {
        super( Messages.getString( "ElementXMLInexistantException.message", balise, valeur ) ); //$NON-NLS-1$
    }
}
