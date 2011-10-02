package by.kofi.scd.dataservice;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * Abstract dao service to extend by all dao services
 *
 * @author harchevnikov_m
 *         Date: 02/10/11
 *         Time: 17:23
 */
public abstract class AbstractDaoService {

    @Autowired(required = true)
    @Qualifier("hibernateCRUDService")
    private CRUDService hibernateCRUDService;

    /**
     * @return Hibernate session
     */
    public Session getSession() {
        return hibernateCRUDService.getNativeHibernateSession();
    }

    /**
     * @return Hibernate template
     */
    public HibernateTemplate getHibernateTemplate() {
        return hibernateCRUDService.getNativeHibernateTemplate();
    }
}
