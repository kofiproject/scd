package by.kofi.scd.dataservice;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;

/**
 * Hibernate crud interface implementation
 *
 * @author harchevnikov_m
 *         Date: 18.09.11
 *         Time: 21:20
 */
@Scope("singleton")
public class HibernateCRUDDataServiceBean extends HibernateDaoSupport implements CRUDDataService {

    @PostConstruct
    public void init() {
        Locale.setDefault(Locale.ENGLISH);
    }

    /**
     * Default constructor
     */
    public HibernateCRUDDataServiceBean() {
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public <T> T merge(T t) {
        return getHibernateTemplate().merge(t);
    }

    public <T, PK extends Serializable> T find(Class<T> type, PK id) {
        return getHibernateTemplate().get(type, id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public <T, PK extends Serializable> boolean delete(Class<T> type, PK id) {
        HibernateTemplate hibernateTemplate = getHibernateTemplate();
        T entity = hibernateTemplate.load(type, id);
        hibernateTemplate.delete(entity);
        return false;
    }

    public <T> List<T> list(Class<T> type) {
        Criteria criteria = getSession().createCriteria(type);
        List list = criteria.list();
        return list;
    }

    public Session getNativeHibernateSession() {
        return getSession();
    }

    public HibernateTemplate getNativeHibernateTemplate() {
        return getHibernateTemplate();
    }
}