package seven_wonders;

/**
 * �ge
 */
public enum Age {
    /**
     * �ge I
     */
    I,
    /**
     * �ge II
     */
    II,
    /**
     * �ge III
     */
    III;

    /**
     * Indique si un �ge est pr�sent dans une cha�ne d�identification d��ges
     *
     * @param  chaine cha�ne d�identification d��ges
     * @param  age    �ge
     * @return        vrai si l��ge est pr�sent et faux sinon
     */
    public static boolean estPresent( final String chaine, final Age age ) {
        return chaine.charAt( age.ordinal() ) == 'x';
    }
}
