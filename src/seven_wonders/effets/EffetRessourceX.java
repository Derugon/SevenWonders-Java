package seven_wonders.effets;

import java.util.TreeMap;
import java.util.function.BiFunction;

import seven_wonders.Joueur;
import seven_wonders.TypeRessource;
import seven_wonders.utils.Quantite;
import seven_wonders.utils.QuantiteLimitee;
import seven_wonders.utils.QuantiteLimiteeInvalideException;

/**
 * Effet produisant une ressource
 */
public abstract class EffetRessourceX extends Effet {
    private static final BiFunction<Joueur, QuantiteLimitee, QuantiteLimitee> REINITIALISER = ( joueur,
                                                                                                quantite ) -> quantite.reinitialiser();

    /**
     * Quantité de ressource produite
     */
    private final Quantite                         quantite;
    /**
     * Quantité de ressource utilisée par les joueurs
     */
    private final TreeMap<Joueur, QuantiteLimitee> utilisation;

    /**
     * Crée un effet produisant une unité d’une ressource
     */
    public EffetRessourceX() {
        this( new Quantite( 1 ) );
    }

    /**
     * Crée une copie d’un effet produisant une ressource
     *
     * @param  autre                effet à copier
     * @throws NullPointerException si l’effet est nul
     */
    public EffetRessourceX( final EffetRessourceX autre ) {
        super( autre );
        quantite    = new Quantite( autre.quantite );
        utilisation = new TreeMap<>( autre.utilisation );
    }

    /**
     * Crée un effet produisant une ressource
     *
     * @param quantite quantité de ressource produite
     */
    public EffetRessourceX( final Quantite quantite ) {
        super( OBTENTION_VIDE );
        this.quantite = quantite;
        utilisation   = new TreeMap<>();
    }

    @Override
    public EffetRessourceX affecter( final Joueur joueur ) {
        super.affecter( joueur );
        return this;
    }

    @Override
    public EffetRessourceX appliquer( final Joueur joueur ) {
        super.appliquer( joueur );
        return this;
    }

    @Override
    public abstract EffetRessourceX deepClone();

    @Override
    public boolean equals( final Object obj ) {
        return super.equals( obj ) && obj instanceof EffetRessourceX
               && quantite.equals( ( (EffetRessourceX) obj ).quantite );
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + quantite.hashCode();
    }

    /**
     * Indique la quantité de ressource produite
     *
     * @return la quantité de ressource produite
     */
    public Quantite quantite() {
        return quantite;
    }

    /**
     * Réinitialise les ressources utilisées
     */
    public void reinitialiser() {
        utilisation.replaceAll( REINITIALISER );
    }

    /**
     * Indique le type de ressource produite
     *
     * @return le type de ressource produite
     */
    public abstract TypeRessource type();

    /**
     * Utilise une unité de la ressource produite
     *
     * @return vrai si la ressource est utilisable et faux sinon
     */
    public boolean utiliser() {
        return utiliser( joueur() );
    }

    /**
     * Utilise une unité de la ressource produite
     *
     * @param  joueur joueur utilisant la ressource
     * @return        vrai si la ressource est utilisable et faux sinon
     */
    public boolean utiliser( final Joueur joueur ) {
        return utiliser( joueur, new Quantite( 1 ) );
    }

    /**
     * Utilise la ressource produite
     *
     * @param  joueur   joueur utilisant la ressource
     * @param  quantite quantité de ressource à utiliser
     * @return          vrai si la ressource est utilisable et faux sinon
     */
    public boolean utiliser( final Joueur joueur, final Quantite quantite ) {
        try {
            utilisation.put( joueur, utilisation.getOrDefault( joueur, new QuantiteLimitee( this.quantite.intValue() ) )
                                                .ajouter( quantite.intValue() ) );
            return true;
        } catch ( @SuppressWarnings( "unused" ) final QuantiteLimiteeInvalideException e ) {
            return false;
        }
    }

    /**
     * Utilise la ressource produite
     *
     * @param  quantite quantité de ressource à utiliser
     * @return          vrai si la ressource est utilisable et faux sinon
     */
    public boolean utiliser( final Quantite quantite ) {
        return utiliser( joueur(), quantite );
    }
}
