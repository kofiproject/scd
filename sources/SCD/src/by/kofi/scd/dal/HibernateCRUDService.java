package by.kofi.scd.dal;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

/**
 * Hibernate crud interface implementation
 *
 *
 * @author harchevnikov_m
 *         Date: 18.09.11
 *         Time: 21:20
 */
public class HibernateCRUDService implements CRUDService {

    @Inject
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

    @CommitAfter
    public <T> T create(T t) {
//        session.beginTransaction();
        session.persist(t);
        session.flush();
        session.refresh(t);
//        session.getTransaction().commit();
        return t;
    }

    @SuppressWarnings("unchecked")
    public <T, PK extends Serializable> T find(Class<T> type, PK id) {
        return (T) session.get(type, id);
    }

    @CommitAfter
    public <T> T update(T type) {
        session.merge(type);
        return type;
    }

    @SuppressWarnings("unchecked")
    @CommitAfter
    public <T, PK extends Serializable> void delete(Class<T> type, PK id) {
        T ref = (T) session.get(type, id);
        session.delete(ref);
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> list(Class<T> type) {
        return session.createCriteria(type).list();
    }
}
