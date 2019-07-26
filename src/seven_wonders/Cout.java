package seven_wonders;

import java.util.EnumMap;
import java.util.Objects;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import seven_wonders.utils.Quantite;

/**
 * Coût
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
     * Génère un coût à l’aide d’un conteneur XML
     *
     * @param  conteneur            élément XML contenant le(s) coût(s)
     * @return                      le coût généré
     * @throws NullPointerException si l’élément XML est nul
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
     * Coût en pièces
     */
    final Quantite                               pieces;
    /**
     * Coût en matières premières
     */
    final EnumMap<TypeMatierePremiere, Quantite> matieresPremieres;

    /**
     * Coût en produits manufacturés
     */
    final EnumMap<TypeProduitManufacture, Quantite> produitsManufactures;

    /**
     * Crée une copie d’un coût
     *
     * @param  autre                coût à copier
     * @throws NullPointerException si le coût est nul
     */
    public Cout( final Cout autre ) {
        Objects.requireNonNull( autre );
        pieces               = new Quantite( autre.pieces );
        matieresPremieres    = new EnumMap<>( autre.matieresPremieres );
        produitsManufactures = new EnumMap<>( autre.produitsManufactures );
    }

    /**
     * Crée un coût
     *
     * @param  matieresPremieres    coût en matières premières
     * @param  produitsManufactures coût en produits manufacturés
     * @throws NullPointerException si les pièces, les matières premières ou les
     *                              produits manufacturés sont nuls
     */
    public Cout( final EnumMap<TypeMatierePremiere, Quantite> matieresPremieres,
                 final EnumMap<TypeProduitManufacture, Quantite> produitsManufactures ) {
        this( new Quantite(), matieresPremieres, produitsManufactures );
    }

    /**
     * Crée un coût
     *
     * @param  pieces               coût en pièces
     * @throws NullPointerException si les pièces, les matières premières ou les
     *                              produits manufacturés sont nuls
     */
    public Cout( final Quantite pieces ) {
        this( pieces, new EnumMap<>( TypeMatierePremiere.class ), new EnumMap<>( TypeProduitManufacture.class ) );
    }

    /**
     * Crée un coût
     *
     * @param  pieces               coût en pièces
     * @param  matieresPremieres    coût en matières premières
     * @param  produitsManufactures coût en produits manufacturés
     * @throws NullPointerException si les pièces, les matières premières ou les
     *                              produits manufacturés sont nuls
     */
    public Cout( final Quantite pieces,
                 final EnumMap<TypeMatierePremiere, Quantite> matieresPremieres,
                 final EnumMap<TypeProduitManufacture, Quantite> produitsManufactures ) {
        this.pieces               = Objects.requireNonNull( pieces );
        this.matieresPremieres    = Objects.requireNonNull( matieresPremieres );
        this.produitsManufactures = Objects.requireNonNull( produitsManufactures );
    }
}
