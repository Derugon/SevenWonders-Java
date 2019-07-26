package seven_wonders.net;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Processeur
 */
public class Processeur implements Runnable {
    protected static final String MESSAGE_FERMER = "$fermer"; //$NON-NLS-1$
    protected static final String REPONSE_FERMER = "fermé";   //$NON-NLS-1$
    private static final String   AD             = " [";      //$NON-NLS-1$
    private static final String   AM             = ":";       //$NON-NLS-1$
    private static final String   AF             = "] ";      //$NON-NLS-1$
    private static final String   ME             = "=<= ";    //$NON-NLS-1$
    private static final String   MS             = "=>= ";    //$NON-NLS-1$
    private static final String   MEI            = "??? : ";  //$NON-NLS-1$

    /**
     * Communication au serveur
     */
    private final Socket              connexion;
    /**
     * Flux du serveur en lecture
     */
    private final BufferedInputStream lecture;
    /**
     * Flux du serveur en écriture
     */
    private final PrintWriter         ecriture;

    /**
     * Crée un processeur de gestion d’un client
     *
     * @param connexion communication au serveur
     */
    public Processeur( final Socket connexion ) {
        this.connexion = connexion;
        PrintWriter         ecriture = null;
        BufferedInputStream lecture  = null;
        try {
            lecture  = new BufferedInputStream( connexion.getInputStream() );
            ecriture = new PrintWriter( connexion.getOutputStream() );
        } catch ( final IOException e ) {
            e.printStackTrace();
        }
        this.lecture  = lecture;
        this.ecriture = ecriture;
    }

    @Override
    public void run() {
        boolean fermer = false;
        while ( !connexion.isClosed() )
            try {
                final byte[]            b       = new byte[ 4096 ];
                final int               taille  = lecture.read( b );
                final String            message = new String( b, 0, taille );
                final InetSocketAddress client  = (InetSocketAddress) connexion.getRemoteSocketAddress();

                String       reponse = "";                           //$NON-NLS-1$
                final String distant = Thread.currentThread()
                                             .getName()
                                       + AD + client.getAddress()
                                                    .getHostAddress()
                                       + AM + client.getPort() + AF;
                if ( message.charAt( 0 ) == '$' )
                    switch ( message ) {
                    case MESSAGE_FERMER:
                        reponse = REPONSE_FERMER;
                        fermer = true;
                        System.out.println( distant + ME + message );
                        break;
                    default:
                        System.out.println( distant + ME + MEI + message );
                    }
                System.out.println( distant + MS + reponse );
                ecriture.write( reponse );
                ecriture.flush();
                if ( fermer )
                    connexion.close();
            } catch ( final IOException e ) {
                e.printStackTrace();
            }
    }
}
