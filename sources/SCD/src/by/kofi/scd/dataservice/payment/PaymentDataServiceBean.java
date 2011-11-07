package by.kofi.scd.dataservice.payment;

import by.kofi.scd.dataservice.AbstractDataServiceBean;
import by.kofi.scd.dataservice.credit.CreditRequestDataService;
import by.kofi.scd.dataservice.credit.CreditRequestQueryBuilder;
import by.kofi.scd.entity.CreditRequest;
import by.kofi.scd.entity.CreditRequestStateEnum;
import by.kofi.scd.entity.Payment;
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
public class PaymentDataServiceBean extends AbstractDataServiceBean implements PaymentDataService {

    /**
     * {@inheritDoc}
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public List<Payment> getPaymentsByAccount(Long accountNumber) throws SCDTechnicalException {
        try {
            return getSession().createQuery(PaymentQueryBuilder.getPaymentsByAccount())
                    .setLong("account", accountNumber)
                    .list();
        } catch (HibernateException e) {
            throw new SCDTechnicalException(e);
        }
    }
}
