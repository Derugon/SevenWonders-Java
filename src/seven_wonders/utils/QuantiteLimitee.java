package seven_wonders.utils;

import java.util.Objects;
import java.util.function.IntConsumer;

/**
 * Quantit� limit�e
 */
public class QuantiteLimitee extends Quantite {
    /**
     * Identificateur
     */
    private static final long serialVersionUID = -2935092491284258737L;

    /**
     * Limite
     */
    private final int limite;

    /**
     * Cr�e une quantit� limit�e, � 0
     *
     * @param limite limite de la quantit�
     */
    public QuantiteLimitee( final int limite ) {
        super();
        if ( limite < 0 )
            throw new QuantiteInvalideException( limite );
        this.limite = limite;
    }

    /**
     * Cr�e une quantit� limit�e
     *
     * @param  valeur                    valeur de la quantit�
     * @param  limite                    limite de la quantit�
     * @throws QuantiteInvalideException si la valeur de la quantit� est n�gative
     */
    public QuantiteLimitee( final int valeur, final int limite ) {
        super( valeur );
        if ( limite < 0 )
            throw new QuantiteInvalideException( limite );
        this.limite = limite;
    }

    /**
     * Cr�e une quantit� limit�e
     *
     * @param  valeur                    valeur de la quantit�
     * @param  limite                    limite de la quantit�
     * @throws NullPointerException      si la valeur est nulle
     * @throws QuantiteInvalideException si la valeur de la quantit� est n�gative
     */
    public QuantiteLimitee( final Number valeur, final int limite ) {
        super( valeur );
        if ( limite < 0 )
            throw new QuantiteInvalideException( limite );
        this.limite = limite;
    }

    /**
     * Cr�e une copie d�une quantit� limit�e
     *
     * @param  autre                quantit� limit�e � copier
     * @throws NullPointerException si la quantit� limit�e est nulle
     */
    public QuantiteLimitee( final QuantiteLimitee autre ) {
        super( autre );
        limite = autre.limite;
    }

    /**
     * Ajoute une valeur � la quantit�
     *
     * @param  valeur                           valeur � ajouter � la quantit�
     * @return                                  la quantit�
     * @throws QuantiteInvalideException        si la valeur de la quantit� est
     *                                          n�gative
     * @throws QuantiteLimiteeInvalideException si la valeur de la quantit� d�passe
     *                                          la limite
     */
    @Override
    public QuantiteLimitee ajouter( final int valeur ) {
        super.ajouter( valeur );
        return this;
    }

    /**
     * Ajoute une valeur � la quantit�, et la limite � 0 au lieu de lancer une
     * exception
     *
     * @param  valeur valeur � ajouter � la quantit�
     * @param  reste  fonction � lancer s�il y a un reste
     * @return        la quantit�
     */
    @Override
    public QuantiteLimitee ajouterLimite( final int valeur, final IntConsumer reste ) {
        try {
            return ajouter( valeur );
        } catch ( @SuppressWarnings( "unused" ) final QuantiteLimiteeInvalideException e ) {
            final int temp = intValue();
            fixer( limite );
            reste.accept( temp + valeur - limite );
        } catch ( @SuppressWarnings( "unused" ) final QuantiteInvalideException e ) { /* ... */ }
        super.ajouterLimite( valeur, reste );
        return this;
    }

    @Override
    public QuantiteLimitee deepClone() {
        return new QuantiteLimitee( this );
    }

    /**
     * Fixe la valeur de la quantit�
     *
     * @param  valeur                           valeur de la quantit�
     * @return                                  la quantit�
     * @throws QuantiteInvalideException        si la valeur de la quantit� est
     *                                          n�gative
     * @throws QuantiteLimiteeInvalideException si la valeur de la quantit� d�passe
     *                                          la limite
     */
    @Override
    public QuantiteLimitee fixer( final int valeur ) {
        if ( valeur > limite )
            throw new QuantiteLimiteeInvalideException( limite, valeur );
        super.fixer( valeur );
        return this;
    }

    /**
     * Incr�mente la quantit�
     *
     * @return                                  la quantit�
     * @throws QuantiteLimiteeInvalideException si la valeur de la quantit� d�passe
     *                                          la limite (si elle est limit�e)
     */
    @Override
    public QuantiteLimitee incrementer() {
        super.incrementer();
        return this;
    }

    @Override
    public QuantiteLimitee incrementerLimite() {
        if ( intValue() != limite )
            super.incrementerLimite();
        return this;
    }

    @Override
    public QuantiteLimitee incrementerLimite( final IntConsumer limite ) {
        Objects.requireNonNull( limite );
        if ( intValue() == this.limite ) {
            limite.accept( 1 );
            return this;
        }
        return incrementer();
    }

    @Override
    public QuantiteLimitee incrementerLimite( final Procedure limite ) {
        Objects.requireNonNull( limite );
        if ( intValue() == this.limite ) {
            limite.executer();
            return this;
        }
        return incrementer();
    }

    /**
     * Indique la limite de la quantit�
     *
     * @return la limite de la quantit�
     */
    public int limite() {
        return limite;
    }

    @Override
    public QuantiteLimitee reinitialiser() {
        super.reinitialiser();
        return this;
    }

    /**
     * Retire une valeur de la quantit�
     *
     * @param  valeur                           valeur � retirer de la quantit�
     * @return                                  la quantit�
     * @throws QuantiteInvalideException        si la valeur de la quantit� est
     *                                          n�gative
     * @throws QuantiteLimiteeInvalideException si la valeur de la quantit� d�passe
     *                                          la limite
     */
    @Override
    public QuantiteLimitee retirer( final int valeur ) {
        super.retirer( valeur );
        return this;
    }

    /**
     * Retire une valeur de la quantit�, et la limite � 0 au lieu de lancer une
     * exception
     *
     * @param  valeur valeur � retirer de la quantit�
     * @param  reste  fonction � lancer s�il y a un reste
     * @return        la quantit�
     */
    @Override
    public QuantiteLimitee retirerLimite( final int valeur, final IntConsumer reste ) {
        try {
            return retirer( valeur );
        } catch ( @SuppressWarnings( "unused" ) final QuantiteLimiteeInvalideException e ) {
            final int temp = intValue();
            fixer( limite );
            reste.accept( valeur - temp + limite );
        } catch ( @SuppressWarnings( "unused" ) final QuantiteInvalideException e ) { /* ... */ }
        super.retirerLimite( valeur, reste );
        return this;
    }
}
