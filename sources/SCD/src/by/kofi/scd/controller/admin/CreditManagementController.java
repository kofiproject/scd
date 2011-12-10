package by.kofi.scd.controller.admin;

import by.kofi.scd.business.client.ClientBusinessBean;
import by.kofi.scd.business.credit.CreditBusinessBean;
import by.kofi.scd.controller.login.RegistrationController;
import by.kofi.scd.entity.Client;
import by.kofi.scd.entity.Credit;
import by.kofi.scd.exceptions.SCDBusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * @author harchevnikov_m
 *         Date: 26.09.11
 *         Time: 22:34
 */
@Controller
@Scope("request")
public class CreditManagementController {
    private Long creditId;
//    private Credit credit;

    private String name;
    private String description;
    private BigDecimal percent;
    private BigDecimal penaltyPercent;
    private Long maxTerm;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPercent() {
        return percent;
    }

    public void setPercent(BigDecimal percent) {
        this.percent = percent;
    }

    public BigDecimal getPenaltyPercent() {
        return penaltyPercent;
    }

    public void setPenaltyPercent(BigDecimal penaltyPercent) {
        this.penaltyPercent = penaltyPercent;
    }

    public Long getMaxTerm() {
        return maxTerm;
    }

    public void setMaxTerm(Long maxTerm) {
        this.maxTerm = maxTerm;
    }

    @Autowired
    private CreditBusinessBean creditBusinessBean;

    public Long getCreditId() {
        return creditId;
    }

    public void setCreditId(Long creditId) {
        this.creditId = creditId;
    }

    public void loadCreditDetails() throws SCDBusinessException {
        try {
            Long id = getCreditId();
            if (id != null && id > 0) {
                Credit credit = creditBusinessBean.getCreditById(id);

                setName(credit.getName());
                setDescription(credit.getDescription());
                setPercent(credit.getPercent());
                setPercent(credit.getPercent());
                setPenaltyPercent(credit.getPenaltyPercent().multiply(new BigDecimal(100)));
                setMaxTerm(credit.getMaxTerm());

            } else {
                setName("");
                setDescription("");
                setPercent(null);
                setPercent(null);
                setPenaltyPercent(null);
                setMaxTerm(null);
            }
        } catch (SCDBusinessException e) {
            throw new SCDBusinessException(e.getMessage(), e);
        }
    }

    public void updateCredit() throws SCDBusinessException {
        try {
            Credit credit = new Credit();
            credit.setCreditId(this.creditId);
            credit.setName(this.name);
            credit.setDescription(this.description);
            credit.setPercent(this.percent);
            credit.setPenaltyPercent(this.penaltyPercent.divide(new BigDecimal(100)));
            credit.setMaxTerm(this.maxTerm);
            credit.setMaxSumPercent(BigDecimal.ZERO);

            creditBusinessBean.mergeCredit(credit);
        } catch (SCDBusinessException e) {
            throw new SCDBusinessException(e.getMessage(), e);
        }
    }

    private Credit getCreditDetailsFromRequest() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        Credit credit = new Credit();
        credit.setCreditId(new Long(request.getParameter("creditId")));
        credit.setDescription(request.getParameter("description"));
        credit.setName(request.getParameter("name"));
        BigDecimal percent = new BigDecimal(request.getParameter("percent"));
        credit.setPercent(percent.divide(new BigDecimal(100)));
        BigDecimal penaltyPercent = new BigDecimal(request.getParameter("penaltyPercent"));
        credit.setPenaltyPercent(penaltyPercent);

        credit.setMaxSumPercent(BigDecimal.ZERO);

        return credit;
    }
}
