package by.kofi.scd.dataservice.department;

import by.kofi.scd.dataservice.AbstractDaoService;
import by.kofi.scd.entity.Department;
import by.kofi.scd.exceptions.SCDTechnicalException;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Department Data Service
 *
 * @author harchevnikov_m
 *         Date: 02/10/11
 *         Time: 16:43
 */
@Service
public class DepartmentDAOService extends AbstractDaoService implements DepartmentService {

    @Transactional(propagation = Propagation.MANDATORY)
    public List<Department> getItems(List<Long> ids) throws SCDTechnicalException {
        try {
            return getSession().createCriteria(Department.class).list();
        } catch (HibernateException e) {
            throw new SCDTechnicalException(e);
        }
    }
}
