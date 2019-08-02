package seven_wonders.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

import org.w3c.dom.Node;

/**
 * Outils XML
 */
public class XML {
    private static final String EXCEPTION_ARGUMENT_ENUMERATION = "la classe n�est pas une �num�ration";                         //$NON-NLS-1$
    private static final String EXCEPTION_ARGUMENT_METHODE     = "l��num�ration ne contient pas de m�thode � valueOf � valide"; //$NON-NLS-1$

    private static final String VALUE_OF = "valueOf"; //$NON-NLS-1$

    /**
     * Convertit le contenu textuel d�une node XML en constante d��num�ration
     *
     * @param  enumeration              �num�ration
     * @param  node                     node XML
     * @return                          la constante d��num�ration
     * @throws NullPointerException     si l��num�ration ou la node XML est nulle
     * @throws IllegalArgumentException si la classe n�est pas une �num�ration
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
