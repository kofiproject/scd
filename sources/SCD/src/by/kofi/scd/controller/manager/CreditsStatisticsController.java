package by.kofi.scd.controller.manager;

import by.kofi.scd.business.credit.CreditItemBusinessBean;
import by.kofi.scd.business.credit.CreditRequestBusinessBean;
import by.kofi.scd.dto.manager.CreditStatistics;
import by.kofi.scd.entity.CreditRequest;
import by.kofi.scd.exceptions.SCDBusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author harchevnikov_m
 *         Date: 26.09.11
 *         Time: 22:34
 */
@Controller
@Scope("request")
public class CreditsStatisticsController {
    private Date startDate = new Date();
    private Date endDate = new Date();
    private CreditStatistics statistics;

    @Autowired
    @Qualifier("ciBB")
    private CreditItemBusinessBean creditItemBusinessBean;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public CreditStatistics getStatistics() {
        return statistics;
    }

    public void setStatistics(CreditStatistics statistics) {
        this.statistics = statistics;
    }

    public void updateStatistics() {
        int newCreditsCount = creditItemBusinessBean.getNewCreditsCount(getStartDate(), getEndDate());
        int closedCount = creditItemBusinessBean.getClosedCount(getStartDate(), getEndDate());
        int withPenaltyCount = creditItemBusinessBean.getWithPenaltyCount(getStartDate(), getEndDate());

        this.statistics = new CreditStatistics(newCreditsCount, closedCount, withPenaltyCount);
    }
}
