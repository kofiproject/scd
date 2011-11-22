package by.kofi.scd.dataservice.credit.item;

import by.kofi.scd.dataservice.AbstractDataService;
import by.kofi.scd.entity.CreditItem;
import by.kofi.scd.entity.CreditItemStateEnum;
import by.kofi.scd.entity.CreditRequest;
import by.kofi.scd.entity.CreditRequestStateEnum;
import by.kofi.scd.exceptions.SCDTechnicalException;

import java.util.List;

/**
 * @author harchevnikov_m
 *         Date: 15/10/11
 *         Time: 21:06
 */
public interface CreditItemDataService extends AbstractDataService {

    /**
     * Retrieve creditItems by state
     *
     * @param state state
     * @return creditItems
     * @throws SCDTechnicalException hql error
     */
    public List<CreditItem> getCreditItemsByState(CreditItemStateEnum state) throws SCDTechnicalException;
}