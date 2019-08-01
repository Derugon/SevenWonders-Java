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
public class Merveille implements Possedable {
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
     * @param nom nom de la merveille
     */
    public Merveille( final String nom ) {
        Effet effet = null;
        for ( int i = 0 ; i < MERVEILLES.getLength() ; ++i ) {
            final Element element = (Element) MERVEILLES.item( i );
            final Node    attrNom = element.getAttributes()
                                           .getNamedItem( "nom" ); //$NON-NLS-1$
            if ( attrNom != null && attrNom.getTextContent()
                                           .equals( nom ) )
                effet = Effet.generer( element );
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
}
