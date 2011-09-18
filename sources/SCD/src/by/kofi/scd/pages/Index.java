package by.kofi.scd.pages;

import by.kofi.scd.dal.HibernateCRUDService;
import by.kofi.scd.entity.SCDUser;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;

import java.util.Date;

/**
 * Start page
 *
 * @author harchevnikov_m
 *         Date: 18.09.11
 *         Time: 22:14
 */
public class Index {

    @Property
    private SCDUser user;

    public Date getCurrentDate() {
        HibernateCRUDService crudService = new HibernateCRUDService();
        Session session2 = crudService.getSession();
        session2.beginTransaction();

        return new Date();
    }
}