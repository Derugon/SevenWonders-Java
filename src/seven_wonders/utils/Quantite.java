package seven_wonders.utils;

import java.util.Objects;
import java.util.function.IntConsumer;
import java.util.function.Procedure;

import org.json.JSONString;

/**
 * Quantité
 */
public class Quantite extends Number implements Comparable<Number>, DeepCloneable<Quantite>, JSONString {
    /**
     * Identificateur
     */
    private static final long serialVersionUID = 1494770605602731878L;

    private static final IntConsumer RESTE_VIDE = reste -> { /* ... */ };

    /**
     * Nom par défaut d’un attribut contenant une quantité
     */
    public static final String ATTRIBUT = "quantite"; //$NON-NLS-1$

    /**
     * Crée une quantité par la somme de quantités
     *
     * @param  quantites            quantités à additionner
     * @return                      la quantité créée
     * @throws NullPointerException si une quantité est nulle
     */
    public static Quantite somme( final Quantite... quantites ) {
        int total = 0;
        for ( final Quantite quantite : quantites )
            total += Objects.requireNonNull( quantite ).valeur;
        return new Quantite( total );
    }

    /**
     * Valeur de la quantité
     */
    private int valeur;

    /**
     * Crée une quantité à 0
     */
    public Quantite() {
        fixer( 0 );
    }

    /**
     * Crée une quantité
     *
     * @param  valeur                    valeur de la quantité
     * @throws QuantiteInvalideException si la valeur de la quantité est négative
     */
    public Quantite( final int valeur ) {
        fixer( valeur );
    }

    /**
     * Crée une quantité
     *
     * @param  valeur                    valeur de la quantité
     * @throws NullPointerException      si la valeur est nulle
     * @throws QuantiteInvalideException si la valeur de la quantité est négative
     */
    public Quantite( final Number valeur ) {
        fixer( Objects.requireNonNull( valeur )
                      .intValue() );
    }

    /**
     * Crée une copie d’une quantité
     *
     * @param  autre                quantité à copier
     * @throws NullPointerException si la quantité est nulle
     */
    public Quantite( final Quantite autre ) {
        valeur = Objects.requireNonNull( autre ).valeur;
    }

    /**
     * Ajoute une valeur à la quantité
     *
     * @param  valeur                    valeur à ajouter à la quantité
     * @return                           la quantité
     * @throws QuantiteInvalideException si la valeur de la quantité est négative
     */
    public Quantite ajouter( final int valeur ) {
        return fixer( this.valeur + valeur );
    }

    /**
     * Ajoute une valeur à la quantité, et la limite à 0 au lieu de lancer une
     * exception
     *
     * @param  valeur valeur à ajouter à la quantité
     * @return        la quantité
     */
    public Quantite ajouterLimite( final int valeur ) {
        return ajouterLimite( valeur, RESTE_VIDE );
    }

    /**
     * Ajoute une valeur à la quantité, et la limite à 0 au lieu de lancer une
     * exception
     *
     * @param  valeur               valeur à ajouter à la quantité
     * @param  reste                fonction à lancer s’il y a un reste
     * @return                      la quantité
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
     * Décrémente la quantité
     *
     * @return                           la quantité
     * @throws QuantiteInvalideException si la valeur de la quantité est négative
     */
    public Quantite decrementer() {
        return retirer( 1 );
    }

    /**
     * Décrémente la quantité, et la limite à 0 au lieu de lancer une exception
     *
     * @return la quantité
     */
    public Quantite decrementerLimite() {
        return decrementerLimite( Procedure.VIDE );
    }

    /**
     * Décrémente la quantité, et la limite à 0 au lieu de lancer une exception
     *
     * @param  limite               procédure à exécuter si la quantité n’a pas été
     *                              décrémentée
     * @return                      la quantité
     * @throws NullPointerException si la procédure est nulle
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
     * Décrémente la quantité, et la limite à 0 au lieu de lancer une exception
     *
     * @param  limite               procédure à exécuter si la quantité n’a pas été
     *                              décrémentée
     * @return                      la quantité
     * @throws NullPointerException si la procédure est nulle
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

    @Override
    public boolean equals( final Object obj ) {
        return obj instanceof Quantite && valeur == ( (Quantite) obj ).valeur;
    }

    /**
     * Fixe la valeur de la quantité
     *
     * @param  valeur                    valeur de la quantité
     * @return                           la quantité
     * @throws QuantiteInvalideException si la valeur de la quantité est négative
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

    @Override
    public int hashCode() {
        return valeur;
    }

    /**
     * Incrémente la quantité
     *
     * @return la quantité
     */
    public Quantite incrementer() {
        return ajouter( 1 );
    }

    /**
     * Incrémente la quantité, et la limite au lieu de lancer une exception
     *
     * @return la quantité
     */
    public Quantite incrementerLimite() {
        return incrementer();
    }

    /**
     * Incrémente la quantité, et la limite au lieu de lancer une exception
     *
     * @param  limite               procédure à exécuter si la quantité n’a pas été
     *                              incrémentée
     * @return                      la quantité
     * @throws NullPointerException si la procédure est nulle
     */
    public Quantite incrementerLimite( final IntConsumer limite ) {
        Objects.requireNonNull( limite );
        return incrementerLimite();
    }

    /**
     * Incrémente la quantité, et la limite au lieu de lancer une exception
     *
     * @param  limite               procédure à exécuter si la quantité n’a pas été
     *                              décrémentée
     * @return                      la quantité
     * @throws NullPointerException si la procédure est nulle
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
     * Réinitialise la quantité
     *
     * @return la quantité
     */
    public Quantite reinitialiser() {
        return fixer( 0 );
    }

    /**
     * Retire une valeur de la quantité
     *
     * @param  valeur                    valeur à retirer de la quantité
     * @return                           la quantité
     * @throws QuantiteInvalideException si la valeur de la quantité est négative
     */
    public Quantite retirer( final int valeur ) {
        return fixer( this.valeur - valeur );
    }

    /**
     * Retire une valeur de la quantité, et la limite à 0 au lieu de lancer une
     * exception
     *
     * @param  valeur valeur à retirer de la quantité
     * @return        la quantité
     */
    public Quantite retirerLimite( final int valeur ) {
        return retirerLimite( valeur, RESTE_VIDE );
    }

    /**
     * Retire une valeur de la quantité, et la limite à 0 au lieu de lancer une
     * exception
     *
     * @param  valeur               valeur à retirer de la quantité
     * @param  reste                fonction à lancer s’il y a un reste
     * @return                      la quantité
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

    @Override
    public String toJSONString() {
        return toString();
    }

    @Override
    public String toString() {
        return Integer.toString( valeur );
    }
}
