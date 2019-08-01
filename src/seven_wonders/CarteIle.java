package seven_wonders;

import seven_wonders.effets.Effet;

/**
 * Carte d��le
 */
public class CarteIle extends Carte {
    /**
     * Niveau d�une carte d��le
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
     * Niveau de la carte d��le
     */
    private final Niveau niveau;

    /**
     * Cr�e une copie d�une carte d��le
     *
     * @param  autre                carte d��le � copier
     * @throws NullPointerException si la carte d��le est nulle
     */
    public CarteIle( final CarteIle autre ) {
        super( autre );
        niveau = autre.niveau;
    }

    /**
     * Cr�e une carte d��le
     *
     * @param  nom                  nom de la carte
     * @param  cout                 co�t de la carte
     * @param  effet                effet de la carte
     * @param  niveau               niveau de la carte d��le
     * @throws NullPointerException si le nom, le co�t ou l�effet est nul
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
     * Indique le niveau de la carte d��le
     *
     * @return le niveau de la carte d��le
     */
    public Niveau niveau() {
        return niveau;
    }
}
