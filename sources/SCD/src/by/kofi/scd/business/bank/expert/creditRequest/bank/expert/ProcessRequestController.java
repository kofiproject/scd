package by.kofi.scd.business.bank.expert.creditRequest.bank.expert;

import by.kofi.scd.business.AccountBusinessBean;
import by.kofi.scd.business.credit.CreditItemBusinessBean;
import by.kofi.scd.business.credit.CreditRequestBusinessBean;
import by.kofi.scd.business.employee.EmployeeBusinessBean;
import by.kofi.scd.business.mail.MailBusinessBean;
import by.kofi.scd.common.FacesUtil;
import by.kofi.scd.common.constants.NavigationActionEnum;
import by.kofi.scd.entity.*;
import by.kofi.scd.exceptions.SCDBusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.faces.context.FacesContext;
import java.math.BigDecimal;
import java.sql.Timestamp;
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
    private boolean lockedCreditRequest;

    @Autowired
    private CreditRequestBusinessBean creditRequestBusinessBean;
    @Autowired
    private AccountBusinessBean accountBusinessBean;
    @Autowired
    private MailBusinessBean mailBusinessBean;
    @Autowired
    private EmployeeBusinessBean employeeBusinessBean;

    public boolean getLockedCreditRequest() {
        return lockedCreditRequest;
    }

    public void setLockedCreditRequest(boolean lockedCreditRequest) {
        this.lockedCreditRequest = lockedCreditRequest;
    }

    public Long getCreditRequestId() {
        return creditRequestId;
    }

    public void setCreditRequestId(Long creditRequestId) throws SCDBusinessException {
        this.creditRequestId = creditRequestId;
        CreditRequest creditRequestById = creditRequestBusinessBean.getCreditRequestById(creditRequestId);
        setCreditRequest(creditRequestById);

        this.lockedCreditRequest = false;
        Employee lockedByEmployee = creditRequestById.getLockedByEmployee();
        if (lockedByEmployee != null) {
            long contextEmployeeId = FacesUtil.getUserContext().getEmployee().getEmployeeId();
            long employeeId = lockedByEmployee.getEmployeeId();
            if (employeeId != contextEmployeeId) {
                this.lockedCreditRequest = true;
                return;
            }
        }

        lockCreditRequest();
    }

    public CreditRequest getCreditRequest() {
        return creditRequest;
    }

    public void setCreditRequest(CreditRequest creditRequest) {
        this.creditRequest = creditRequest;
    }

    public String confirmAction() throws SCDBusinessException {
        //update request
        Employee employee = FacesUtil.getUserContext().getEmployee();
        creditRequest.setEmployee(employee);
        creditRequest.setProcessingDate(new Date());
        creditRequest.setState(CreditRequestStateEnum.CONFIRMED);
        creditRequest = creditRequestBusinessBean.storeCreditRequest(creditRequest);

        //send email notification
        mailBusinessBean.sendCreditRequestConfirmMail(creditRequest);

        unLockCreditRequest();

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

        unLockCreditRequest();

        return NavigationActionEnum.EXPERT_CREDIT_REQUEST_LIST.getValue();
    }

    public void lockCreditRequest() throws SCDBusinessException {
        Employee employee = FacesUtil.getUserContext().getEmployee();
        this.employeeBusinessBean.lockCreditRequest(getCreditRequestId(), employee.getEmployeeId());
    }


    public void unLockCreditRequest() throws SCDBusinessException {
        this.employeeBusinessBean.unlockCreditRequest(getCreditRequestId());
    }

}
