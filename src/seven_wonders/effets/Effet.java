package seven_wonders.effets;

import java.util.Objects;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.function.Function;

import org.json.AttributJSONInvalideException;
import org.json.ConvertibleJSON;
import org.json.JSONArray;
import org.json.JSONObject;

import seven_wonders.Joueur;
import seven_wonders.Possedable;
import seven_wonders.utils.DeepCloneable;

/**
 * Effet
 */
public abstract class Effet implements DeepCloneable<Effet>, ConvertibleJSON, Possedable {
    /**
     * Types d�effets
     */
    private static final TreeMap<String, Function<JSONObject, ? extends Effet>> categoriesEffets = new TreeMap<>();

    private static final String ATTRIBUT_CATEGORIE = "categorie"; //$NON-NLS-1$

    protected static final Consumer<Joueur> OBTENTION_VIDE = ( final Joueur joueur ) -> { /* ... */ };

    /**
     * G�n�re un effet � l�aide d�une liste JSON
     *
     * @param  effets               liste JSON
     * @return                      l�effet g�n�r�
     * @throws NullPointerException si la liste JSON est nulle
     */
    public static Effet generer( final JSONArray effets ) {
        switch ( effets.length() ) {
        case 0:
            return vide();
        case 1:
            return generer( (JSONObject) effets.get( 0 ) );
        default:
            final EffetCombine effetCombine = new EffetCombine();
            for ( final Object effet : effets )
                effetCombine.ajouter( generer( (JSONObject) effet ) );
            return effetCombine;
        }
    }

    /**
     * G�n�re un effet � l�aide d�un objet JSON
     *
     * @param  effet                objet JSON
     * @return                      l�effet g�n�r�
     * @throws NullPointerException si l�objet JSON est nul
     */
    public static Effet generer( final JSONObject effet ) {
        final Function<JSONObject, ? extends Effet> generateur = categoriesEffets.get( effet.get( ATTRIBUT_CATEGORIE ) );
        if ( generateur == null )
            throw new AttributJSONInvalideException( ATTRIBUT_CATEGORIE, effet );
        return generateur.apply( effet );
    }

    /**
     * Cr�e un effet vide
     *
     * @return un effet vide
     */
    public static Effet vide() {
        return new EffetVide();
    }

    /**
     * Ajoute un type d�effet
     *
     * @param identificateur identificateur de l�effet
     * @param generateur     fonction g�n�ratrice de l�effet
     */
    protected static void ajouterType( final String identificateur,
                                       final Function<JSONObject, ? extends Effet> generateur ) {
        categoriesEffets.putIfAbsent( identificateur, generateur );
    }

    /**
     * Joueur auquel s�applique l�effet
     */
    private Joueur joueur;

    /**
     * Action � l�obtention de l�effet
     */
    private final Consumer<Joueur> obtention;

    /**
     * Cr�e un effet vide
     */
    public Effet() {
        this( OBTENTION_VIDE );
    }

    /**
     * Cr�e un effet
     *
     * @param obtention action � l�obtention de l�effet
     */
    public Effet( final Consumer<Joueur> obtention ) {
        joueur         = null;
        this.obtention = obtention;
    }

    /**
     * Cr�e une copie d�un effet
     *
     * @param  autre                effet � copier
     * @throws NullPointerException si l�effet est nul
     */
    public Effet( final Effet autre ) {
        Objects.requireNonNull( autre );
        joueur    = autre.joueur;
        obtention = autre.obtention;
    }

    @Override
    public Effet affecter( final Joueur joueur ) {
        this.joueur = joueur;
        return this;
    }

    /**
     * Applique l�effet � un joueur
     *
     * @param  joueur joueur auquel s�applique l�effet
     * @return        l�effet
     */
    public Effet appliquer( final Joueur joueur ) {
        this.joueur = joueur;
        return this;
    }

    /**
     * Combine l�effet avec un autre
     *
     * @param  autre effet � combiner
     * @return       l�effet combin�
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
     * Indique le joueur auquel s�applique l�effet
     *
     * @return le joueur auquel s�applique l�effet
     */
    public Joueur joueur() {
        return joueur;
    }

    /**
     * Aplique l�effet � l�obtention
     */
    public void obtention() {
        obtention.accept( joueur );
    }

    @Override
    public Joueur possesseur() {
        return joueur;
    }
}

/**
 * Effet vide
 */
final class EffetVide extends Effet {
    @Override
    public Effet deepClone() {
        return new EffetVide();
    }
}
