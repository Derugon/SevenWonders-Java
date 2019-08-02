package seven_wonders.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

import org.w3c.dom.Node;

/**
 * Outils XML
 */
public class XML {
    private static final String EXCEPTION_ARGUMENT_ENUMERATION = "la classe n’est pas une énumération";                         //$NON-NLS-1$
    private static final String EXCEPTION_ARGUMENT_METHODE     = "l’énumération ne contient pas de méthode « valueOf » valide"; //$NON-NLS-1$

    private static final String VALUE_OF = "valueOf"; //$NON-NLS-1$

    /**
     * Convertit le contenu textuel d’une node XML en constante d’énumération
     *
     * @param  enumeration              énumération
     * @param  node                     node XML
     * @return                          la constante d’énumération
     * @throws NullPointerException     si l’énumération ou la node XML est nulle
     * @throws IllegalArgumentException si la classe n’est pas une énumération
     *                                  valide
     */
    @SuppressWarnings( "unchecked" )
    public static <E extends Enum<E>> E toEnumConstant( final Class<E> enumeration, final Node node ) {
        if ( !Objects.requireNonNull( enumeration )
                     .isEnum() )
            throw new IllegalArgumentException( EXCEPTION_ARGUMENT_ENUMERATION );
        Objects.requireNonNull( node );
        try {
            return (E) enumeration.getMethod( VALUE_OF, String.class )
                                  .invoke( null, node.getTextContent()
                                                     .toUpperCase()
                                                     .replace( ' ', '_' ) );
        } catch ( @SuppressWarnings( "unused" ) final IllegalAccessException | InvocationTargetException
                                                      | NoSuchMethodException e ) {
            throw new IllegalArgumentException( EXCEPTION_ARGUMENT_METHODE );
        }
    }
}
