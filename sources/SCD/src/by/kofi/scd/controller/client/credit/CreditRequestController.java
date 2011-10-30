package by.kofi.scd.controller.client.credit;

import by.kofi.scd.business.credit.CreditBusinessBean;
import by.kofi.scd.business.credit.CreditRequestBusinessBean;
import by.kofi.scd.business.mail.MailBusinessBean;
import by.kofi.scd.common.FacesUtil;
import by.kofi.scd.common.constants.NavigationActionEnum;
import by.kofi.scd.dto.UserContext;
import by.kofi.scd.entity.Client;
import by.kofi.scd.entity.Credit;
import by.kofi.scd.entity.CreditRequest;
import by.kofi.scd.entity.CreditRequestStateEnum;
import by.kofi.scd.exceptions.SCDBusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 * @author harchevnikov_m
 *         Date: 29/10/11
 *         Time: 16:10
 */
@Service
public class CreditRequestController implements Serializable {
    private BigDecimal monthlyCacheIncome = BigDecimal.ZERO;
    private long term;
    private BigDecimal sum = BigDecimal.ZERO;
    private BigDecimal maxSum = BigDecimal.ZERO;
    private Credit credit;
    private boolean existInProcessRequest;

    @Autowired
    private CreditBusinessBean creditBusinessBean;

    @Autowired
    private CreditRequestBusinessBean creditRequestBusinessBean;

    @Autowired
    private MailBusinessBean mailBusinessBean;

    public BigDecimal getMonthlyCacheIncome() {
        return monthlyCacheIncome;
    }

    public void setMonthlyCacheIncome(BigDecimal monthlyCacheIncome) {
        this.monthlyCacheIncome = monthlyCacheIncome;
    }

    public long getTerm() {
        return term;
    }

    public void setTerm(long term) {
        this.term = term;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public BigDecimal getMaxSum() {
        updateMaxAvailableSum();
        return maxSum;
    }

    public void setMaxSum(BigDecimal maxSum) {
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

    public String sendRequestAction() throws SCDBusinessException {
        UserContext userContext = FacesUtil.getUserContext();
        Client client = userContext.getClient();

        Date issuanceDate = Calendar.getInstance().getTime();

        /*
        save credit  request in DB
         */
        CreditRequest creditRequest = new CreditRequest();
        creditRequest.setMonthlyCacheIncome(getMonthlyCacheIncome());
        creditRequest.setTerm(getTerm());
        creditRequest.setAmount(getSum());
        creditRequest.setCredit(getCredit());
        creditRequest.setClient(client);
        creditRequest.setIssuanceDate(issuanceDate);
        creditRequest.setState(CreditRequestStateEnum.IN_PROCESS);

        this.creditRequestBusinessBean.storeCreditRequest(creditRequest);

        /*
       and than email notification
        */
        mailBusinessBean.sendCreditRequestNotificationMail(creditRequest, client);

        return NavigationActionEnum.CLIENT_CREDIT_REQUEST_SEND_COMPLETE.getValue();
    }

    public boolean isExistInProcessRequest() throws SCDBusinessException {
        return this.creditRequestBusinessBean.existCreditRequestInProcess(getCredit(),
                FacesUtil.getUserContext().getClient());
    }
}
