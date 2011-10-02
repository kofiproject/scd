package by.kofi.scd.dataservice;

import by.kofi.scd.entity.Department;
import by.kofi.scd.exceptions.SCDTechnicalException;

import java.util.List;

/**
 * @author harchevnikov_m
 *         Date: 02/10/11
 *         Time: 16:59
 */
public interface AbstractDataService {

    /**
     * @return base operations service
     */
    public CRUDDataService getHibernateCRUDService();
}
