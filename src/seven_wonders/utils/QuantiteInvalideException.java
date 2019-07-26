package seven_wonders.utils;

/**
 * Exception lancée lorsqu’une quantité obtient une valeur négative
 */
public class QuantiteInvalideException extends RuntimeException {
    /**
     * Identificateur
     */
    private static final long serialVersionUID = -3539664814095202474L;

    /**
     * Crée une exception
     *
     * @param valeur valeur obtenue
     */
    public QuantiteInvalideException( final int valeur ) {
        super( Messages.getString( "QuantiteInvalideException.message" ) + valeur ); //$NON-NLS-1$
    }

    /**
     * Crée une exception
     *
     * @param message message à afficher
     */
    public QuantiteInvalideException( final String message ) {
        super( message );
    }
}
