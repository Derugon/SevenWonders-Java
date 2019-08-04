package seven_wonders;

import java.util.EnumMap;
import java.util.Objects;

import org.json.AttributJSONInvalideException;
import org.json.JSONArray;
import org.json.JSONObject;

import seven_wonders.utils.Quantite;

/**
 * Coût
 */
public class Cout {
    private static final String ATTRIBUT_CATEGORIE  = "categorie";           //$NON-NLS-1$
    private static final String ATTRIBUT_TYPE       = "type";                //$NON-NLS-1$
    private static final String MATIERE_PREMIERE    = "matiere premiere";    //$NON-NLS-1$
    private static final String PIECES              = "pieces";              //$NON-NLS-1$
    private static final String PRODUIT_MANUFACTURE = "produit manufacture"; //$NON-NLS-1$

    /**
     * Nom par défaut d’un attribut contenant un coût
     */
    public static final String ATTRIBUT = "cout"; //$NON-NLS-1$

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
     * Crée un coût à partir d’une liste JSON
     *
     * @param  liste                liste JSON
     * @throws NullPointerException si la liste JSON est nulle
     */
    public Cout( final JSONArray liste ) {
        Objects.requireNonNull( liste );
        pieces               = new Quantite();
        matieresPremieres    = new EnumMap<>( TypeMatierePremiere.class );
        produitsManufactures = new EnumMap<>( TypeProduitManufacture.class );
        liste.forEach( objet -> ajouter( (JSONObject) objet ) );
    }

    /**
     * Crée un coût à partir d’un objet JSON
     *
     * @param  objet                objet JSON
     * @throws NullPointerException si l’objet JSON est nul
     */
    public Cout( final JSONObject objet ) {
        Objects.requireNonNull( objet );
        pieces               = new Quantite();
        matieresPremieres    = new EnumMap<>( TypeMatierePremiere.class );
        produitsManufactures = new EnumMap<>( TypeProduitManufacture.class );
        ajouter( objet );
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

    /**
     * Ajoute un objet JSON au coût
     *
     * @param  objet                objet JSON à ajouter
     * @throws NullPointerException si l’objet JSON est nul
     */
    private void ajouter( final JSONObject objet ) {
        final String categorie = Objects.requireNonNull( objet )
                                        .getString( ATTRIBUT_CATEGORIE );
        switch ( categorie ) {
        case PIECES:
            pieces.ajouter( objet.getInt( Quantite.ATTRIBUT ) );
            break;
        case MATIERE_PREMIERE:
            matieresPremieres.compute( TypeMatierePremiere.valueOf( objet.getString( ATTRIBUT_TYPE )
                                                                         .toUpperCase() ),
                                       ( indice, quantite ) -> quantite == null
                                               ? new Quantite( Integer.parseInt( objet.getString( Quantite.ATTRIBUT ) ) )
                                               : quantite.ajouter( Integer.parseInt( objet.getString( Quantite.ATTRIBUT ) ) ) );
            break;
        case PRODUIT_MANUFACTURE:
            produitsManufactures.compute( TypeProduitManufacture.valueOf( objet.getString( ATTRIBUT_TYPE )
                                                                               .toUpperCase() ),
                                          ( indice, quantite ) -> quantite == null
                                                  ? new Quantite( Integer.parseInt( objet.getString( Quantite.ATTRIBUT ) ) )
                                                  : quantite.ajouter( Integer.parseInt( objet.getString( Quantite.ATTRIBUT ) ) ) );
            break;
        default:
            throw new AttributJSONInvalideException( ATTRIBUT_CATEGORIE, categorie );
        }
    }
}
