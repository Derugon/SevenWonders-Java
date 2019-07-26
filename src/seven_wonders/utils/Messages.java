package seven_wonders.utils;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Messages
 */
public class Messages {
    private static final String BUNDLE_NAME = "seven_wonders.messages"; //$NON-NLS-1$

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle( BUNDLE_NAME );

    /**
     * Donne le message demandé
     *
     * @param  key  l’identificateur du message
     * @param  args arguments
     * @return      le message correspondant
     */
    public static String getString( final String key, final String... args ) {
        try {
            final String string = RESOURCE_BUNDLE.getString( key );
            for ( int i = 0 ; i < args.length ; ++i )
                string.replace( "$" + i, args[i] ); //$NON-NLS-1$
            return string;
        } catch ( @SuppressWarnings( "unused" ) final MissingResourceException e ) {
            return '!' + key + '!';
        }
    }
}
