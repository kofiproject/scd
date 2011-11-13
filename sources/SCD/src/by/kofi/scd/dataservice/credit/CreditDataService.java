package by.kofi.scd.dataservice.credit;

import by.kofi.scd.dataservice.AbstractDataService;
import by.kofi.scd.entity.Credit;
import by.kofi.scd.entity.CreditRequest;
import by.kofi.scd.entity.CreditRequestStateEnum;
import by.kofi.scd.exceptions.SCDTechnicalException;

import java.util.List;

/**
 * @author harchevnikov_m
 *         Date: 15/10/11
 *         Time: 21:06
 */
public interface CreditDataService extends AbstractDataService {

    /**
     * Credit by name
     *
     * @param name name
     * @return credit
     * @throws SCDTechnicalException hql error
     */
    public Credit getCreditByName(String name) throws SCDTechnicalException;

}