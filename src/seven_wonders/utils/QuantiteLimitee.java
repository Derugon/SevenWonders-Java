package seven_wonders.utils;

import java.util.Objects;
import java.util.function.IntConsumer;

/**
 * Quantité limitée
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
     * Crée une quantité limitée, à 0
     *
     * @param limite limite de la quantité
     */
    public QuantiteLimitee( final int limite ) {
        super();
        if ( limite < 0 )
            throw new QuantiteInvalideException( limite );
        this.limite = limite;
    }

    /**
     * Crée une quantité limitée
     *
     * @param  valeur                    valeur de la quantité
     * @param  limite                    limite de la quantité
     * @throws QuantiteInvalideException si la valeur de la quantité est négative
     */
    public QuantiteLimitee( final int valeur, final int limite ) {
        super( valeur );
        if ( limite < 0 )
            throw new QuantiteInvalideException( limite );
        this.limite = limite;
    }

    /**
     * Crée une quantité limitée
     *
     * @param  valeur                    valeur de la quantité
     * @param  limite                    limite de la quantité
     * @throws NullPointerException      si la valeur est nulle
     * @throws QuantiteInvalideException si la valeur de la quantité est négative
     */
    public QuantiteLimitee( final Number valeur, final int limite ) {
        super( valeur );
        if ( limite < 0 )
            throw new QuantiteInvalideException( limite );
        this.limite = limite;
    }

    /**
     * Crée une copie d’une quantité limitée
     *
     * @param  autre                quantité limitée à copier
     * @throws NullPointerException si la quantité limitée est nulle
     */
    public QuantiteLimitee( final QuantiteLimitee autre ) {
        super( autre );
        limite = autre.limite;
    }

    /**
     * Ajoute une valeur à la quantité
     *
     * @param  valeur                           valeur à ajouter à la quantité
     * @return                                  la quantité
     * @throws QuantiteInvalideException        si la valeur de la quantité est
     *                                          négative
     * @throws QuantiteLimiteeInvalideException si la valeur de la quantité dépasse
     *                                          la limite
     */
    @Override
    public QuantiteLimitee ajouter( final int valeur ) {
        super.ajouter( valeur );
        return this;
    }

    /**
     * Ajoute une valeur à la quantité, et la limite à 0 au lieu de lancer une
     * exception
     *
     * @param  valeur valeur à ajouter à la quantité
     * @param  reste  fonction à lancer s’il y a un reste
     * @return        la quantité
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
     * Fixe la valeur de la quantité
     *
     * @param  valeur                           valeur de la quantité
     * @return                                  la quantité
     * @throws QuantiteInvalideException        si la valeur de la quantité est
     *                                          négative
     * @throws QuantiteLimiteeInvalideException si la valeur de la quantité dépasse
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
     * Incrémente la quantité
     *
     * @return                                  la quantité
     * @throws QuantiteLimiteeInvalideException si la valeur de la quantité dépasse
     *                                          la limite (si elle est limitée)
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
     * Indique la limite de la quantité
     *
     * @return la limite de la quantité
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
     * Retire une valeur de la quantité
     *
     * @param  valeur                           valeur à retirer de la quantité
     * @return                                  la quantité
     * @throws QuantiteInvalideException        si la valeur de la quantité est
     *                                          négative
     * @throws QuantiteLimiteeInvalideException si la valeur de la quantité dépasse
     *                                          la limite
     */
    @Override
    public QuantiteLimitee retirer( final int valeur ) {
        super.retirer( valeur );
        return this;
    }

    /**
     * Retire une valeur de la quantité, et la limite à 0 au lieu de lancer une
     * exception
     *
     * @param  valeur valeur à retirer de la quantité
     * @param  reste  fonction à lancer s’il y a un reste
     * @return        la quantité
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
