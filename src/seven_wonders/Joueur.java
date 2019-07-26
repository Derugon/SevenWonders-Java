package seven_wonders;

/**
 * Joueur
 */
public class Joueur {
    /**
     * Nom du joueur
     */
    private final String nom;
    /**
     * Merveille du joueur
     */
    private Merveille    merveille;
    /**
     * Pièces du joueur
     */
    private final int    pieces;

    /**
     * Crée un joueur
     *
     * @param nom nom du joueur
     */
    public Joueur( final String nom ) {
        this.nom = nom;
        pieces   = 6;
    }

    @Override
    public boolean equals( final Object autre ) {
        return autre instanceof Joueur && nom.equals( ( (Joueur) autre ).nom );
    }

    @Override
    public int hashCode() {
        return nom.hashCode();
    }

    /**
     * Indique la merveille du joueur
     *
     * @return la merveille du joueur
     */
    public Merveille merveille() {
        return merveille;
    }

    /**
     * Modifie la merveille du joueur
     *
     * @param  merveille la nouvelle merveille du joueur
     * @return           la merveille du joueur
     */
    public Merveille merveille( final Merveille merveille ) {
        this.merveille = merveille;
        return merveille();
    }

    /**
     * Indique le nom du joueur
     *
     * @return le nom du joueur
     */
    public String nom() {
        return nom;
    }

    /**
     * Indique le nombre de pièces du joueur
     *
     * @return le nombre de pièces du joueur
     */
    public int pieces() {
        return pieces;
    }

    public int pieces( final int pieces ) {
        // TODO
        return 0;
    }
}
