package seven_wonders;

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
        super( "échec du parsage « " + parsage + " » de la chaîne de caractères suivante : " + valeur ); //$NON-NLS-1$ //$NON-NLS-2$
    }

    /**
     * Crée une exception
     *
     * @param message message à afficher
     * @param cause   cause de l’exception
     */
    public ParsageInvalideException( final String message, final Throwable cause ) {
        super( message, cause );
    }
}
