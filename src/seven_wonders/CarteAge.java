package seven_wonders;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import seven_wonders.effets.Effet;
import seven_wonders.utils.XML;

/**
 * Carte d��ge
 */
public class CarteAge extends Carte {
    private static final String ATTRIBUT_AGE  = "age";  //$NON-NLS-1$
    private static final String ATTRIBUT_NOM  = "nom";  //$NON-NLS-1$
    private static final String ATTRIBUT_TYPE = "type"; //$NON-NLS-1$
    private static NodeList     CARTES_AGE    = null;

    static {
        try {
            CARTES_AGE = DocumentBuilderFactory.newInstance()
                                               .newDocumentBuilder()
                                               .parse( new File( "CartesAge.xml" ) ) //$NON-NLS-1$
                                               .getDocumentElement()
                                               .getElementsByTagName( "carteAge" ); //$NON-NLS-1$
        } catch ( final ParserConfigurationException | SAXException | IOException e ) {
            e.printStackTrace();
        }
    }

    /**
     * G�n�re une carte d��ge � l�aide de sa node XML
     *
     * @param  nom                  nom de la carte d��ge
     * @param  age                  �ge auquel la carte est utilis�e
     * @return                      la carte d��ge g�n�r�e
     * @throws NullPointerException si le nom est nul
     */
    public static CarteAge generer( final String nom, final Age age ) {
        Objects.requireNonNull( nom );
        for ( int i = 0 ; i < CARTES_AGE.getLength() ; ++i ) {
            final Element      element   = (Element) CARTES_AGE.item( i );
            final NamedNodeMap attributs = element.getAttributes();
            final Node         attrNom   = attributs.getNamedItem( ATTRIBUT_NOM );
            if ( attrNom != null && attrNom.getTextContent()
                                           .equals( nom )
                 && Age.estPresent( attributs.getNamedItem( ATTRIBUT_AGE )
                                             .getTextContent(),
                                    age ) )
                return new CarteAge( nom, Cout.generer( element ), Effet.generer( element ), age,
                                     XML.toEnumConstant( TypeCarteAge.class,
                                                         attributs.getNamedItem( ATTRIBUT_TYPE ) ) );
        }
        return null;
    }

    /**
     * �ge de la carte
     */
    private final Age          age;
    /**
     * Type de la carte d��ge
     */
    private final TypeCarteAge type;

    /**
     * Cr�e une copir d�une carte d��ge
     *
     * @param  autre                carte d��ge � copier
     * @throws NullPointerException si la carte d��ge est nulle
     */
    public CarteAge( final CarteAge autre ) {
        super( autre );
        age  = autre.age;
        type = autre.type;
    }

    /**
     * Cr�e une carte d��ge
     *
     * @param  nom                  nom de la carte d��ge
     * @param  cout                 co�t de la carte d��ge
     * @param  effet                effet de la carte d��ge
     * @param  age                  �ge de la carte d��ge
     * @param  type                 type de la carte d��ge
     * @throws NullPointerException si le nom, le co�t ou l�effet est nul
     */
    public CarteAge( final String nom, final Cout cout, final Effet effet, final Age age, final TypeCarteAge type ) {
        super( nom, cout, effet );
        this.age  = age;
        this.type = type;
    }

    /**
     * Indique l��ge de la carte
     *
     * @return l��ge de la carte
     */
    public Age age() {
        return age;
    }

    @Override
    public CarteAge deepClone() {
        return new CarteAge( this );
    }

    /**
     * Indique le type de la carte d��ge
     *
     * @return le type de la carte d��ge
     */
    public TypeCarteAge type() {
        return type;
    }
}
