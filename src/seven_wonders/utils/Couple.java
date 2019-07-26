package seven_wonders.utils;

/**
 * Couple
 *
 * @param <T> premier élément
 * @param <U> second élément
 */
public class Couple<T, U> implements DeepCloneable<Couple<T, U>> {
    private static final String SD = "[ ";  //$NON-NLS-1$
    private static final String SM = " ; "; //$NON-NLS-1$
    private static final String SF = " ]";  //$NON-NLS-1$

    /**
     * Premier élément
     */
    private final T premier;
    /**
     * Second élément
     */
    private final U second;

    /**
     * Crée une copie d’un couple
     *
     * @param autre couple à copier
     */
    @SuppressWarnings( "unchecked" )
    public Couple( final Couple<T, U> autre ) {
        premier = autre.premier instanceof DeepCloneable<?>
                ? (T) ( (DeepCloneable<? extends DeepCloneable<?>>) autre.premier ).deepClone() : autre.premier;
        second  = autre.second instanceof DeepCloneable<?>
                ? (U) ( (DeepCloneable<? extends DeepCloneable<?>>) autre.second ).deepClone() : autre.second;
    }

    /**
     * Crée un couple
     *
     * @param premier premier élément
     * @param second  second élément
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
     * Indique le premier élément
     *
     * @return le premier élément
     */
    public T premier() {
        return premier;
    }

    /**
     * Indique le second élément
     *
     * @return le second élément
     */
    public U second() {
        return second;
    }

    @Override
    public String toString() {
        return SD + premier + SM + second + SF;
    }
}
