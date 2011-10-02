package by.kofi.scd.business;

import by.kofi.scd.dataservice.department.DepartmentDataService;
import by.kofi.scd.entity.Department;
import by.kofi.scd.exceptions.SCDTechnicalException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author harchevnikov_m
 *         Date: 02/10/11
 *         Time: 01:09
 */
@Service("departmentBusinessBean")
@Scope("session")
public class DepartmentBusinessBean {
    private static final Logger LOGGER = Logger.getLogger(DepartmentBusinessBean.class);

    @Autowired
    private DepartmentDataService departmentService;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public String processItems(List<Long> ids) {

        /*    Department dep = new Department();
                dep.setAddress("Аддресс");
                dep.setDepartmentNo("нумер");
                dep = departmentService.getHibernateCRUDService().merge(dep);

        */
        List<Department> items = null;
        try {
            items = departmentService.getItems(ids);
        } catch (SCDTechnicalException e) {

            e.printStackTrace();
        }
        return items.size() + " ";
    }
}
