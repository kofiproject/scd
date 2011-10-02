package by.kofi.scd.dataservice.department;

import by.kofi.scd.entity.Department;
import by.kofi.scd.exceptions.SCDTechnicalException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author harchevnikov_m
 *         Date: 02/10/11
 *         Time: 16:59
 */
public interface DepartmentService {

    /**
     *
     * @param ids
     * @return
     * @throws SCDTechnicalException
     */
    public List<Department> getItems(List<Long> ids) throws SCDTechnicalException;
}
