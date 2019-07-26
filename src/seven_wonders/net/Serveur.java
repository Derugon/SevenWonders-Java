package seven_wonders.net;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Serveur
 */
public class Serveur {
    private static final int    PAR_DEFAUT_PORT = 3874;
    private static final String PAR_DEFAUT_HOTE = "127.0.0.1"; //$NON-NLS-1$

    private final String       hote;
    private final int          port;
    private final ServerSocket serveur;
    private boolean            actif;

    /**
     * Crée un serveur en 127.0.0.1:3874
     */
    public Serveur() {
        this( PAR_DEFAUT_HOTE, PAR_DEFAUT_PORT );
    }

    /**
     * Crée un serveur
     *
     * @param hote hôte du serveur
     * @param port port du serveur
     */
    public Serveur( final String hote, final int port ) {
        this.hote = hote;
        this.port = port;
        ServerSocket serveur = null;
        try {
            serveur = new ServerSocket( port, 100, InetAddress.getByName( hote ) );
        } catch ( final IOException e ) {
            e.printStackTrace();
        }
        this.serveur = serveur;
        actif        = true;
    }

    /**
     * Ferme le serveur
     *
     * @return le serveur
     */
    public Serveur fermer() {
        actif = false;
        return this;
    }

    /**
     * Indique l’hôte du serveur
     *
     * @return l’hôte du serveur
     */
    public String hote() {
        return hote;
    }

    /**
     * Lance le serveur
     *
     * @return le serveur
     */
    public Serveur lancer() {
        new Thread( () -> {
            while ( actif )
                try {
                    @SuppressWarnings( "resource" )
                    final Socket client = serveur.accept();
                    new Thread( new Processeur( client ) ).start();
                } catch ( final IOException e ) {
                    e.printStackTrace();
                }
            try {
                serveur.close();
            } catch ( final IOException e ) {
                e.printStackTrace();
            }
        } ).start();
        return this;
    }

    /**
     * Indique le port du serveur
     *
     * @return le port du serveur
     */
    public int port() {
        return port;
    }
}
