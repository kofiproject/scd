package by.kofi.scd.dataservice;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;

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
     * Creates/updates a new object for the given type.
     *
     * @param <T> Entity class
     * @param t   entity
     * @return persisted Object
     */
    <T> T merge(T t);

    /**
     * Deletes the given object by id
     *
     * @param <T>
     * @param <PK>
     * @param type , entity class type
     * @param id
     */
    <T, PK extends Serializable> boolean delete(Class<T> type, PK id);

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

    /**
     * @return Hibernate session instance
     */
    Session getNativeHibernateSession();

    /**
     * @return Spring HibernateTemplate
     */
    HibernateTemplate getNativeHibernateTemplate();
}
