package seven_wonders.utils;

import java.util.Objects;

/**
 * Proc�dure
 */
@FunctionalInterface
public interface Procedure {
    /**
     * Cr�e une proc�dure qui ex�cute celle indiqu�e puis celle-ci
     *
     * @param  avant                proc�dure � ex�cuter avant
     * @return                      la proc�dure compos�e
     * @throws NullPointerException si la proc�dure est nulle
     */
    default Procedure dAbord( final Procedure avant ) {
        Objects.requireNonNull( avant );
        return () -> {
            avant.executer();
            executer();
        };
    }

    /**
     * Cr�e une proc�dure qui ex�cute celle-ci puis celle indiqu�e
     *
     * @param  apres                proc�dure � ex�cuter apr�s
     * @return                      la proc�dure compos�e
     * @throws NullPointerException si la proc�dure est nulle
     */
    default Procedure ensuite( final Procedure apres ) {
        Objects.requireNonNull( apres );
        return () -> {
            executer();
            apres.executer();
        };
    }

    /**
     * Ex�cute la proc�dure
     */
    void executer();
}
