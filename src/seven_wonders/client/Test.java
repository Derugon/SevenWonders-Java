package seven_wonders.client;

import java.util.Arrays;

import seven_wonders.net.Connexion;
import seven_wonders.net.Serveur;

/**
 * Classe de test
 */
public class Test {
    private static final String AO = "{"; //$NON-NLS-1$
    private static final String AF = "}"; //$NON-NLS-1$

    /**
     * Méthode principale
     *
     * @param args arguments à l’exécution
     */
    public static void main( final String[] args ) {
        final String hote = "127.0.0.1"; //$NON-NLS-1$
        final int    port = 2345;

        final Serveur serveur = new Serveur( hote, port );
        serveur.lancer();

        final Connexion[] joueurs = new Connexion[ 4 ];
        Arrays.setAll( joueurs, indice -> new Connexion( hote, port ) );
        for ( final Connexion joueur : joueurs )
            new Thread( joueur ).start();

        try {
            Thread.sleep( 3000 );
        } catch ( final InterruptedException e ) {
            e.printStackTrace();
        }
        joueurs[2].fermer();
        try {
            Thread.sleep( 3000 );
        } catch ( final InterruptedException e ) {
            e.printStackTrace();
        }
        joueurs[0].fermer();
        joueurs[1].fermer();
        joueurs[3].fermer();
    }

    /**
     * Indique le nombre de thread
     */
    @SuppressWarnings( "unused" )
    private static void nbThreads() {
        System.out.println( AO + Thread.activeCount() + AF );
    }
}
