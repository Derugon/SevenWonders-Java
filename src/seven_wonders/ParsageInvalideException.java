package seven_wonders;

import seven_wonders.utils.Messages;

/**
 * Exception lancée lorsqu’un parsage de chaîne de caractère échoue
 */
public class ParsageInvalideException extends RuntimeException {
    /**
     * Identificateur
     */
    private static final long serialVersionUID = 4796629449464165761L;

    /**
     * Crée une exception
     *
     * @param message message à afficher
     */
    public ParsageInvalideException( final String message ) {
        super( message );
    }

    /**
     * Crée une exception
     *
     * @param parsage parsage réalisé
     * @param valeur  valeur parsée
     */
    public ParsageInvalideException( final String parsage, final String valeur ) {
        super( Messages.getString( "ParsageInvalideException.message", parsage ) + valeur ); //$NON-NLS-1$
    }
}
