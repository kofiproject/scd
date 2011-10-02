package by.kofi.scd.dataservice.department;

import by.kofi.scd.dataservice.AbstractDataService;
import by.kofi.scd.entity.Department;
import by.kofi.scd.exceptions.SCDTechnicalException;

import java.util.List;

/**
 * @author harchevnikov_m
 *         Date: 02/10/11
 *         Time: 16:59
 */
public interface DepartmentDataService extends AbstractDataService {

    /**
     * @param ids
     * @return
     * @throws SCDTechnicalException
     */
    public List<Department> getItems(List<Long> ids) throws SCDTechnicalException;
}
