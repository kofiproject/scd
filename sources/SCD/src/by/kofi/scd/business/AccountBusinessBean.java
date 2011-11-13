package by.kofi.scd.business;

import by.kofi.scd.entity.Account;
import by.kofi.scd.entity.SCDUser;
import by.kofi.scd.exceptions.SCDBusinessException;
import by.kofi.scd.exceptions.SCDTechnicalException;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;


@Service
public class AccountBusinessBean extends AbstractBusinessBean {

    private static final Logger LOGGER = Logger.getLogger(AccountBusinessBean.class);

    @Transactional(propagation = Propagation.REQUIRED)
    public Account getAccountByIdentityId(Long id) throws SCDBusinessException {
        try {
            Account account = getCRUDDataService().find(Account.class, id);
            return account;
        } catch (SCDTechnicalException e) {
            throw new SCDBusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Account createAccount() throws SCDBusinessException {
        try {
            Session session = getCRUDDataService().getNativeHibernateSession();
            BigDecimal accountNo = (BigDecimal) session.createSQLQuery("select SQ_ACCOUNT_NO.nextval from dual").uniqueResult();

            Account account = new Account();
            account.setAccountNumber(accountNo.longValue());

            return getCRUDDataService().merge(account);
        } catch (SCDTechnicalException e) {
            throw new SCDBusinessException(e);
        }
    }
}