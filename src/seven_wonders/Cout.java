package seven_wonders;

import java.util.EnumMap;
import java.util.Objects;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import seven_wonders.utils.Quantite;

/**
 * Co�t
 */
public class Cout {
    private static final String ATTRIBUT_CATEGORIE  = "categorie";           //$NON-NLS-1$
    private static final String ATTRIBUT_QUANTITE   = "quantite";            //$NON-NLS-1$
    private static final String ATTRIBUT_TYPE       = "type";                //$NON-NLS-1$
    private static final String BALISE_COUT         = "cout";                //$NON-NLS-1$
    private static final String MATIERE_PREMIERE    = "matiere premiere";    //$NON-NLS-1$
    private static final String PIECES              = "pieces";              //$NON-NLS-1$
    private static final String PRODUIT_MANUFACTURE = "produit manufacture"; //$NON-NLS-1$

    /**
     * G�n�re un co�t � l�aide d�un conteneur XML
     *
     * @param  conteneur            �l�ment XML contenant le(s) co�t(s)
     * @return                      le co�t g�n�r�
     * @throws NullPointerException si l��l�ment XML est nul
     */
    public static Cout generer( final Element conteneur ) {
        final NodeList                                  nodes                = Objects.requireNonNull( conteneur )
                                                                                      .getElementsByTagName( BALISE_COUT );
        final Quantite                                  pieces               = new Quantite();
        final EnumMap<TypeMatierePremiere, Quantite>    matieresPremieres    = new EnumMap<>( TypeMatierePremiere.class );
        final EnumMap<TypeProduitManufacture, Quantite> produitsManufactures = new EnumMap<>( TypeProduitManufacture.class );
        for ( int i = 0 ; i < nodes.getLength() ; ++i ) {
            final NamedNodeMap attributs = nodes.item( i )
                                                .getAttributes();
            final String       categorie = attributs.getNamedItem( ATTRIBUT_CATEGORIE )
                                                    .getTextContent();
            switch ( categorie ) {
            case PIECES:
                pieces.ajouter( Integer.parseInt( attributs.getNamedItem( ATTRIBUT_QUANTITE )
                                                           .getTextContent() ) );
                break;
            case MATIERE_PREMIERE:
                matieresPremieres.compute( TypeMatierePremiere.valueOf( attributs.getNamedItem( ATTRIBUT_TYPE )
                                                                                 .getTextContent()
                                                                                 .toUpperCase() ),
                                           ( indice, quantite ) -> quantite == null
                                                   ? new Quantite( Integer.parseInt( attributs.getNamedItem( ATTRIBUT_QUANTITE )
                                                                                              .getTextContent() ) )
                                                   : quantite.ajouter( Integer.parseInt( attributs.getNamedItem( ATTRIBUT_QUANTITE )
                                                                                                  .getTextContent() ) ) );
                break;
            case PRODUIT_MANUFACTURE:
                produitsManufactures.compute( TypeProduitManufacture.valueOf( attributs.getNamedItem( ATTRIBUT_TYPE )
                                                                                       .getTextContent()
                                                                                       .toUpperCase() ),
                                              ( indice,
                                                quantite ) -> quantite
                                                              == null ? new Quantite( Integer.parseInt( attributs.getNamedItem( ATTRIBUT_QUANTITE )
                                                                                                                 .getTextContent() ) )
                                                                      : quantite.ajouter( Integer.parseInt( attributs.getNamedItem( ATTRIBUT_QUANTITE )
                                                                                                                     .getTextContent() ) ) );
                break;
            default:
                throw new AttributXMLInvalideException( ATTRIBUT_CATEGORIE, categorie );
            }
        }
        return new Cout( pieces, matieresPremieres, produitsManufactures );
    }

    /**
     * Co�t en pi�ces
     */
    final Quantite                               pieces;
    /**
     * Co�t en mati�res premi�res
     */
    final EnumMap<TypeMatierePremiere, Quantite> matieresPremieres;

    /**
     * Co�t en produits manufactur�s
     */
    final EnumMap<TypeProduitManufacture, Quantite> produitsManufactures;

    /**
     * Cr�e une copie d�un co�t
     *
     * @param  autre                co�t � copier
     * @throws NullPointerException si le co�t est nul
     */
    public Cout( final Cout autre ) {
        Objects.requireNonNull( autre );
        pieces               = new Quantite( autre.pieces );
        matieresPremieres    = new EnumMap<>( autre.matieresPremieres );
        produitsManufactures = new EnumMap<>( autre.produitsManufactures );
    }

    /**
     * Cr�e un co�t
     *
     * @param  matieresPremieres    co�t en mati�res premi�res
     * @param  produitsManufactures co�t en produits manufactur�s
     * @throws NullPointerException si les pi�ces, les mati�res premi�res ou les
     *                              produits manufactur�s sont nuls
     */
    public Cout( final EnumMap<TypeMatierePremiere, Quantite> matieresPremieres,
                 final EnumMap<TypeProduitManufacture, Quantite> produitsManufactures ) {
        this( new Quantite(), matieresPremieres, produitsManufactures );
    }

    /**
     * Cr�e un co�t
     *
     * @param  pieces               co�t en pi�ces
     * @throws NullPointerException si les pi�ces, les mati�res premi�res ou les
     *                              produits manufactur�s sont nuls
     */
    public Cout( final Quantite pieces ) {
        this( pieces, new EnumMap<>( TypeMatierePremiere.class ), new EnumMap<>( TypeProduitManufacture.class ) );
    }

    /**
     * Cr�e un co�t
     *
     * @param  pieces               co�t en pi�ces
     * @param  matieresPremieres    co�t en mati�res premi�res
     * @param  produitsManufactures co�t en produits manufactur�s
     * @throws NullPointerException si les pi�ces, les mati�res premi�res ou les
     *                              produits manufactur�s sont nuls
     */
    public Cout( final Quantite pieces,
                 final EnumMap<TypeMatierePremiere, Quantite> matieresPremieres,
                 final EnumMap<TypeProduitManufacture, Quantite> produitsManufactures ) {
        this.pieces               = Objects.requireNonNull( pieces );
        this.matieresPremieres    = Objects.requireNonNull( matieresPremieres );
        this.produitsManufactures = Objects.requireNonNull( produitsManufactures );
    }
}
