package by.kofi.scd.business.client;

import by.kofi.scd.business.grid.AbstractGridBusinessBean;
import by.kofi.scd.business.grid.GridHeader;
import by.kofi.scd.business.grid.ResultRowField;
import by.kofi.scd.dataservice.client.ClientDataService;
import by.kofi.scd.dto.UserContext;
import by.kofi.scd.dto.client.ActiveCreditsResultRow;
import by.kofi.scd.entity.CreditItem;
import by.kofi.scd.entity.CreditItemStateEnum;
import by.kofi.scd.exceptions.SCDBusinessException;
import by.kofi.scd.exceptions.SCDTechnicalException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author harchevnikov_m
 *         Date: 15/10/11
 *         Time: 18:55
 */
@Service
public class ClosedCreditsGridBusinessBean extends AbstractGridBusinessBean {

    private static final Logger LOGGER = Logger.getLogger(ClosedCreditsGridBusinessBean.class);

    @Autowired
    private ClientDataService clientDataService;
    @Autowired
    private ActiveCreditsGridBusinessBean activeCreditsGridBusinessBean;


    @Transactional(propagation = Propagation.REQUIRED)
    public List<ActiveCreditsResultRow> executeSearch() throws SCDBusinessException {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        UserContext userContext = (UserContext) session.getAttribute("userContext");
        Long clientId = userContext.getClient().getClientId();
        try {
            List<CreditItem> creditItems = clientDataService.getCreditItems(clientId, CreditItemStateEnum.CLOSED);

            List<ActiveCreditsResultRow> result = new ArrayList<ActiveCreditsResultRow>(creditItems.size());
            for (CreditItem creditItem : creditItems) {
                result.add(new ActiveCreditsResultRow(creditItem));
            }

            return result;

        } catch (SCDTechnicalException e) {
            LOGGER.error(e.getMessage());
            throw new SCDBusinessException("getClosedCreditItems", e);
        }
    }

    @Override
    public GridHeader[] getHeaders() {
        return this.activeCreditsGridBusinessBean.getHeaders();
    }

    @Override
    public ResultRowField[] getFields() {
        return this.activeCreditsGridBusinessBean.getFields();
    }
}