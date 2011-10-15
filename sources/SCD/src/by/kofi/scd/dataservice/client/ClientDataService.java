package by.kofi.scd.dataservice.client;

import by.kofi.scd.dataservice.AbstractDataService;
import by.kofi.scd.dataservice.CRUDDataService;
import by.kofi.scd.entity.Client;
import by.kofi.scd.exceptions.SCDTechnicalException;

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
}
