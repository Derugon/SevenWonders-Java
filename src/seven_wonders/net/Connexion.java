package seven_wonders.net;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;

import seven_wonders.utils.Couple;
import seven_wonders.utils.Quantite;

/**
 * Connexion � un serveur
 */
public class Connexion implements Runnable {
    private static final String IE = "|<- "; //$NON-NLS-1$
    private static final String IS = "|-> "; //$NON-NLS-1$
    private static final String S  = " : ";  //$NON-NLS-1$

    /**
     * Nombre de connexions existantes
     */
    private static final Quantite                                         quantite = new Quantite();
    /**
     * Connexion au serveur
     */
    private final Socket                                                  connexion;
    /**
     * Flux du serveur en lecture
     */
    private BufferedInputStream                                           lecture;
    /**
     * Flux du serveur en �criture
     */
    private PrintWriter                                                   ecriture;
    /**
     * Message � envoyer au serveur
     */
    private final ConcurrentLinkedQueue<Couple<String, Consumer<String>>> envois;

    /**
     * Cr�e une connexion � un serveur
     *
     * @param hote h�te du serveur
     * @param port port du serveur
     */
    public Connexion( final String hote, final int port ) {
        Socket connexion = null;
        try {
            connexion = new Socket( hote, port );
            ecriture  = new PrintWriter( connexion.getOutputStream(), true );
            lecture   = new BufferedInputStream( connexion.getInputStream() );
        } catch ( final IOException e ) {
            e.printStackTrace();
        }
        this.connexion = connexion;
        envois         = new ConcurrentLinkedQueue<>();
    }

    /**
     * Ferme le serveur
     */
    public void fermer() {
        envoyer( Processeur.MESSAGE_FERMER, renvoi -> {
            try {
                connexion.close();
            } catch ( final IOException e ) {
                e.printStackTrace();
            }
        } );
    }

    @Override
    public void finalize() {
        quantite.decrementerLimite();
    }

    @Override
    public void run() {
        while ( !connexion.isClosed() ) {
            try {
                Thread.sleep( 1000 );
            } catch ( final InterruptedException e ) {
                e.printStackTrace();
            }
            try {
                final Couple<String, Consumer<String>> message = envois.remove();
                System.out.println( IS + Thread.currentThread()
                                               .getName()
                                    + S + message.premier() );
                ecriture.write( message.premier() );
                ecriture.flush();
                final byte[] b       = new byte[ 4096 ];
                final int    taille  = lecture.read( b );
                final String reponse = new String( b, 0, taille );
                message.second()
                       .accept( reponse );
                System.out.println( IE + Thread.currentThread()
                                               .getName()
                                    + S + reponse );
            } catch ( final IOException e ) {
                e.printStackTrace();
            } catch ( @SuppressWarnings( "unused" ) final NoSuchElementException e ) { /* ... */ }
        }
    }

    /**
     * Envoie un message au serveur
     *
     * @param  message              message � envoyer au serveur
     * @param  renvoi               fonction � ex�cuter avec le message renvoy�
     * @return                      vrai si le message sera envoy� et faux sinon
     * @throws NullPointerException si le message est nul
     */
    private boolean envoyer( final String message, final Consumer<String> renvoi ) {
        return envois.add( new Couple<>( Objects.requireNonNull( message ), Objects.requireNonNull( renvoi ) ) );
    }
}
