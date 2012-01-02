package by.kofi.scd.business;

import by.kofi.scd.entity.PercentHistory;
import by.kofi.scd.exceptions.SCDBusinessException;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by IntelliJ IDEA.
 * User: Book
 * Date: 02.01.12
 * Time: 17:44
 * To change this template use File | Settings | File Templates.
 */
public class PercentHistoryBusinessBean extends AbstractBusinessBean
{
    private static final Logger LOGGER = Logger.getLogger(AccountBusinessBean.class);

    @Transactional(propagation = Propagation.REQUIRED)
    public PercentHistory getPercentHistoryByNumberAccount(Long accountNumber) throws SCDBusinessException
    {
       Query query = getCRUDDataService().getNativeHibernateSession()
                    .createQuery(" from   " + accountNumber);

            return (PercentHistory) query.uniqueResult();

    }
}
