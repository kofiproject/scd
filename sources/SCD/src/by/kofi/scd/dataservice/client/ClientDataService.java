package by.kofi.scd.dataservice.client;

import by.kofi.scd.dataservice.AbstractDataService;
import by.kofi.scd.dataservice.CRUDDataService;
import by.kofi.scd.entity.Client;
import by.kofi.scd.entity.CreditItem;
import by.kofi.scd.entity.CreditItemStateEnum;
import by.kofi.scd.exceptions.SCDTechnicalException;

import java.io.Serializable;
import java.util.List;

/**
 * @author harchevnikov_m
 *         Date: 15/10/11
 *         Time: 21:06
 */
public interface ClientDataService extends AbstractDataService {

    /**
     * Retrieve client by passport data(ignore series case)
     *
     * @param passportSeries passport series
     * @param passportNo     passport no
     * @return client
     * @throws SCDTechnicalException hql error
     */
    public Client getClientByPassportData(String passportSeries, Long passportNo) throws SCDTechnicalException;

    /**
     * Retrieve creditItems list by status and client id
     *
     * @param clientId client id
     * @param status   credit items status
     * @return creditItems list
     * @throws SCDTechnicalException hibernate exc
     */
    public List<CreditItem> getCreditItems(Long clientId, CreditItemStateEnum status) throws SCDTechnicalException;
}
