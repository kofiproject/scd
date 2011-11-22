package by.kofi.scd.dataservice.credit.request;

import by.kofi.scd.dataservice.AbstractDataServiceBean;
import by.kofi.scd.entity.*;
import by.kofi.scd.exceptions.SCDTechnicalException;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author harchevnikov_m
 *         Date: 15/10/11
 *         Time: 21:06
 */
@Service
public class CreditRequestDataServiceBean extends AbstractDataServiceBean implements CreditRequestDataService {

    /**
     * {@inheritDoc}
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public List<CreditRequest> getCreditRequests(Long clientId, Long creditId, CreditRequestStateEnum state) throws SCDTechnicalException {
        try {
            return getSession().createQuery(CreditRequestQueryBuilder.getCreditRequestsByCreditClient())
                    .setLong("clientId", clientId)
                    .setLong("creditId", creditId)
                    .setLong("state", state.ordinal())
                    .list();
        } catch (HibernateException e) {
            throw new SCDTechnicalException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public List<CreditRequest> getCreditRequests(Long clientId, CreditRequestStateEnum state) throws SCDTechnicalException {
        try {
            return getSession().createQuery(CreditRequestQueryBuilder.getCreditRequestsByClient())
                    .setLong("clientId", clientId)
                    .setLong("state", state.ordinal())
                    .list();
        } catch (HibernateException e) {
            throw new SCDTechnicalException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public List<CreditRequest> getCreditRequestsByEmployee(Long employeeId, CreditRequestStateEnum state) throws SCDTechnicalException {
        try {
            return getSession().createQuery(CreditRequestQueryBuilder.getCreditRequestsByEmployee())
                    .setLong("employeeId", employeeId)
                    .setLong("state", state.ordinal())
                    .list();
        } catch (HibernateException e) {
            throw new SCDTechnicalException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public List<CreditRequest> getCreditRequests(CreditRequestStateEnum state) throws SCDTechnicalException {
        try {
            return getSession().createQuery(CreditRequestQueryBuilder.getCreditRequests())
                    .setLong("state", state.ordinal())
                    .list();
        } catch (HibernateException e) {
            throw new SCDTechnicalException(e);
        }
    }
}
