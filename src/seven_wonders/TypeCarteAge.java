package seven_wonders;

import java.util.EnumMap;

/**
 * Type de carte d’âge
 */
public enum TypeCarteAge {
    /**
     * Bâtiment de cité
     */
    CITE( true, true, true ),
    /**
     * Bâtiment civil
     */
    CIVIL( true, true, true ),
    /**
     * Bâtiment commercial
     */
    COMMERCIAL( true, true, true ),
    /**
     * Guilde
     */
    GUILDE( false, false, true ),
    /**
     * Matière première
     */
    MATIERE_PREMIERE( true, true, false ),
    /**
     * Bâtiment militaire
     */
    MILITAIRE( true, true, true ),
    /**
     * Produit manufacturé
     */
    PRODUIT_MANUFACTURE( true, true, false ),
    /**
     * Bâtiment scientifique
     */
    SCIENTIFIQUE( true, true, true );

    /**
     * Diposibilité du type de carte à chaque âge
     */
    private final EnumMap<Age, Boolean> ages;

    private TypeCarteAge( final boolean... ages ) {
        this.ages = new EnumMap<>( Age.class );
        for ( int i = 0 ; i < Age.values().length ; ++i )
            this.ages.put( Age.values()[i], ages[i] );
    }

    /**
     * Indique si le type de carte est présent à l’âge donné
     *
     * @param  age âge à tester
     * @return     vrai si le type de carte est présent et faux sinon
     */
    public boolean estPresent( final Age age ) {
        return ages.get( age );
    }
}
