package by.kofi.scd.dataservice.credit;

import by.kofi.scd.exceptions.SCDTechnicalException;

/**
 * Queries builder util class
 *
 * @author harchevnikov_m
 *         Date: 29/10/11
 *         Time: 14:05
 */
public class CreditRequestQueryBuilder {

    public static String getCreditRequestsByCreditClient() {
        return "from CreditRequest cr where cr.client.id = :clientId and cr.credit.id = :creditId and cr.state = :state";
    }

    public static String getCreditRequestsByClient() {
        return "from CreditRequest cr where cr.client.id = :clientId and cr.state = :state";
    }

    public static String getCreditRequestsByEmployee() {
        return "from CreditRequest cr where cr.employee.id = :employeeId and cr.state = :state";
    }
    public static String getCreditRequests() {
        return "from CreditRequest cr where cr.state = :state and cr.lockedByEmployee is null";
    }

}
