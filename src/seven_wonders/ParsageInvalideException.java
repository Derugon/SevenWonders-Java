package seven_wonders;

import seven_wonders.utils.Messages;

/**
 * Exception lanc�e lorsqu�un parsage de cha�ne de caract�re �choue
 */
public class ParsageInvalideException extends RuntimeException {
    /**
     * Identificateur
     */
    private static final long serialVersionUID = 4796629449464165761L;

    /**
     * Cr�e une exception
     *
     * @param message message � afficher
     */
    public ParsageInvalideException( final String message ) {
        super( message );
    }

    /**
     * Cr�e une exception
     *
     * @param parsage parsage r�alis�
     * @param valeur  valeur pars�e
     */
    public ParsageInvalideException( final String parsage, final String valeur ) {
        super( Messages.getString( "ParsageInvalideException.message", parsage ) + valeur ); //$NON-NLS-1$
    }
}
