package seven_wonders.utils;

import java.util.Objects;
import java.util.function.IntConsumer;

/**
 * Quantit�
 */
public class Quantite extends Number implements Comparable<Number>, DeepCloneable<Quantite> {
    /**
     * Identificateur
     */
    private static final long serialVersionUID = 1494770605602731878L;

    private static final IntConsumer RESTE_VIDE     = reste -> { /* ... */ };
    private static final Procedure   PROCEDURE_VIDE = () -> { /* ... */ };

    /**
     * Cr�e une quantit� par la somme de quantit�s
     *
     * @param  quantites            quantit�s � additionner
     * @return                      la quantit� cr��e
     * @throws NullPointerException si une quantit� est nulle
     */
    public static Quantite somme( final Quantite... quantites ) {
        int total = 0;
        for ( final Quantite quantite : quantites )
            total += Objects.requireNonNull( quantite ).valeur;
        return new Quantite( total );
    }

    /**
     * Valeur de la quantit�
     */
    private int valeur;

    /**
     * Cr�e une quantit� � 0
     */
    public Quantite() {
        fixer( 0 );
    }

    /**
     * Cr�e une quantit�
     *
     * @param  valeur                    valeur de la quantit�
     * @throws QuantiteInvalideException si la valeur de la quantit� est n�gative
     */
    public Quantite( final int valeur ) {
        fixer( valeur );
    }

    /**
     * Cr�e une quantit�
     *
     * @param  valeur                    valeur de la quantit�
     * @throws NullPointerException      si la valeur est nulle
     * @throws QuantiteInvalideException si la valeur de la quantit� est n�gative
     */
    public Quantite( final Number valeur ) {
        fixer( Objects.requireNonNull( valeur )
                      .intValue() );
    }

    /**
     * Cr�e une copie d�une quantit�
     *
     * @param  autre                quantit� � copier
     * @throws NullPointerException si la quantit� est nulle
     */
    public Quantite( final Quantite autre ) {
        valeur = Objects.requireNonNull( autre ).valeur;
    }

    /**
     * Ajoute une valeur � la quantit�
     *
     * @param  valeur                    valeur � ajouter � la quantit�
     * @return                           la quantit�
     * @throws QuantiteInvalideException si la valeur de la quantit� est n�gative
     */
    public Quantite ajouter( final int valeur ) {
        return fixer( this.valeur + valeur );
    }

    /**
     * Ajoute une valeur � la quantit�, et la limite � 0 au lieu de lancer une
     * exception
     *
     * @param  valeur valeur � ajouter � la quantit�
     * @return        la quantit�
     */
    public Quantite ajouterLimite( final int valeur ) {
        return ajouterLimite( valeur, RESTE_VIDE );
    }

    /**
     * Ajoute une valeur � la quantit�, et la limite � 0 au lieu de lancer une
     * exception
     *
     * @param  valeur               valeur � ajouter � la quantit�
     * @param  reste                fonction � lancer s�il y a un reste
     * @return                      la quantit�
     * @throws NullPointerException si la fonction est nulle
     */
    public Quantite ajouterLimite( final int valeur, final IntConsumer reste ) {
        Objects.requireNonNull( reste );
        try {
            return fixer( this.valeur + valeur );
        } catch ( @SuppressWarnings( "unused" ) final QuantiteInvalideException e ) {
            final int temp = this.valeur;
            this.valeur = 0;
            reste.accept( -valeur - temp );
        }
        return this;
    }

    @Override
    public int compareTo( final Number o ) {
        return Integer.compare( valeur, o.intValue() );
    }

    /**
     * D�cr�mente la quantit�
     *
     * @return                           la quantit�
     * @throws QuantiteInvalideException si la valeur de la quantit� est n�gative
     */
    public Quantite decrementer() {
        return retirer( 1 );
    }

    /**
     * D�cr�mente la quantit�, et la limite � 0 au lieu de lancer une exception
     *
     * @return la quantit�
     */
    public Quantite decrementerLimite() {
        return decrementerLimite( PROCEDURE_VIDE );
    }

    /**
     * D�cr�mente la quantit�, et la limite � 0 au lieu de lancer une exception
     *
     * @param  limite               proc�dure � ex�cuter si la quantit� n�a pas �t�
     *                              d�cr�ment�e
     * @return                      la quantit�
     * @throws NullPointerException si la proc�dure est nulle
     */
    public Quantite decrementerLimite( final IntConsumer limite ) {
        Objects.requireNonNull( limite );
        if ( valeur == 0 ) {
            limite.accept( 1 );
            return this;
        }
        return retirer( 1 );
    }

    /**
     * D�cr�mente la quantit�, et la limite � 0 au lieu de lancer une exception
     *
     * @param  limite               proc�dure � ex�cuter si la quantit� n�a pas �t�
     *                              d�cr�ment�e
     * @return                      la quantit�
     * @throws NullPointerException si la proc�dure est nulle
     */
    public Quantite decrementerLimite( final Procedure limite ) {
        Objects.requireNonNull( limite );
        if ( valeur == 0 ) {
            limite.executer();
            return this;
        }
        return retirer( 1 );
    }

    @Override
    public Quantite deepClone() {
        return new Quantite( this );
    }

    @Override
    public double doubleValue() {
        return intValue();
    }

    /**
     * Fixe la valeur de la quantit�
     *
     * @param  valeur                    valeur de la quantit�
     * @return                           la quantit�
     * @throws QuantiteInvalideException si la valeur de la quantit� est n�gative
     */
    public Quantite fixer( final int valeur ) {
        if ( valeur < 0 )
            throw new QuantiteInvalideException( valeur );
        this.valeur = valeur;
        return this;
    }

    @Override
    public float floatValue() {
        return intValue();
    }

    /**
     * Incr�mente la quantit�
     *
     * @return la quantit�
     */
    public Quantite incrementer() {
        return ajouter( 1 );
    }

    /**
     * Incr�mente la quantit�, et la limite au lieu de lancer une exception
     *
     * @return la quantit�
     */
    public Quantite incrementerLimite() {
        return incrementer();
    }

    /**
     * Incr�mente la quantit�, et la limite au lieu de lancer une exception
     *
     * @param  limite               proc�dure � ex�cuter si la quantit� n�a pas �t�
     *                              incr�ment�e
     * @return                      la quantit�
     * @throws NullPointerException si la proc�dure est nulle
     */
    public Quantite incrementerLimite( final IntConsumer limite ) {
        Objects.requireNonNull( limite );
        return incrementerLimite();
    }

    /**
     * Incr�mente la quantit�, et la limite au lieu de lancer une exception
     *
     * @param  limite               proc�dure � ex�cuter si la quantit� n�a pas �t�
     *                              d�cr�ment�e
     * @return                      la quantit�
     * @throws NullPointerException si la proc�dure est nulle
     */
    public Quantite incrementerLimite( final Procedure limite ) {
        Objects.requireNonNull( limite );
        return incrementerLimite();
    }

    @Override
    public int intValue() {
        return valeur;
    }

    @Override
    public long longValue() {
        return intValue();
    }

    /**
     * R�initialise la quantit�
     *
     * @return la quantit�
     */
    public Quantite reinitialiser() {
        return fixer( 0 );
    }

    /**
     * Retire une valeur de la quantit�
     *
     * @param  valeur                    valeur � retirer de la quantit�
     * @return                           la quantit�
     * @throws QuantiteInvalideException si la valeur de la quantit� est n�gative
     */
    public Quantite retirer( final int valeur ) {
        return fixer( this.valeur - valeur );
    }

    /**
     * Retire une valeur de la quantit�, et la limite � 0 au lieu de lancer une
     * exception
     *
     * @param  valeur valeur � retirer de la quantit�
     * @return        la quantit�
     */
    public Quantite retirerLimite( final int valeur ) {
        return retirerLimite( valeur, RESTE_VIDE );
    }

    /**
     * Retire une valeur de la quantit�, et la limite � 0 au lieu de lancer une
     * exception
     *
     * @param  valeur               valeur � retirer de la quantit�
     * @param  reste                fonction � lancer s�il y a un reste
     * @return                      la quantit�
     * @throws NullPointerException si la fonction est nulle
     */
    public Quantite retirerLimite( final int valeur, final IntConsumer reste ) {
        Objects.requireNonNull( reste );
        try {
            return fixer( this.valeur - valeur );
        } catch ( @SuppressWarnings( "unused" ) final QuantiteInvalideException e ) {
            this.valeur = 0;
            reste.accept( valeur - this.valeur );
        }
        return this;
    }
}
