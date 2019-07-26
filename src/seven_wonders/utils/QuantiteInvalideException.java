package seven_wonders.utils;

/**
 * Exception lanc�e lorsqu�une quantit� obtient une valeur n�gative
 */
public class QuantiteInvalideException extends RuntimeException {
    /**
     * Identificateur
     */
    private static final long serialVersionUID = -3539664814095202474L;

    /**
     * Cr�e une exception
     *
     * @param valeur valeur obtenue
     */
    public QuantiteInvalideException( final int valeur ) {
        super( Messages.getString( "QuantiteInvalideException.message" ) + valeur ); //$NON-NLS-1$
    }

    /**
     * Cr�e une exception
     *
     * @param message message � afficher
     */
    public QuantiteInvalideException( final String message ) {
        super( message );
    }
}
