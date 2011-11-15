package by.kofi.scd.dataservice.employee;

import by.kofi.scd.dataservice.AbstractDataService;
import by.kofi.scd.exceptions.SCDTechnicalException;

/**
 * @author harchevnikov_m
 *         Date: 15/11/11
 *         Time: 19:52
 */
public interface EmployeeDataService extends AbstractDataService {
    public void lockCreditRequest(Long creditRequestId, Long employeeId) throws SCDTechnicalException;

    public void unlockCreditRequest(Long creditRequestId) throws SCDTechnicalException;

    public void unlockAllCreditRequestsByEmployee(Long employeeId) throws SCDTechnicalException;
}
