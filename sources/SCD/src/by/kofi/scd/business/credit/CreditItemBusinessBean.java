package by.kofi.scd.business.credit;

import by.kofi.scd.business.AbstractBusinessBean;
import by.kofi.scd.dataservice.credit.CreditDataService;
import by.kofi.scd.dataservice.credit.item.CreditItemDataService;
import by.kofi.scd.entity.Credit;
import by.kofi.scd.entity.CreditItem;
import by.kofi.scd.entity.CreditItemStateEnum;
import by.kofi.scd.exceptions.SCDBusinessException;
import by.kofi.scd.exceptions.SCDTechnicalException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author harchevnikov_m
 *         Date: 15/10/11
 *         Time: 18:55
 */
@Service
public class CreditItemBusinessBean extends AbstractBusinessBean {

    private static final Logger LOGGER = Logger.getLogger(CreditItemBusinessBean.class);
    @Autowired
    private CreditItemDataService creditItemDataService;


    @Transactional(propagation = Propagation.REQUIRED)
    public CreditItem storeCreditItem(CreditItem creditItem) throws SCDBusinessException {
        try {
            return getCRUDDataService().merge(creditItem);
        } catch (SCDTechnicalException e) {
            throw new SCDBusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<CreditItem> getCreditItemsByState(CreditItemStateEnum state) throws SCDBusinessException {
        try {
            return creditItemDataService.getCreditItemsByState(state);
        } catch (SCDTechnicalException e) {
            throw new SCDBusinessException(e);
        }
    }
}
