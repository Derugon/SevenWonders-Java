package seven_wonders.effets;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import seven_wonders.Joueur;
import seven_wonders.TypeProduitManufacture;
import seven_wonders.utils.Quantite;

/**
 * Effet produisant une mati�re premi�re
 */
public class EffetProduitManufactureX extends EffetRessourceX {
    private static final String TYPE = "type"; //$NON-NLS-1$

    static {
        Effet.ajouterType( "produit manufacture", EffetProduitManufactureX::generer ); //$NON-NLS-1$
    }

    /**
     * G�n�re un effet � l�aide d�une node XML
     *
     * @param  node node XML
     * @return      l�effet g�n�r�
     */
    public static EffetProduitManufactureX generer( final Node node ) {
        final NamedNodeMap attributs = node.getAttributes();
        return new EffetProduitManufactureX( TypeProduitManufacture.valueOf( attributs.getNamedItem( TYPE )
                                                                                      .getTextContent()
                                                                                      .toUpperCase() ) );
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
        return super.equals( obj ) && obj instanceof EffetProduitManufactureX
               && type.equals( ( (EffetProduitManufactureX) obj ).type );
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + type.hashCode();
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
