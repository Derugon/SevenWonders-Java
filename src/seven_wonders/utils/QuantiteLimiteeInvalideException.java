package seven_wonders.utils;

/**
 * Exception lancée lorsqu’une quantité obtient une valeur négative
 */
public class QuantiteLimiteeInvalideException extends RuntimeException {
    /**
     * Identificateur
     */
    private static final long serialVersionUID = -3539664814095202474L;

    /**
     * Crée une exception
     *
     * @param limite limite de la quantité
     * @param valeur valeur obtenue
     */
    public QuantiteLimiteeInvalideException( final int limite, final int valeur ) {
        super( Messages.getString( "QuantiteLimiteeInvalideException.message", Integer.toString( limite ) ) + valeur ); //$NON-NLS-1$
    }

    /**
     * Crée une exception
     *
     * @param message message à afficher
     */
    public QuantiteLimiteeInvalideException( final String message ) {
        super( message );
    }
}
