package seven_wonders.effets;

import java.util.Objects;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.function.Function;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import seven_wonders.AttributXMLInvalideException;
import seven_wonders.Joueur;
import seven_wonders.utils.DeepCloneable;

/**
 * Effet
 */
public abstract class Effet implements DeepCloneable<Effet> {
    /**
     * Types d’effets
     */
    private static final TreeMap<String, Function<Node, ? extends Effet>> categoriesEffets = new TreeMap<>();

    private static final String ATTRIBUT_CATEGORIE = "categorie"; //$NON-NLS-1$
    private static final String BALISE_EFFET       = "effet";     //$NON-NLS-1$

    protected static final Consumer<Joueur> OBTENTION_VIDE = ( final Joueur joueur ) -> { /* ... */ };

    /**
     * Ajoute un type d’effet
     *
     * @param identificateur identificateur de l’effet
     * @param generateur     fonction génératrice de l’effet
     */
    public static void ajouterType( final String identificateur, final Function<Node, ? extends Effet> generateur ) {
        categoriesEffets.putIfAbsent( identificateur, generateur );
    }

    /**
     * Génère un effets à l’aide d’un conteneur XML
     *
     * @param  element              élément XML contenant un/des effet(s)
     * @return                      l’effet généré
     * @throws NullPointerException si l’élément XML est nul
     */
    public static Effet generer( final Element element ) {
        final NodeList effets = Objects.requireNonNull( element )
                                       .getElementsByTagName( BALISE_EFFET );
        if ( effets.getLength() == 0 )
            return new Effet() {
                @Override
                public Effet deepClone() {
                    return null;
                }
            };
        if ( effets.getLength() == 1 )
            return generer( effets.item( 0 ) );
        final EffetCombine effetCombine = new EffetCombine();
        for ( int i = 0 ; i < effets.getLength() ; ++i )
            effetCombine.ajouter( generer( effets.item( i ) ) );
        return effetCombine;
    }

    /**
     * Génère un effet à l’aide d’une node XML
     *
     * @param  node                 node XML
     * @return                      l’effet généré
     * @throws NullPointerException si la node XML est nulle
     */
    public static Effet generer( final Node node ) {
        final NamedNodeMap                    attributs  = Objects.requireNonNull( node )
                                                                  .getAttributes();
        final Function<Node, ? extends Effet> generateur = categoriesEffets.get( attributs.getNamedItem( ATTRIBUT_CATEGORIE )
                                                                                          .getTextContent() );
        if ( generateur == null )
            throw new AttributXMLInvalideException( ATTRIBUT_CATEGORIE, attributs );
        return generateur.apply( node );
    }

    /**
     * Joueur auquel s’applique l’effet
     */
    private Joueur joueur;

    /**
     * Action à l’obtention de l’effet
     */
    private final Consumer<Joueur> obtention;

    /**
     * Crée un effet vide
     */
    public Effet() {
        this( OBTENTION_VIDE );
    }

    /**
     * Crée un effet
     *
     * @param obtention action à l’obtention de l’effet
     */
    public Effet( final Consumer<Joueur> obtention ) {
        joueur         = null;
        this.obtention = obtention;
    }

    /**
     * Crée une copie d’un effet
     *
     * @param  autre                effet à copier
     * @throws NullPointerException si l’effet est nul
     */
    public Effet( final Effet autre ) {
        Objects.requireNonNull( autre );
        joueur    = autre.joueur;
        obtention = autre.obtention;
    }

    /**
     * Applique l’effet à un joueur
     *
     * @param  joueur joueur auquel s’applique l’effet
     * @return        l’effet
     */
    public Effet appliquer( final Joueur joueur ) {
        this.joueur = joueur;
        return this;
    }

    /**
     * Combine l’effet avec un autre
     *
     * @param  autre effet à combiner
     * @return       l’effet combiné
     */
    public Effet combiner( final Effet autre ) {
        return new EffetCombine( this, autre );
    }

    @Override
    public boolean equals( final Object obj ) {
        return obj instanceof Effet && joueur.equals( ( (Effet) obj ).joueur )
               && obtention.equals( ( (Effet) obj ).obtention );
    }

    @Override
    public int hashCode() {
        return 31 * joueur.hashCode() + obtention.hashCode();
    }

    /**
     * Indique le joueur auquel s’applique l’effet
     *
     * @return le joueur auquel s’applique l’effet
     */
    public Joueur joueur() {
        return joueur;
    }

    /**
     * Aplique l’effet à l’obtention
     */
    public void obtention() {
        obtention.accept( joueur );
    }
}
