package by.kofi.scd.controller.client.credit;

import by.kofi.scd.business.credit.CreditBusinessBean;
import by.kofi.scd.common.constants.NavigationActionEnum;
import by.kofi.scd.entity.Credit;
import by.kofi.scd.exceptions.SCDBusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * @author harchevnikov_m
 *         Date: 29/10/11
 *         Time: 16:10
 */
@Service
public class CreditRequestController implements Serializable {
    private long monthlyCacheIncome;
    private long term;
    private long sum;
    private long maxSum;
    private Credit credit;

    @Autowired
    private CreditBusinessBean creditBusinessBean;

    public long getMonthlyCacheIncome() {
        return monthlyCacheIncome;
    }

    public void setMonthlyCacheIncome(long monthlyCacheIncome) {
        this.monthlyCacheIncome = monthlyCacheIncome;
    }

    public long getTerm() {
        return term;
    }

    public void setTerm(long term) {
        this.term = term;
    }

    public long getSum() {
        return sum;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }

    public long getMaxSum() {
        updateMaxAvailableSum();
        return maxSum;
    }

    public void setMaxSum(long maxSum) {
        this.maxSum = maxSum;
    }


    public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
        this.term = credit.getMaxTerm();

        updateMaxAvailableSum();
    }

    private void updateMaxAvailableSum() {
        this.maxSum = this.creditBusinessBean.calculateMaxAvailableSum(getCredit(), getTerm(), getMonthlyCacheIncome());
    }

    public String creditDetailsAction() throws SCDBusinessException {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest myRequest = (HttpServletRequest) context.getExternalContext().getRequest();
        long creditId = Long.parseLong(myRequest.getParameter("creditId"));

        setCredit(creditBusinessBean.getCreditById(creditId));
        return NavigationActionEnum.CLIENT_CREDIT_DETAILS.getValue();
    }

}
