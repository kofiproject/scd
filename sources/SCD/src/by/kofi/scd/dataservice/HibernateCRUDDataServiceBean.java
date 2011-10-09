package by.kofi.scd.dataservice;

import by.kofi.scd.entity.Department;
import by.kofi.scd.exceptions.SCDTechnicalException;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
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
    public <T> T merge(T t) throws SCDTechnicalException {
        try {
            return getHibernateTemplate().merge(t);
        } catch (HibernateException e) {
            throw new SCDTechnicalException(e);
        }

    }

    public <T, PK extends Serializable> T find(Class<T> type, PK id) throws SCDTechnicalException {
        try {
            return getHibernateTemplate().get(type, id);
        } catch (HibernateException e) {
            throw new SCDTechnicalException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public <T, PK extends Serializable> void delete(Class<T> type, PK id) throws SCDTechnicalException {
        try {
            HibernateTemplate hibernateTemplate = getHibernateTemplate();
            T entity = hibernateTemplate.load(type, id);
            hibernateTemplate.delete(entity);
        } catch (HibernateException e) {
            throw new SCDTechnicalException(e);
        }


    }

    public <T> List<T> list(Class<T> type) throws SCDTechnicalException {
        try {
            Criteria criteria = getSession().createCriteria(type);
            List list = criteria.list();
            return list;
        } catch (HibernateException e) {
            throw new SCDTechnicalException(e);
        }
    }

    public Session getNativeHibernateSession() {
        return getSession();
    }

    public HibernateTemplate getNativeHibernateTemplate() {
        return getHibernateTemplate();
    }
}