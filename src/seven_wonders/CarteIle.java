package seven_wonders;

import seven_wonders.effets.Effet;

/**
 * Carte d’île
 */
public class CarteIle extends Carte {
    /**
     * Niveau d’une carte d’île
     */
    public enum Niveau {
        /**
         * Niveau 1
         */
        I,
        /**
         * Niveau 2
         */
        II,
        /**
         * Niveau 3
         */
        III
    }

    /**
     * Niveau de la carte d’île
     */
    private final Niveau niveau;

    /**
     * Crée une copie d’une carte d’île
     *
     * @param  autre                carte d’île à copier
     * @throws NullPointerException si la carte d’île est nulle
     */
    public CarteIle( final CarteIle autre ) {
        super( autre );
        niveau = autre.niveau;
    }

    /**
     * Crée une carte d’île
     *
     * @param  nom                  nom de la carte
     * @param  cout                 coût de la carte
     * @param  effet                effet de la carte
     * @param  niveau               niveau de la carte d’île
     * @throws NullPointerException si le nom, le coût ou l’effet est nul
     */
    public CarteIle( final String nom, final Cout cout, final Effet effet, final Niveau niveau ) {
        super( nom, cout, effet );
        this.niveau = niveau;
    }

    @Override
    public CarteIle affecter( final Joueur joueur ) {
        super.affecter( joueur );
        return this;
    }

    @Override
    public CarteIle deepClone() {
        return new CarteIle( this );
    }

    /**
     * Indique le niveau de la carte d’île
     *
     * @return le niveau de la carte d’île
     */
    public Niveau niveau() {
        return niveau;
    }
}
