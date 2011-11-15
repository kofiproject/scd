package by.kofi.scd.dataservice.employee;

/**
 * Queries builder util class
 *
 * @author harchevnikov_m
 *         Date: 29/10/11
 *         Time: 14:05
 */
public class EmployeeQueryBuilder {

    public static String getLockNativeQuery(Long creditRequestId, Long employeeId) {
        return " UPDATE CREDIT_REQUEST SET LOCKED_BY_EMPLOYEE_ID =  " + employeeId +
                " WHERE CREDIT_REQUEST_ID =  " + creditRequestId;
    }

    public static String getUnlockAllNativeQuery(Long employeeId) {
        return " UPDATE CREDIT_REQUEST SET LOCKED_BY_EMPLOYEE_ID = NULL " +
                " WHERE LOCKED_BY_EMPLOYEE_ID =  " + employeeId;
    }

}
