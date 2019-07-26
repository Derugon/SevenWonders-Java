package seven_wonders;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import seven_wonders.utils.Messages;

/**
 * Exception lanc�e lorsqu�un attribut XML poss�de une valeur invalide
 */
public class AttributXMLInvalideException extends RuntimeException {
    /**
     * Identificateur
     */
    private static final long serialVersionUID = -6162169607413860984L;

    /**
     * Cr�e une exception
     *
     * @param message message � afficher
     */
    public AttributXMLInvalideException( final String message ) {
        super( message );
    }

    /**
     * Cr�e une exception
     *
     * @param attribut  attribut
     * @param attributs liste des attributs
     */
    public AttributXMLInvalideException( final String attribut, final NamedNodeMap attributs ) {
        this( attribut, attributs.getNamedItem( attribut ) );
    }

    /**
     * Cr�e une exception
     *
     * @param attribut attribut
     * @param contenu  contenu de l�attribut
     */
    public AttributXMLInvalideException( final String attribut, final Node contenu ) {
        this( attribut, contenu.getTextContent() );
    }

    /**
     * Cr�e une exception
     *
     * @param attribut attribut
     * @param valeur   valeur de l�attribut
     */
    public AttributXMLInvalideException( final String attribut, final String valeur ) {
        super( Messages.getString( "AttributXMLInvalideException.message", attribut ) + valeur ); //$NON-NLS-1$
    }
}
