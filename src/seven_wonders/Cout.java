package seven_wonders;

import java.util.EnumMap;
import java.util.Objects;

import org.json.AttributJSONInvalideException;
import org.json.JSONArray;
import org.json.JSONObject;

import seven_wonders.utils.Quantite;

/**
 * Co�t
 */
public class Cout {
    private static final String ATTRIBUT_CATEGORIE  = "categorie";           //$NON-NLS-1$
    private static final String ATTRIBUT_TYPE       = "type";                //$NON-NLS-1$
    private static final String MATIERE_PREMIERE    = "matiere premiere";    //$NON-NLS-1$
    private static final String PIECES              = "pieces";              //$NON-NLS-1$
    private static final String PRODUIT_MANUFACTURE = "produit manufacture"; //$NON-NLS-1$

    /**
     * Nom par d�faut d�un attribut contenant un co�t
     */
    public static final String ATTRIBUT = "cout"; //$NON-NLS-1$

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
     * Cr�e un co�t � partir d�une liste JSON
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
     * Cr�e un co�t � partir d�un objet JSON
     *
     * @param  objet                objet JSON
     * @throws NullPointerException si l�objet JSON est nul
     */
    public Cout( final JSONObject objet ) {
        Objects.requireNonNull( objet );
        pieces               = new Quantite();
        matieresPremieres    = new EnumMap<>( TypeMatierePremiere.class );
        produitsManufactures = new EnumMap<>( TypeProduitManufacture.class );
        ajouter( objet );
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

    /**
     * Ajoute un objet JSON au co�t
     *
     * @param  objet                objet JSON � ajouter
     * @throws NullPointerException si l�objet JSON est nul
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
