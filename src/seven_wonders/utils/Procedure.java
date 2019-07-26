package seven_wonders.utils;

import java.util.Objects;

/**
 * Procédure
 */
@FunctionalInterface
public interface Procedure {
    /**
     * Crée une procédure qui exécute celle indiquée puis celle-ci
     *
     * @param  avant                procédure à exécuter avant
     * @return                      la procédure composée
     * @throws NullPointerException si la procédure est nulle
     */
    default Procedure dAbord( final Procedure avant ) {
        Objects.requireNonNull( avant );
        return () -> {
            avant.executer();
            executer();
        };
    }

    /**
     * Crée une procédure qui exécute celle-ci puis celle indiquée
     *
     * @param  apres                procédure à exécuter après
     * @return                      la procédure composée
     * @throws NullPointerException si la procédure est nulle
     */
    default Procedure ensuite( final Procedure apres ) {
        Objects.requireNonNull( apres );
        return () -> {
            executer();
            apres.executer();
        };
    }

    /**
     * Exécute la procédure
     */
    void executer();
}
