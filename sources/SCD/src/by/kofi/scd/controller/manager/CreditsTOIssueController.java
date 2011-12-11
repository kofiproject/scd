package by.kofi.scd.controller.manager;

import by.kofi.scd.business.client.ClientBusinessBean;
import by.kofi.scd.business.credit.CreditItemBusinessBean;
import by.kofi.scd.business.credit.CreditRequestBusinessBean;
import by.kofi.scd.business.mail.MailBusinessBean;
import by.kofi.scd.entity.Client;
import by.kofi.scd.entity.CreditRequest;
import by.kofi.scd.exceptions.SCDBusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 * @author harchevnikov_m
 *         Date: 26.09.11
 *         Time: 22:34
 */
@Controller
@Scope("request")
public class CreditsToIssueController {
    private Long creditRequestId;

    @Autowired
    @Qualifier("ciBB")
    private CreditItemBusinessBean creditItemBusinessBean;
    @Autowired
    private CreditRequestBusinessBean creditRequestBusinessBean;

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
        } catch (SCDBusinessException e) {
            throw new SCDBusinessException(e.getMessage(), e);
        }
    }
}
