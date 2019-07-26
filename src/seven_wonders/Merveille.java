package seven_wonders;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import seven_wonders.effets.Effet;

/**
 * Merveille
 */
public class Merveille {
    /**
     * Étape d’une merveille
     */
    public class Etape {
        /**
         * Coût de l’étape
         */
        private final Cout  cout;
        /**
         * Effet de l’étape
         */
        private final Effet effet;

        /**
         * Crée une étape d’une merveille
         *
         * @param cout  coût de l’étape
         * @param effet effet de l’étape
         */
        public Etape( final Cout cout, final Effet effet ) {
            this.cout  = cout;
            this.effet = effet;
        }
    }

    private static NodeList MERVEILLES = null;

    static {
        try {
            MERVEILLES = DocumentBuilderFactory.newInstance()
                                               .newDocumentBuilder()
                                               .parse( new File( "Merveilles.xml" ) ) //$NON-NLS-1$
                                               .getDocumentElement()
                                               .getElementsByTagName( "merveille" ); //$NON-NLS-1$
        } catch ( final ParserConfigurationException | SAXException | IOException e ) {
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
     * Étapes de la merveille
     */
    private final Etape[] etapes;
    /**
     * Joueur possédant la merveille
     */
    private final Joueur  joueur;

    /**
     * Crée une merveille
     *
     * @param nom    nom de la merveille
     * @param joueur joueur possédant la merveille
     */
    public Merveille( final String nom, final Joueur joueur ) {
        Effet effet = null;
        for ( int i = 0 ; i < MERVEILLES.getLength() ; ++i ) {
            final Element element = (Element) MERVEILLES.item( i );
            final Node    attrNom = element.getAttributes()
                                           .getNamedItem( "nom" ); //$NON-NLS-1$
            if ( attrNom != null && attrNom.getTextContent()
                                           .equals( nom ) )
                effet = Effet.generer( joueur, element );
        }
        this.nom    = nom;
        this.effet  = effet == null ? Effet.vide( joueur ) : effet;
        this.joueur = joueur;
    }

    /**
     * Indique le joueur possédant la merveille
     *
     * @return le joueur possédant la merveille
     */
    public Joueur joueur() {
        return joueur;
    }

    /**
     * Indique le nom de la merveille
     *
     * @return le nom de la merveille
     */
    public String nom() {
        return nom;
    }
}
