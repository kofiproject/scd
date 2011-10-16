package by.kofi.scd.dataservice;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;

/**
 * Abstract dao service to extend by all dao services
 *
 * @author harchevnikov_m
 *         Date: 02/10/11
 *         Time: 17:23
 */
@Service("abstractDataServiceBean")
public class AbstractDataServiceBean implements AbstractDataService {

    @Autowired(required = true)
    @Qualifier("hibernateCRUDService")
    private CRUDDataService hibernateCRUDDataService;

    /**
     * @return Hibernate session
     */
    public Session getSession() {
        return hibernateCRUDDataService.getNativeHibernateSession();
    }

    /**
     * @return Hibernate template
     */
    public HibernateTemplate getHibernateTemplate() {
        return hibernateCRUDDataService.getNativeHibernateTemplate();
    }

    public CRUDDataService getHibernateCRUDService() {
        return hibernateCRUDDataService;
    }
}
