package by.kofi.scd.dataservice.client;

import by.kofi.scd.entity.CreditItem;
import by.kofi.scd.entity.CreditItemStateEnum;
import by.kofi.scd.exceptions.SCDTechnicalException;
import org.hibernate.HibernateException;

import java.util.List;

/**
 * Queries builder util class
 *
 * @author harchevnikov_m
 *         Date: 29/10/11
 *         Time: 14:05
 */
public class ClientQueryBuilder {

    public static String buildCreditItemsQuery() throws SCDTechnicalException {
        return "from CreditItem  ci where ci.client.id = :clientId and ci.state = :state";
    }

    public static String buildCreditItemsByStateQuery() throws SCDTechnicalException {
        return "from CreditItem  ci where ci.state = :state";
    }

    public static String buildClientByPassportDataQuery() throws SCDTechnicalException {
        return "from Client cl where upper(cl.passportSeries) = upper(:passportSeries) and cl.passportNo = :passportNo";
    }

}
