package seven_wonders;

/**
 * Âge
 */
public enum Age {
    /**
     * Âge I
     */
    I,
    /**
     * Âge II
     */
    II,
    /**
     * Âge III
     */
    III;

    /**
     * Indique si un âge est présent dans une chaîne d’identification d’âges
     *
     * @param  chaine chaîne d’identification d’âges
     * @param  age    âge
     * @return        vrai si l’âge est présent et faux sinon
     */
    public static boolean estPresent( final String chaine, final Age age ) {
        return chaine.charAt( age.ordinal() ) == 'x';
    }
}
