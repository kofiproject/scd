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
public class CreditLinkBean implements Serializable {
    private Long creditId;
    private Credit credit;

    @Autowired
    private CreditBusinessBean creditBusinessBean;

    public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }

    public Long getCreditId() {
        return creditId;
    }

    public void setCreditId(Long creditId) {
        this.creditId = creditId;
    }

    public String creditDetailsAction() throws SCDBusinessException {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest myRequest = (HttpServletRequest) context.getExternalContext().getRequest();
        Long creditId = Long.parseLong(myRequest.getParameter("creditId"));
        setCreditId(creditId);

        setCredit(creditBusinessBean.getCreditById(creditId));
        return NavigationActionEnum.CLIENT_CREDIT_DETAILS.getValue();
    }

}
