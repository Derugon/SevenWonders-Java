package seven_wonders;

/**
 * Objet possédable par un joueur
 *
 * @param <E> objet possédable par un joueur
 */
public interface Possedable {
    /**
     * Affecte l’objet à un joueur
     *
     * @param  joueur possesseur
     * @return        l’objet
     */
    Object affecter( final Joueur joueur );

    /**
     * Indique le possesseur de l’objet
     *
     * @return le possesseur de l’objet
     */
    Joueur possesseur();
}
