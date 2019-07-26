package seven_wonders.utils;

/**
 * Couple
 *
 * @param <T> premier �l�ment
 * @param <U> second �l�ment
 */
public class Couple<T, U> implements DeepCloneable<Couple<T, U>> {
    private static final String SD = "[ ";  //$NON-NLS-1$
    private static final String SM = " ; "; //$NON-NLS-1$
    private static final String SF = " ]";  //$NON-NLS-1$

    /**
     * Premier �l�ment
     */
    private final T premier;
    /**
     * Second �l�ment
     */
    private final U second;

    /**
     * Cr�e une copie d�un couple
     *
     * @param autre couple � copier
     */
    @SuppressWarnings( "unchecked" )
    public Couple( final Couple<T, U> autre ) {
        premier = autre.premier instanceof DeepCloneable<?>
                ? (T) ( (DeepCloneable<? extends DeepCloneable<?>>) autre.premier ).deepClone() : autre.premier;
        second  = autre.second instanceof DeepCloneable<?>
                ? (U) ( (DeepCloneable<? extends DeepCloneable<?>>) autre.second ).deepClone() : autre.second;
    }

    /**
     * Cr�e un couple
     *
     * @param premier premier �l�ment
     * @param second  second �l�ment
     */
    public Couple( final T premier, final U second ) {
        this.premier = premier;
        this.second  = second;
    }

    @Override
    public Couple<T, U> deepClone() {
        return new Couple<>( this );
    }

    @Override
    public boolean equals( final Object obj ) {
        return obj instanceof Couple && premier.equals( ( (Couple<?, ?>) obj ).premier )
               && second.equals( ( (Couple<?, ?>) obj ).second );
    }

    @Override
    public int hashCode() {
        return 31 * premier.hashCode() + second.hashCode();
    }

    /**
     * Indique le premier �l�ment
     *
     * @return le premier �l�ment
     */
    public T premier() {
        return premier;
    }

    /**
     * Indique le second �l�ment
     *
     * @return le second �l�ment
     */
    public U second() {
        return second;
    }

    @Override
    public String toString() {
        return SD + premier + SM + second + SF;
    }
}
