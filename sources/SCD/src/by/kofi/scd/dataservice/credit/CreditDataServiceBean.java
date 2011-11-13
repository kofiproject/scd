package by.kofi.scd.dataservice.credit;

import by.kofi.scd.dataservice.AbstractDataServiceBean;
import by.kofi.scd.entity.Credit;
import by.kofi.scd.entity.CreditRequest;
import by.kofi.scd.entity.CreditRequestStateEnum;
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
public class CreditDataServiceBean extends AbstractDataServiceBean implements CreditDataService {
    /**
     * {@inheritDoc}
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Credit getCreditByName(String name) throws SCDTechnicalException {
        try {
            return (Credit) getSession().createQuery(CreditQueryBuilder.getCreditByName())
                    .setString("name", name)
                    .uniqueResult();
        } catch (HibernateException e) {
            throw new SCDTechnicalException(e);
        }
    }
}
