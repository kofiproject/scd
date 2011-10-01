package by.kofi.scd.dal;

import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

/**
 * Hibernate crud interface implementation
 *
 * @author harchevnikov_m
 *         Date: 18.09.11
 *         Time: 21:20
 */
public class HibernateCRUDService implements CRUDService {

    private Session session;

    /**
     * Default constructor
     */
    public HibernateCRUDService() {
    }

    /**
     * @return hibernate session instance
     */
    public Session getSession() {
        return session;
    }

    public <T> T create(T t) {
        session.beginTransaction();

        session.persist(t);
        session.flush();
        session.refresh(t);

        session.getTransaction().commit();
        return t;
    }

    @SuppressWarnings("unchecked")
    public <T, PK extends Serializable> T find(Class<T> type, PK id) {
        return (T) session.get(type, id);
    }

    public <T> T update(T type) {
        session.beginTransaction();
        session.merge(type);
        session.getTransaction().commit();
        return type;
    }

    @SuppressWarnings("unchecked")
    public <T, PK extends Serializable> void delete(Class<T> type, PK id) {
        session.beginTransaction();
        T ref = (T) session.get(type, id);
        session.getTransaction().commit();
        session.delete(ref);
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> list(Class<T> type) {
        return session.createCriteria(type).list();
    }
}
