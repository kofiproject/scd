package by.kofi.scd.business.credit;

import by.kofi.scd.business.AbstractBusinessBean;
import by.kofi.scd.business.AccountBusinessBean;
import by.kofi.scd.dataservice.CRUDDataService;
import by.kofi.scd.dataservice.credit.item.CreditItemDataService;
import by.kofi.scd.entity.*;
import by.kofi.scd.exceptions.SCDBusinessException;
import by.kofi.scd.exceptions.SCDTechnicalException;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author harchevnikov_m
 *         Date: 15/10/11
 *         Time: 18:55
 */
@Service
public class CreditItemBusinessBean extends AbstractBusinessBean {

    private static final Logger LOGGER = Logger.getLogger(CreditItemBusinessBean.class);
    @Autowired
    private CreditItemDataService creditItemDataService;

    @Autowired
    private AccountBusinessBean accountBusinessBean;


    @Transactional(propagation = Propagation.REQUIRED)
    public CreditItem getCreditItemById(long creditItemId) throws SCDBusinessException {
        try {
            return getCRUDDataService().find(CreditItem.class, creditItemId);
        } catch (SCDTechnicalException e) {
            throw new SCDBusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public CreditItem storeCreditItem(CreditItem creditItem) throws SCDBusinessException {
        try {

            CRUDDataService crudDataService = getCRUDDataService();
            crudDataService.merge(creditItem.getCreditAccount());
            return crudDataService.merge(creditItem);
        } catch (SCDTechnicalException e) {
            throw new SCDBusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<CreditItem> getCreditItemsByState(CreditItemStateEnum state) throws SCDBusinessException {
        try {
            return creditItemDataService.getCreditItemsByState(state);
        } catch (SCDTechnicalException e) {
            throw new SCDBusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public CreditItem createCreditItem(CreditRequest creditRequest) throws SCDBusinessException {
        CRUDDataService crudDataService = getCRUDDataService();

        creditRequest.setState(CreditRequestStateEnum.ISSUED);

        BigDecimal creditSum = creditRequest.getSum();

        CreditItem creditItem = new CreditItem();
        creditItem.setSum(creditSum);
        creditItem.setClient(creditRequest.getClient());
        creditItem.setCredit(creditRequest.getCredit());
        creditItem.setIssuanceDate(new Date());
        creditItem.setTerm(creditRequest.getTerm());
        creditItem.setState(CreditItemStateEnum.ACTIVE);
        creditItem.setLastUpdated(new Date());

        Account debitAccount = accountBusinessBean.createAccount(AccountTypeEnum.DEBIT);
        Account creditAccount = accountBusinessBean.createAccount(AccountTypeEnum.CREDIT);
        creditAccount.setSum(creditSum);
        Account paymentsAccount = accountBusinessBean.createAccount(AccountTypeEnum.PAYMENT);

        creditItem.setDebitAccount(debitAccount);
        creditItem.setCreditAccount(creditAccount);
        creditItem.setPaymentsAccount(paymentsAccount);

        Account bankAccount = getBankAccount();
        transfer(bankAccount, debitAccount, creditSum);


        try {
            crudDataService.merge(creditRequest);
            return crudDataService.merge(creditItem);
        } catch (SCDTechnicalException e) {
            throw new SCDBusinessException(e);
        }

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public CreditItem closeCreditItem(long accountNo) throws SCDBusinessException {
        try {
            Account account = accountBusinessBean.getAccountByNumber(accountNo);

            CRUDDataService crudDataService = getCRUDDataService();

            CreditItem creditItem = account.getCreditItem();
            creditItem.setState(CreditItemStateEnum.CLOSED);
            creditItem.setClosingDate(new Date());

            return crudDataService.merge(creditItem);
        } catch (SCDTechnicalException e) {
            throw new SCDBusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Account getBankAccount() throws SCDBusinessException {
        try {
            return getCRUDDataService().find(Account.class, 0L);
        } catch (SCDTechnicalException e) {
            throw new SCDBusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void transfer(Account from, Account to, BigDecimal sum) throws SCDBusinessException {
        try {
            from.setSum(from.getSum().subtract(sum));
            to.setSum(from.getSum().add(sum));

            CRUDDataService crudDataService = getCRUDDataService();
            crudDataService.merge(from);
            crudDataService.merge(to);

        } catch (SCDTechnicalException e) {
            throw new SCDBusinessException(e);
        }
    }
/*
 private int newCreditsCount;
    private int closedCreditsCount;
    private int withPenaltyCreditsCount;
*/
    @Transactional(propagation = Propagation.REQUIRED)
    public int getNewCreditsCount(Date start, Date end) {
        Session session = getCRUDDataService().getNativeHibernateSession();
        Query query = session.createQuery("select count(ci) from CreditItem ci where ci.issuanceDate between :start and :end")
                .setDate("start", start).setDate("end", end);
        Object o = query.uniqueResult();
        return ((Long)o).intValue();
    }

     @Transactional(propagation = Propagation.REQUIRED)
     public int getClosedCount(Date start, Date end) {
        Session session = getCRUDDataService().getNativeHibernateSession();
        Query query = session.createQuery("select count(ci) from CreditItem ci where ci.closingDate between :start and :end")
                .setDate("start", start).setDate("end", end);
        Object o = query.uniqueResult();
        return ((Long)o).intValue();
    }

     @Transactional(propagation = Propagation.REQUIRED)
    public int getWithPenaltyCount(Date start, Date end) {
        Session session = getCRUDDataService().getNativeHibernateSession();
        Query query = session.createQuery("select ci from CreditItem ci where ci.issuanceDate >= :start")
                .setDate("start", start);
        List<CreditItem> list = query.list();
        Calendar calendar = Calendar.getInstance();
        int result = 0;
        for (CreditItem creditItem : list) {
            Date issuanceDate = creditItem.getIssuanceDate();
            if (issuanceDate.compareTo(start) >= 0) {
                calendar.setTime(issuanceDate);
                calendar.add(Calendar.MONTH, creditItem.getTerm().intValue());
                if (calendar.getTime().compareTo(end) <= 0) {
                    result++;
                }
            }
        }

        return result;
    }

}
