package by.kofi.scd.business;

import by.kofi.scd.dataservice.AbstractDataServiceBean;
import by.kofi.scd.dataservice.CRUDDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author harchevnikov_m
 *         Date: 15/10/11
 *         Time: 18:56
 */
public class AbstractBusinessBean {
    @Autowired
    @Qualifier("abstractDataServiceBean")
    private AbstractDataServiceBean abstractDataServiceBean;

    protected CRUDDataService getCRUDDataService() {
        return this.abstractDataServiceBean.getHibernateCRUDService();
    }
}
