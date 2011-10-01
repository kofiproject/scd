package by.kofi.scd.dal;

import by.kofi.scd.entity.Role;
import by.kofi.scd.util.HibernateUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

import static junit.framework.Assert.assertNotNull;

/**
 * @author harchevnikov_m
 *         Date: 18.09.11
 *         Time: 21:27
 */
public class HibernateCRUDServiceTest {
    @Before
    public void setUp() {
        Locale.setDefault(Locale.ENGLISH);
    }

    @Test
    public void testCreate() throws Exception {
        Role role = new Role();
        role.setName("name2");

//        HibernateCRUDService crudService = new HibernateCRUDService(HibernateUtil.getSession());
//        role = crudService.create(role);

        assertNotNull(role);
    }
}
