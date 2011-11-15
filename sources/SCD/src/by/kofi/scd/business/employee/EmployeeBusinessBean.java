package by.kofi.scd.business.employee;

import by.kofi.scd.business.AbstractBusinessBean;
import by.kofi.scd.dataservice.credit.CreditDataService;
import by.kofi.scd.dataservice.employee.EmployeeDataService;
import by.kofi.scd.entity.Credit;
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
public class EmployeeBusinessBean extends AbstractBusinessBean {

    private static final Logger LOGGER = Logger.getLogger(EmployeeBusinessBean.class);

    @Autowired
    private EmployeeDataService employeeDataService;

    @Transactional(propagation = Propagation.REQUIRED)
    public void lockCreditRequest(Long creditRequestId, Long employeeId) throws SCDBusinessException {
        try {
            employeeDataService.lockCreditRequest(creditRequestId, employeeId);
        } catch (SCDTechnicalException e) {
            throw new SCDBusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void unlockCreditRequest(Long creditRequestId) throws SCDBusinessException {
        try {
            employeeDataService.unlockCreditRequest(creditRequestId);
        } catch (SCDTechnicalException e) {
            throw new SCDBusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void unlockAllCreditRequestsByEmployee(Long employeeId) throws SCDBusinessException {
        try {
            employeeDataService.unlockAllCreditRequestsByEmployee(employeeId);
        } catch (SCDTechnicalException e) {
            throw new SCDBusinessException(e);
        }
    }
}
