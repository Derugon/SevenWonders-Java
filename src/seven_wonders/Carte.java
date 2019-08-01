package seven_wonders;

import java.util.Objects;

import seven_wonders.effets.Effet;
import seven_wonders.utils.DeepCloneable;

/**
 * Carte de jeu
 */
public abstract class Carte implements DeepCloneable<Carte>, Possedable {
    /**
     * Nom de la carte
     */
    private final String nom;
    /**
     * Coût de la carte
     */
    private final Cout   cout;
    /**
     * Effet de la carte
     */
    private final Effet  effet;
    /**
     * Joueur possédant la carte
     */
    private Joueur       joueur;

    /**
     * Crée une copie d’une carte
     *
     * @param  autre                carte à copier
     * @throws NullPointerException si la carte est nulle
     */
    public Carte( final Carte autre ) {
        Objects.requireNonNull( autre );
        nom   = autre.nom;
        cout  = new Cout( autre.cout );
        effet = autre.effet.deepClone();
    }

    /**
     * Crée une carte
     *
     * @param  nom                  nom de la carte
     * @param  cout                 coût de la carte
     * @param  effet                effet de la carte
     * @throws NullPointerException si le nom, le coût ou l’effet est nul
     */
    public Carte( final String nom, final Cout cout, final Effet effet ) {
        this.nom   = Objects.requireNonNull( nom );
        this.cout  = Objects.requireNonNull( cout );
        this.effet = Objects.requireNonNull( effet );
    }

    @Override
    public Carte affecter( final Joueur joueur ) {
        this.joueur = joueur;
        return this;
    }

    @Override
    public boolean equals( final Object obj ) {
        return obj instanceof Carte && nom.equals( ( (Carte) obj ).nom );
    }

    @Override
    public int hashCode() {
        return nom.hashCode();
    }

    /**
     * Indique le nom de la carte
     *
     * @return le nom de la carte
     */
    public String nom() {
        return nom;
    }

    @Override
    public Joueur possesseur() {
        return joueur;
    }
}
