package seven_wonders.utils;

/**
 * Fournit une m�thode de clonage non prot�g�e, contrairement � Object.clone(),
 * et g�rant le polymorphisme, contairement au constructeur de copie
 *
 * @param <T> type sur lequel est impl�ment� l�interface
 */
public interface DeepCloneable<T extends DeepCloneable<T>> {
    /**
     * Cr�e un clone de l�objet
     *
     * @return un clone de l�objet
     */
    T deepClone();
}
