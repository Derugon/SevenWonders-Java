package seven_wonders.utils;

/**
 * Fournit des méthodes pour l’utilisation d’une classe nulle
 */
public interface Nullable {
    /**
     * Fournit une référence nulle d’un objet, pour faciliter la gestion de valeurs
     * nulles
     */
    static class Null {
        private static final String STRING = "null"; //$NON-NLS-1$

        @Override
        public Object clone() {
            return this;
        }

        @Override
        public boolean equals( final Object object ) {
            return object == null || object == this;
        }

        @Override
        public int hashCode() {
            return 0;
        }

        @Override
        public String toString() {
            return STRING;
        }
    }

    /**
     * Valeur nulle
     */
    Null NULL = new Null();
}
