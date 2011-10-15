package by.kofi.scd.dataservice.user;

import by.kofi.scd.dataservice.AbstractDataService;
import by.kofi.scd.entity.Client;
import by.kofi.scd.entity.Department;
import by.kofi.scd.exceptions.SCDTechnicalException;

import java.util.List;

/**
 * @author harchevnikov_m
 *         Date: 02/10/11
 *         Time: 16:59
 */
public interface ClientDataService extends AbstractDataService {

    public Client getItems(List<Long> ids) throws SCDTechnicalException;
}
