package seven_wonders;

import org.json.EnumConvertibleJSON;

/**
 * Type de ressource
 * 
 * @param <E> type d’énumération
 */
public interface TypeRessource<E extends Enum<E>> extends EnumConvertibleJSON<E> { /* ... */ }
