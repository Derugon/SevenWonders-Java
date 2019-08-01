package seven_wonders;

import seven_wonders.effets.Effet;

/**
 * Carte de leader
 */
public class CarteLeader extends Carte {
    /**
     * Cr�e une copie d�une carte de leader
     *
     * @param  autre                carte de leader � copier
     * @throws NullPointerException si la carte de leader est nulle
     */
    public CarteLeader( final CarteLeader autre ) {
        super( autre );
    }

    /**
     * Cr�e une carte de leader
     *
     * @param  nom                  nom de la carte de leader
     * @param  cout                 co�t de la carte de leader
     * @param  effet                effet de la carte de leader
     * @throws NullPointerException si le nom, le co�t ou l�effet est nul
     */
    public CarteLeader( final String nom, final Cout cout, final Effet effet ) {
        super( nom, cout, effet );
    }

    @Override
    public CarteLeader affecter( final Joueur joueur ) {
        super.affecter( joueur );
        return this;
    }

    @Override
    public CarteLeader deepClone() {
        return new CarteLeader( this );
    }
}
