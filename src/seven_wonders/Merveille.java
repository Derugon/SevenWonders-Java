package seven_wonders;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import seven_wonders.effets.Effet;
import seven_wonders.utils.ConvertibleJSON;

/**
 * Merveille
 */
public class Merveille implements ConvertibleJSON, Possedable {
    /**
     * �tape d�une merveille
     */
    public class Etape {
        /**
         * Co�t de l��tape
         */
        private final Cout  cout;
        /**
         * Effet de l��tape
         */
        private final Effet effet;

        /**
         * Cr�e une �tape d�une merveille
         *
         * @param cout  co�t de l��tape
         * @param effet effet de l��tape
         */
        public Etape( final Cout cout, final Effet effet ) {
            this.cout  = cout;
            this.effet = effet;
        }
    }

    private static JSONArray MERVEILLES = null;

    static {
        try {
            MERVEILLES = new JSONArray( new String( Files.readAllBytes( Paths.get( "Merveilles.json" ) ) ) );
        } catch ( final JSONException | IOException e ) {
            e.printStackTrace();
        }
    }

    /**
     * Nom de la merveille
     */
    private final String  nom;
    /**
     * Effet de la merveille
     */
    private final Effet   effet;
    /**
     * �tapes de la merveille
     */
    private final Etape[] etapes;
    /**
     * Joueur poss�dant la merveille
     */
    private Joueur        joueur;

    /**
     * Cr�e une merveille
     *
     * @param nom   nom de la merveille
     * @param faceB si la face b doit �tre prise au lieu de la face a
     */
    public Merveille( final String nom, final boolean faceB ) {
        Effet         effet   = null;
        final boolean trouvee = false;
        for ( int i = 0 ; !trouvee ; ++i ) {
            if ( i >= MERVEILLES.length() )
                throw new IllegalArgumentException( "merveille inexistante�:" + nom ); //$NON-NLS-1$
            final JSONObject merveille = MERVEILLES.getJSONObject( i );
            final String     attrNom   = merveille.getString( "nom" ); //$NON-NLS-1$
            if ( attrNom.equals( nom ) ) {
                final JSONObject face = merveille.getJSONObject( faceB ? "b" : "a" ); //$NON-NLS-1$ //$NON-NLS-2$
                try {
                    effet = Effet.generer( face.getJSONArray( "effets" ) ); //$NON-NLS-1$
                } catch ( final JSONException e ) {
                    try {
                        effet = Effet.generer( face.getJSONObject( "effet" ) ); //$NON-NLS-1$
                    } catch ( final JSONException e2 ) { /* ... */ }
                }
            }
        }
        this.nom   = nom;
        this.effet = effet == null ? Effet.vide() : effet;
    }

    @Override
    public Merveille affecter( final Joueur joueur ) {
        this.joueur = joueur;
        return this;
    }

    /**
     * Indique le nom de la merveille
     *
     * @return le nom de la merveille
     */
    public String nom() {
        return nom;
    }

    @Override
    public Joueur possesseur() {
        return joueur;
    }

    @Override
    public JSONObject toJSONObject() {
        return null;
    }
}
