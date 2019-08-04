package seven_wonders.utils;

/**
 * Exception lanc�e lorsqu�une quantit� obtient une valeur n�gative
 */
public class QuantiteLimiteeInvalideException extends RuntimeException {
    /**
     * Identificateur
     */
    private static final long serialVersionUID = -3539664814095202474L;

    /**
     * Cr�e une exception
     *
     * @param limite limite de la quantit�
     * @param valeur valeur obtenue
     */
    public QuantiteLimiteeInvalideException( final int limite, final int valeur ) {
        super( "quantit� limite de " + limite + " d�pass�e, quantit� obtenue : " + valeur ); //$NON-NLS-1$ //$NON-NLS-2$
    }

    /**
     * Cr�e une exception
     *
     * @param message message � afficher
     */
    public QuantiteLimiteeInvalideException( final String message ) {
        super( message );
    }

    /**
     * Cr�e une exception
     *
     * @param message message � afficher
     * @param cause   cause de l�exception
     */
    public QuantiteLimiteeInvalideException( final String message, final Throwable cause ) {
        super( message, cause );
    }
}
