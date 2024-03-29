package by.kofi.scd.business;

import by.kofi.scd.dataservice.CRUDDataService;
import by.kofi.scd.entity.Account;
import by.kofi.scd.entity.AccountTypeEnum;
import by.kofi.scd.entity.SCDUser;
import by.kofi.scd.exceptions.SCDBusinessException;
import by.kofi.scd.exceptions.SCDTechnicalException;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;


@Service
public class AccountBusinessBean extends AbstractBusinessBean {

    private static final Logger LOGGER = Logger.getLogger(AccountBusinessBean.class);

    @Transactional(propagation = Propagation.REQUIRED)
    public Account getAccountByNumber(Long accountNumber) throws SCDBusinessException {
//        try {
            Query query = getCRUDDataService().getNativeHibernateSession()
                    .createQuery(" from Account  acc where  acc.accountNumber = " + accountNumber);

            return (Account) query.uniqueResult();
//        } catch (SCDTechnicalException e) {
//            throw new SCDBusinessException(e);
//        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Account createAccount(AccountTypeEnum type) throws SCDBusinessException {
        try {
            Session session = getCRUDDataService().getNativeHibernateSession();
            BigDecimal accountNo = (BigDecimal) session.createSQLQuery("select SQ_ACCOUNT_NO.nextval from dual").uniqueResult();

            Account account = new Account();
            account.setAccountNumber(accountNo.longValue());
            account.setSum(BigDecimal.ZERO);
            account.setType(type);

            return getCRUDDataService().merge(account);
        } catch (SCDTechnicalException e) {
            throw new SCDBusinessException(e);
        }
    }

    public List<Account> getAccounts() throws SCDBusinessException {
        try {
            return getCRUDDataService().list(Account.class);
        } catch (SCDTechnicalException e) {
            throw new SCDBusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Account merge(Account account) throws SCDBusinessException {
         try {
            CRUDDataService crudDataService = getCRUDDataService();
            return crudDataService.merge(account);
        } catch (SCDTechnicalException e) {
            throw new SCDBusinessException(e.getMessage(), e);
        }
    }

}