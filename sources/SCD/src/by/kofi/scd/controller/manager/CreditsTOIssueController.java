package by.kofi.scd.controller.manager;

import by.kofi.scd.business.credit.CreditItemBusinessBean;
import by.kofi.scd.business.credit.CreditRequestBusinessBean;
import by.kofi.scd.business.download.FileDownloadService;
import by.kofi.scd.business.download.contract.ContractGeneratorService;
import by.kofi.scd.common.FacesUtil;
import by.kofi.scd.dto.UserContext;
import by.kofi.scd.entity.Account;
import by.kofi.scd.entity.CreditRequest;
import by.kofi.scd.exceptions.SCDBusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * @author harchevnikov_m
 *         Date: 26.09.11
 *         Time: 22:34
 */
@Controller
@Scope("request")
public class CreditsToIssueController {
    private Long creditRequestId;
    private File generatedContract;

    private boolean enoughMoney;

    @Autowired
    @Qualifier("ciBB")
    private CreditItemBusinessBean creditItemBusinessBean;
    @Autowired
    private CreditRequestBusinessBean creditRequestBusinessBean;

    @Autowired
    private ContractGeneratorService contractGeneratorService;
    @Autowired
    private FileDownloadService fileDownloadService;

    public void updateEnoughMoney() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest myRequest = (HttpServletRequest) context.getExternalContext().getRequest();
            Long creditRequestId = Long.parseLong(myRequest.getParameter("creditRequestId"));

//            Long creditRequestId = Long.parseLong(req_id.toString());
            CreditRequest creditRequest = creditRequestBusinessBean.getCreditRequestById(creditRequestId);
            Account bankAccount = creditItemBusinessBean.getBankAccount();
            if (creditRequest.getSum().compareTo(bankAccount.getSum()) > 0) {
                enoughMoney = false;
            } else {
                enoughMoney = true;
            }

        } catch (SCDBusinessException e) {

        }
    }

    public boolean getEnoughMoney() {
            return enoughMoney;
    }

    public void setEnoughMoney(boolean enoughMoney) {
        this.enoughMoney = enoughMoney;
    }


    public File getGeneratedContract() {
        return generatedContract;
    }

    public void setGeneratedContract(File generatedContract) {
        this.generatedContract = generatedContract;
    }

    public Long getCreditRequestId() {
        return creditRequestId;
    }

    public void setCreditRequestId(Long creditRequestId) {
        this.creditRequestId = creditRequestId;
    }

    public void issueCreditRequest() throws SCDBusinessException {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest myRequest = (HttpServletRequest) context.getExternalContext().getRequest();
            Long creditRequestId = Long.parseLong(myRequest.getParameter("creditRequestId"));

            CreditRequest creditRequest = creditRequestBusinessBean.getCreditRequestById(creditRequestId);

            creditItemBusinessBean.createCreditItem(creditRequest);

            FacesUtil.getSession().setAttribute("req_id", creditRequestId);

        } catch (SCDBusinessException e) {
            throw new SCDBusinessException(e.getMessage(), e);
        }
    }

    /**
     * Write report in http response and remove file
     *
     * @throws SCDBusinessException report download error
     */
    public void downloadContract() throws SCDBusinessException {
        FacesContext context = FacesContext.getCurrentInstance();

        Object req_id = FacesUtil.getSession().getAttribute("req_id");
        Long creditRequestId = Long.parseLong(req_id.toString());

        CreditRequest creditRequest = creditRequestBusinessBean.getCreditRequestById(creditRequestId);

        UserContext userContext = FacesUtil.getUserContext();
        File contract = contractGeneratorService.generateReport(creditRequest, userContext);
        fileDownloadService.downloadFile(contract, userContext, true);
    }

}
