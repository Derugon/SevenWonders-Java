package seven_wonders.effets;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;

import seven_wonders.Joueur;

/**
 * Effet combinant plusieurs effets
 */
public class EffetCombine extends Effet implements Iterable<Effet> {
    /**
     * Effets
     */
    private final LinkedList<Effet> effets;

    /**
     * Crée un effet combiné
     *
     * @param  effets               effets à combiner
     * @throws NullPointerException si le joueur ou l’un des effets est nul
     */
    public EffetCombine( final Effet... effets ) {
        super( j -> {
            for ( final Effet effet : effets )
                effet.obtention();
        } );
        this.effets = new LinkedList<>();
        ajouter( effets );
    }

    /**
     * Crée une copie d’un effet combiné
     *
     * @param  autre                effet combiné à copier
     * @throws NullPointerException si l’effet est nul
     */
    public EffetCombine( final EffetCombine autre ) {
        super( autre );
        effets = new LinkedList<>( autre.effets );
    }

    @Override
    public EffetCombine affecter( final Joueur joueur ) {
        super.affecter( joueur );
        return this;
    }

    /**
     * Ajoute un/des effet(s) à l’effet combiné
     *
     * @param  effets               effet(s) à ajouter
     * @return                      l’effet
     * @throws NullPointerException si l’un des effets est nul
     */
    public EffetCombine ajouter( final Effet... effets ) {
        for ( final Effet effet : effets )
            this.effets.add( Objects.requireNonNull( effet ) );
        return this;
    }

    @Override
    public EffetCombine appliquer( final Joueur joueur ) {
        super.appliquer( joueur );
        return this;
    }

    @Override
    public EffetCombine combiner( final Effet autre ) {
        return new EffetCombine( this ).ajouter( autre );
    }

    @Override
    public EffetCombine deepClone() {
        return new EffetCombine( this );
    }

    @Override
    public boolean equals( final Object obj ) {
        return super.equals( obj ) && obj instanceof EffetCombine && effets.equals( ( (EffetCombine) obj ).effets );
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + effets.hashCode();
    }

    @Override
    public Iterator<Effet> iterator() {
        return effets.iterator();
    }
}
