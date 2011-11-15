package by.kofi.scd.dataservice.employee;

import by.kofi.scd.dataservice.AbstractDataServiceBean;
import by.kofi.scd.dataservice.credit.CreditDataService;
import by.kofi.scd.dataservice.credit.CreditQueryBuilder;
import by.kofi.scd.entity.Credit;
import by.kofi.scd.exceptions.SCDTechnicalException;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author harchevnikov_m
 *         Date: 15/10/11
 *         Time: 21:06
 */
@Service
public class EmployeeDataServiceBean extends AbstractDataServiceBean implements EmployeeDataService {

    /**
     * {@inheritDoc}
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void lockCreditRequest(Long creditRequestId, Long employeeId) throws SCDTechnicalException {
        try {
            getSession().createSQLQuery(EmployeeQueryBuilder.getLockNativeQuery(creditRequestId, employeeId))
                    .executeUpdate();
        } catch (HibernateException e) {
            throw new SCDTechnicalException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void unlockCreditRequest(Long creditRequestId) throws SCDTechnicalException {
        try {
            getSession().createSQLQuery(EmployeeQueryBuilder.getLockNativeQuery(creditRequestId, null))
                    .executeUpdate();
        } catch (HibernateException e) {
            throw new SCDTechnicalException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void unlockAllCreditRequestsByEmployee(Long employeeId) throws SCDTechnicalException {
        try {
            getSession().createSQLQuery(EmployeeQueryBuilder.getUnlockAllNativeQuery(employeeId))
                    .executeUpdate();
        } catch (HibernateException e) {
            throw new SCDTechnicalException(e);
        }
    }
}
