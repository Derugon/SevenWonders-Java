package seven_wonders.effets;

import java.util.Objects;

import org.json.JSONObject;

import seven_wonders.Joueur;
import seven_wonders.TypeMatierePremiere;
import seven_wonders.utils.Quantite;

/**
 * Effet produisant une mati�re premi�re
 */
public class EffetMatierePremiere extends EffetRessourceX {
    private static final String ATTRIBUT_TYPE = "type"; //$NON-NLS-1$

    static {
        Effet.ajouterType( "matiere premiere", objet -> new EffetMatierePremiere( objet ) ); //$NON-NLS-1$
    }

    /**
     * Type de mati�re premi�re produite
     */
    private final TypeMatierePremiere type;

    /**
     * Cr�e une copie d�un effet produisant une mati�re premi�re
     *
     * @param  autre                effet � copier
     * @throws NullPointerException si l�effet est nul
     */
    public EffetMatierePremiere( final EffetMatierePremiere autre ) {
        super( autre );
        type = autre.type;
    }

    /**
     * Cr�e un effet produisant une unit� d�une mati�re premi�re � partir d�un objet
     * JSON
     *
     * @param  objet                objet JSON
     * @throws NullPointerException si l�objet JSON est nul
     */
    public EffetMatierePremiere( final JSONObject objet ) {
        super( objet );
        type = TypeMatierePremiere.fromJSONString( Objects.requireNonNull( objet )
                                                          .getString( ATTRIBUT_TYPE ) );
    }

    /**
     * Cr�e un effet produisant une unit� d�une mati�re premi�re
     *
     * @param type type de mati�re premi�re produite
     */
    public EffetMatierePremiere( final TypeMatierePremiere type ) {
        super();
        this.type = type;
    }

    /**
     * Cr�e un effet produisant une mati�re premi�re
     *
     * @param type     type de mati�re premi�re produite
     * @param quantite quantit� de mati�re premi�re produite
     */
    public EffetMatierePremiere( final TypeMatierePremiere type, final Quantite quantite ) {
        super( quantite );
        this.type = type;
    }

    @Override
    public EffetMatierePremiere affecter( final Joueur joueur ) {
        super.affecter( joueur );
        return this;
    }

    @Override
    public Effet combiner( final Effet autre ) {
        return autre instanceof EffetMatierePremiere && joueur().equals( autre.joueur() )
               && type.equals( ( (EffetMatierePremiere) autre ).type )
                       ? new EffetMatierePremiere( type,
                                                   Quantite.somme( quantite(),
                                                                   ( (EffetMatierePremiere) autre ).quantite() ) )
                       : super.combiner( autre );
    }

    @Override
    public EffetMatierePremiere deepClone() {
        return new EffetMatierePremiere( this );
    }

    @Override
    public boolean equals( final Object obj ) {
        return super.equals( obj ) && obj instanceof EffetMatierePremiere
               && type.equals( ( (EffetMatierePremiere) obj ).type );
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + type.hashCode();
    }

    @Override
    public JSONObject toJSONObject() {
        return super.toJSONObject().put( ATTRIBUT_TYPE, type.toJSONString() );
    }

    /**
     * Indique le type de mati�re premi�re produite
     *
     * @return le type de mati�re premi�re produite
     */
    @Override
    public TypeMatierePremiere type() {
        return type;
    }
}
