package by.kofi.scd.dataservice.credit;

import by.kofi.scd.dataservice.AbstractDataService;
import by.kofi.scd.entity.*;
import by.kofi.scd.exceptions.SCDTechnicalException;

import java.util.List;

/**
 * @author harchevnikov_m
 *         Date: 15/10/11
 *         Time: 21:06
 */
public interface CreditRequestDataService extends AbstractDataService {

    /**
     * Retrieve list of creditRequests
     *
     * @param clientId clientId
     * @param creditId creditId
     * @param state    state
     * @return creditRequests
     * @throws SCDTechnicalException hql error
     */
    public List<CreditRequest> getCreditRequests(Long clientId, Long creditId, CreditRequestStateEnum state) throws SCDTechnicalException;

    /**
     * Retrieve list of creditRequests
     *
     * @param clientId clientId
     * @param state    state
     * @return creditRequests
     * @throws SCDTechnicalException hql error
     */
    public List<CreditRequest> getCreditRequests(Long clientId, CreditRequestStateEnum state) throws SCDTechnicalException;

}