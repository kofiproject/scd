package by.kofi.scd.dataservice;

import by.kofi.scd.dataservice.department.DepartmentDataService;
import by.kofi.scd.entity.Department;
import by.kofi.scd.exceptions.SCDTechnicalException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.util.List;

/**
 * Abstract dao service to extend by all dao services
 *
 * @author harchevnikov_m
 *         Date: 02/10/11
 *         Time: 17:23
 */
public abstract class AbstractDataServiceBean implements AbstractDataService {

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
