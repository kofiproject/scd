package by.kofi.scd.util;

import by.kofi.scd.entity.Role;
import by.kofi.scd.entity.SCDUser;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.assertNotNull;

/**
 * @author harchevnikov_m
 *         Date: 18.09.11
 *         Time: 18:52
 */
public class HibernateUtilTest {
    @Before
    public void setUp() {
        Locale.setDefault(Locale.ENGLISH);
    }



    @Test
    public void testGetSession() throws Exception {
        Session session = HibernateUtil.getSession();

        assertNotNull(session);
    }

    @Test
    public void test_sessionInit() throws Exception {
/*
        Role role = new Role();
        role.setName("Name");

        SCDUser user = new SCDUser();
        user.setLogin("login");
        user.setPassword("password");
        user.setRole(role);
*/

        Session session = HibernateUtil.getSession();
        session.beginTransaction();

//        SQLQuery sqlQuery = session.createSQLQuery("select * from USERS");
//        List list1 = sqlQuery.list();
//        Object o = session.get(Role.class, 150L);
//        List list = session.createCriteria(Role.class).addOrder(Order.asc("name")).list();

        session.getTransaction().commit();

        session.close();

        assertNotNull(session);
    }
}
