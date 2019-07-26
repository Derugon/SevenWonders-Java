package seven_wonders;

import java.util.EnumMap;

/**
 * Type de carte d��ge
 */
public enum TypeCarteAge {
    /**
     * B�timent de cit�
     */
    CITE( true, true, true ),
    /**
     * B�timent civil
     */
    CIVIL( true, true, true ),
    /**
     * B�timent commercial
     */
    COMMERCIAL( true, true, true ),
    /**
     * Guilde
     */
    GUILDE( false, false, true ),
    /**
     * Mati�re premi�re
     */
    MATIERE_PREMIERE( true, true, false ),
    /**
     * B�timent militaire
     */
    MILITAIRE( true, true, true ),
    /**
     * Produit manufactur�
     */
    PRODUIT_MANUFACTURE( true, true, false ),
    /**
     * B�timent scientifique
     */
    SCIENTIFIQUE( true, true, true );

    /**
     * Diposibilit� du type de carte � chaque �ge
     */
    private final EnumMap<Age, Boolean> ages;

    private TypeCarteAge( final boolean... ages ) {
        this.ages = new EnumMap<>( Age.class );
        for ( int i = 0 ; i < Age.values().length ; ++i )
            this.ages.put( Age.values()[i], ages[i] );
    }

    /**
     * Indique si le type de carte est pr�sent � l��ge donn�
     *
     * @param  age �ge � tester
     * @return     vrai si le type de carte est pr�sent et faux sinon
     */
    public boolean estPresent( final Age age ) {
        return ages.get( age );
    }
}
