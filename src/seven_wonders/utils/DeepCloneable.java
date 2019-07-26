package seven_wonders.utils;

/**
 * Fournit une méthode de clonage non protégée, contrairement à Object.clone(),
 * et gérant le polymorphisme, contairement au constructeur de copie
 *
 * @param <T> type sur lequel est implémenté l’interface
 */
public interface DeepCloneable<T extends DeepCloneable<T>> {
    /**
     * Crée un clone de l’objet
     *
     * @return un clone de l’objet
     */
    T deepClone();
}
