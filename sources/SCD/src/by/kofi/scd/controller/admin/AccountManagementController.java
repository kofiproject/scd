package by.kofi.scd.controller.admin;

import by.kofi.scd.business.AccountBusinessBean;
import by.kofi.scd.business.credit.CreditBusinessBean;
import by.kofi.scd.business.credit.CreditItemBusinessBean;
import by.kofi.scd.entity.Account;
import by.kofi.scd.entity.Credit;
import by.kofi.scd.exceptions.SCDBusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @author harchevnikov_m
 *         Date: 26.09.11
 *         Time: 22:34
 */
@Controller
@Scope("request")
public class AccountManagementController {
    private Account account;
    private BigDecimal sum;

    @Autowired
    @Qualifier("ciBB")
    private CreditItemBusinessBean creditItemBusinessBean;
    @Autowired
    private AccountBusinessBean accountBusinessBean;

    public Account getAccount() throws SCDBusinessException {
        return creditItemBusinessBean.getBankAccount();
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addSum() throws SCDBusinessException {
        Account account1 = getAccount();
        account1.setSum(account1.getSum().add(getSum()));

        accountBusinessBean.merge(account1);
    }
}
