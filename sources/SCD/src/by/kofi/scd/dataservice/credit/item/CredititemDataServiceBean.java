package by.kofi.scd.dataservice.credit.item;

import by.kofi.scd.dataservice.AbstractDataServiceBean;
import by.kofi.scd.dataservice.credit.request.CreditRequestDataService;
import by.kofi.scd.dataservice.credit.request.CreditRequestQueryBuilder;
import by.kofi.scd.entity.CreditItem;
import by.kofi.scd.entity.CreditItemStateEnum;
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
public class CreditItemDataServiceBean extends AbstractDataServiceBean implements CreditItemDataService {

    /**
     * {@inheritDoc}
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public List<CreditItem> getCreditItemsByState(CreditItemStateEnum state) throws SCDTechnicalException {
        try {
            return getSession().createQuery(CreditItemQueryBuilder.getCreditItemsByState())
                    .setLong("state", state.ordinal())
                    .list();
        } catch (HibernateException e) {
            throw new SCDTechnicalException(e);
        }
    }
}
