package seven_wonders.effets;

import java.util.Objects;

import org.json.JSONObject;

import seven_wonders.Joueur;
import seven_wonders.TypeProduitManufacture;
import seven_wonders.utils.JSON;
import seven_wonders.utils.Quantite;

/**
 * Effet produisant une mati�re premi�re
 */
public class EffetProduitManufactureX extends EffetRessourceX {
    private static final String ATTRIBUT_TYPE = "type"; //$NON-NLS-1$

    static {
        Effet.ajouterType( "produit manufacture", objet -> new EffetProduitManufactureX( objet ) ); //$NON-NLS-1$
    }

    /**
     * Type de produit manufactur� produit
     */
    private final TypeProduitManufacture type;

    /**
     * Cr�e une copie d�un effet produisant un produit manufactur�
     *
     * @param  autre                effet � copier
     * @throws NullPointerException si l�effet est nul
     */
    public EffetProduitManufactureX( final EffetProduitManufactureX autre ) {
        super( autre );
        type = autre.type;
    }

    /**
     * Cr�e un effet produisant un produit manufactur� � partir d�un objet JSON
     *
     * @param  objet                objet JSON
     * @throws NullPointerException si l�objet JSON est nul
     */
    public EffetProduitManufactureX( final JSONObject objet ) {
        super( objet );
        type = JSON.toEnumConstant( TypeProduitManufacture.class, Objects.requireNonNull( objet )
                                                                         .getString( ATTRIBUT_TYPE ) );
    }

    /**
     * Cr�e un effet produisant une unit� d�un produit manufactur�
     *
     * @param type type de produit manufactur� produit
     */
    public EffetProduitManufactureX( final TypeProduitManufacture type ) {
        super();
        this.type = type;
    }

    /**
     * Cr�e un effet produisant un produit manufactur�
     *
     * @param type     type de produit manufactur� produit
     * @param quantite quantit� de produit manufactur� produit
     */
    public EffetProduitManufactureX( final TypeProduitManufacture type, final Quantite quantite ) {
        super( quantite );
        this.type = type;
    }

    @Override
    public EffetProduitManufactureX affecter( final Joueur joueur ) {
        super.affecter( joueur );
        return this;
    }

    @Override
    public Effet combiner( final Effet autre ) {
        return autre instanceof EffetProduitManufactureX && joueur().equals( autre.joueur() )
               && type.equals( ( (EffetProduitManufactureX) autre ).type )
                       ? new EffetProduitManufactureX( type,
                                                       Quantite.somme( quantite(),
                                                                       ( (EffetProduitManufactureX) autre ).quantite() ) )
                       : super.combiner( autre );
    }

    @Override
    public EffetProduitManufactureX deepClone() {
        return new EffetProduitManufactureX( this );
    }

    @Override
    public boolean equals( final Object obj ) {
        return obj instanceof EffetProduitManufactureX && super.equals( obj )
               && type.equals( ( (EffetProduitManufactureX) obj ).type );
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + type.hashCode();
    }

    @Override
    public JSONObject toJSONObject() {
        return super.toJSONObject().put( ATTRIBUT_TYPE, JSON.fromEnumConstant( type ) );
    }

    /**
     * Indique le type de produit manufactur� produit
     *
     * @return le type de produit manufactur� produit
     */
    @Override
    public TypeProduitManufacture type() {
        return type;
    }
}
