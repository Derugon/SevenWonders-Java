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
        super( Messages.getString( "QuantiteLimiteeInvalideException.message", Integer.toString( limite ) ) + valeur ); //$NON-NLS-1$
    }

    /**
     * Cr�e une exception
     *
     * @param message message � afficher
     */
    public QuantiteLimiteeInvalideException( final String message ) {
        super( message );
    }
}
