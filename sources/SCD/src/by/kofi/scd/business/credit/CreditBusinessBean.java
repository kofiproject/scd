package by.kofi.scd.business.credit;

import by.kofi.scd.business.AbstractBusinessBean;
import by.kofi.scd.business.RoleBusinessBean;
import by.kofi.scd.dataservice.CRUDDataService;
import by.kofi.scd.dataservice.client.ClientDataService;
import by.kofi.scd.entity.*;
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
public class CreditBusinessBean extends AbstractBusinessBean {

    private static final Logger LOGGER = Logger.getLogger(CreditBusinessBean.class);

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Credit> getCredits() throws SCDBusinessException {
        try {
            return getCRUDDataService().list(Credit.class);
        } catch (SCDTechnicalException e) {
            throw new SCDBusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Credit getCreditById(Long creditId) throws SCDBusinessException {
        try {
            return getCRUDDataService().find(Credit.class, creditId);
        } catch (SCDTechnicalException e) {
            throw new SCDBusinessException(e);
        }
    }

    /**
     * calculates max available credit sum
     *
     * @param credit             credit
     * @param term               term in months
     * @param monthlyCacheIncome cache income
     * @return max available credit sum
     */
    public BigDecimal calculateMaxAvailableSum(Credit credit, Long term, BigDecimal monthlyCacheIncome) {
        if (credit == null) {
            return BigDecimal.ZERO;
        }
        term = term != null ? term : 0L;
        monthlyCacheIncome = monthlyCacheIncome != null ? monthlyCacheIncome : BigDecimal.ZERO;

        BigDecimal termBD = new BigDecimal(term);

        return BigDecimal.ONE.add(credit.getMaxSumPercent()).multiply(monthlyCacheIncome).multiply(termBD);
    }


}
