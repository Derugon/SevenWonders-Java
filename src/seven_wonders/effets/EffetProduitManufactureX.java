package seven_wonders.effets;

import java.util.Objects;

import org.json.JSONObject;

import seven_wonders.Joueur;
import seven_wonders.TypeProduitManufacture;
import seven_wonders.utils.JSON;
import seven_wonders.utils.Quantite;

/**
 * Effet produisant une matière première
 */
public class EffetProduitManufactureX extends EffetRessourceX {
    private static final String ATTRIBUT_TYPE = "type"; //$NON-NLS-1$

    static {
        Effet.ajouterType( "produit manufacture", objet -> new EffetProduitManufactureX( objet ) ); //$NON-NLS-1$
    }

    /**
     * Type de produit manufacturé produit
     */
    private final TypeProduitManufacture type;

    /**
     * Crée une copie d’un effet produisant un produit manufacturé
     *
     * @param  autre                effet à copier
     * @throws NullPointerException si l’effet est nul
     */
    public EffetProduitManufactureX( final EffetProduitManufactureX autre ) {
        super( autre );
        type = autre.type;
    }

    /**
     * Crée un effet produisant un produit manufacturé à partir d’un objet JSON
     *
     * @param  objet                objet JSON
     * @throws NullPointerException si l’objet JSON est nul
     */
    public EffetProduitManufactureX( final JSONObject objet ) {
        super( objet );
        type = JSON.toEnumConstant( TypeProduitManufacture.class, Objects.requireNonNull( objet )
                                                                         .getString( ATTRIBUT_TYPE ) );
    }

    /**
     * Crée un effet produisant une unité d’un produit manufacturé
     *
     * @param type type de produit manufacturé produit
     */
    public EffetProduitManufactureX( final TypeProduitManufacture type ) {
        super();
        this.type = type;
    }

    /**
     * Crée un effet produisant un produit manufacturé
     *
     * @param type     type de produit manufacturé produit
     * @param quantite quantité de produit manufacturé produit
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
     * Indique le type de produit manufacturé produit
     *
     * @return le type de produit manufacturé produit
     */
    @Override
    public TypeProduitManufacture type() {
        return type;
    }
}
