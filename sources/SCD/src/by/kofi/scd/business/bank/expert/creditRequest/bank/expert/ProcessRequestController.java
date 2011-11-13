package by.kofi.scd.business.bank.expert.creditRequest.bank.expert;

import by.kofi.scd.business.AccountBusinessBean;
import by.kofi.scd.business.credit.CreditRequestBusinessBean;
import by.kofi.scd.business.mail.MailBusinessBean;
import by.kofi.scd.common.FacesUtil;
import by.kofi.scd.common.constants.NavigationActionEnum;
import by.kofi.scd.entity.Account;
import by.kofi.scd.entity.CreditRequest;
import by.kofi.scd.entity.CreditRequestStateEnum;
import by.kofi.scd.entity.Employee;
import by.kofi.scd.exceptions.SCDBusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.faces.context.FacesContext;
import java.util.Date;

/**
 * @author harchevnikov_m
 *         Date: 13/11/11
 *         Time: 18:48
 */
@Controller
public class ProcessRequestController {
    private Long creditRequestId;
    private CreditRequest creditRequest;

    @Autowired
    private CreditRequestBusinessBean creditRequestBusinessBean;
    @Autowired
    private AccountBusinessBean accountBusinessBean;
    @Autowired
    private MailBusinessBean mailBusinessBean;


    public Long getCreditRequestId() {
        return creditRequestId;
    }

    public void setCreditRequestId(Long creditRequestId) throws SCDBusinessException {
        this.creditRequestId = creditRequestId;
        CreditRequest creditRequestById = creditRequestBusinessBean.getCreditRequestById(creditRequestId);
        setCreditRequest(creditRequestById);
    }

    public CreditRequest getCreditRequest() {
        return creditRequest;
    }

    public void setCreditRequest(CreditRequest creditRequest) {
        this.creditRequest = creditRequest;
    }

    public String confirmAction() throws SCDBusinessException {
        //update request
        Account account = accountBusinessBean.createAccount();
        Employee employee = FacesUtil.getUserContext().getEmployee();
        creditRequest.setEmployee(employee);
        creditRequest.setState(CreditRequestStateEnum.CONFIRMED);
        creditRequest.setAccount(account);
        creditRequest.setProcessingDate(new Date());
        CreditRequest request = creditRequestBusinessBean.storeCreditRequest(creditRequest);

        //send email notification
        mailBusinessBean.sendCreditRequestConfirmMail(request);

        return NavigationActionEnum.EXPERT_CREDIT_REQUEST_LIST.getValue();
    }

    public String rejectAction() throws SCDBusinessException {
        //update request
        Employee employee = FacesUtil.getUserContext().getEmployee();
        creditRequest.setEmployee(employee);
        creditRequest.setState(CreditRequestStateEnum.REJECTED);
        creditRequest.setProcessingDate(new Date());
        CreditRequest request = creditRequestBusinessBean.storeCreditRequest(creditRequest);

        //send email notification
        mailBusinessBean.sendCreditRequestRejectMail(request);

        return NavigationActionEnum.EXPERT_CREDIT_REQUEST_LIST.getValue();
    }
}
