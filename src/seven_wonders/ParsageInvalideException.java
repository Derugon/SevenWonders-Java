package seven_wonders;

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
        super( "�chec du parsage � " + parsage + " � de la cha�ne de caract�res suivante : " + valeur ); //$NON-NLS-1$ //$NON-NLS-2$
    }

    /**
     * Cr�e une exception
     *
     * @param message message � afficher
     * @param cause   cause de l�exception
     */
    public ParsageInvalideException( final String message, final Throwable cause ) {
        super( message, cause );
    }
}
