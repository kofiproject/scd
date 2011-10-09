package by.kofi.scd.dataservice;

import by.kofi.scd.exceptions.SCDTechnicalException;
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
public interface CRUDDataService {

    /**
     * Creates/updates a new object for the given type.
     *
     * @param <T> Entity class
     * @param t   entity
     * @return persisted Object
     */
    <T> T merge(T t) throws SCDTechnicalException;

    /**
     * Deletes the given object by id
     *
     * @param <T>
     * @param <PK>
     * @param type , entity class type
     * @param id
     */
    <T, PK extends Serializable> void delete(Class<T> type, PK id) throws SCDTechnicalException;

    /**
     * Finds an object by id
     *
     * @param <T>
     * @param <PK>
     * @param type
     * @param id
     * @return the object
     */
    <T, PK extends Serializable> T find(Class<T> type, PK id) throws SCDTechnicalException;

    /**
     * List of objects
     *
     * @param type
     * @param <T>
     * @return
     */
    <T> List<T> list(Class<T> type) throws SCDTechnicalException;

    /**
     * @return Hibernate session instance
     */
    Session getNativeHibernateSession();

    /**
     * @return Spring HibernateTemplate
     */
    HibernateTemplate getNativeHibernateTemplate();
}
