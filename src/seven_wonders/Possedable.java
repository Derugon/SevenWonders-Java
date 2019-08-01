package seven_wonders;

/**
 * Objet poss�dable par un joueur
 *
 * @param <E> objet poss�dable par un joueur
 */
public interface Possedable {
    /**
     * Affecte l�objet � un joueur
     *
     * @param  joueur possesseur
     * @return        l�objet
     */
    Object affecter( final Joueur joueur );

    /**
     * Indique le possesseur de l�objet
     *
     * @return le possesseur de l�objet
     */
    Joueur possesseur();
}
