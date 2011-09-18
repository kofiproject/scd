package by.kofi.scd.dal;

import java.io.Serializable;
import java.util.List;

/**
 * CRUD interface
 *
 * @author harchevnikov_m
 *         Date: 18.09.11
 *         Time: 21:02
 */
public interface CRUDService {

    /**
     * Creates a new object for the given type. After a call to this method the entity will be
     * persisted into database and then refreshed. Also current persistent Session will be flushed.
     *
     * @param <T>
     * @param t
     * @return persisted Object
     */
    <T> T create(T t);

    /**
     * Updates the given object
     *
     * @param <T>
     * @param t
     * @return persisted object
     */
    <T> T update(T t);

    /**
     * Deletes the given object by id
     *
     * @param <T>
     * @param <PK>
     * @param type , entity class type
     * @param id
     */
    <T, PK extends Serializable> void delete(Class<T> type, PK id);

    /**
     * Finds an object by id
     *
     * @param <T>
     * @param <PK>
     * @param type
     * @param id
     * @return the object
     */
    <T, PK extends Serializable> T find(Class<T> type, PK id);

    /**
     * List of objects
     *
     * @param type
     * @param <T>
     * @return
     */
    <T> List<T> list(Class<T> type);
}
