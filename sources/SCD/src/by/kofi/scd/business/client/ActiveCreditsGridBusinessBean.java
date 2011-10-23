package by.kofi.scd.business.client;

import by.kofi.scd.business.AbstractBusinessBean;
import by.kofi.scd.business.AbstractGridBusinessBean;
import by.kofi.scd.business.RoleBusinessBean;
import by.kofi.scd.dataservice.CRUDDataService;
import by.kofi.scd.dataservice.client.ClientDataService;
import by.kofi.scd.dto.UserContext;
import by.kofi.scd.entity.*;
import by.kofi.scd.exceptions.SCDBusinessException;
import by.kofi.scd.exceptions.SCDTechnicalException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
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
public class ActiveCreditsGridBusinessBean extends AbstractGridBusinessBean {

    private static final Logger LOGGER = Logger.getLogger(ActiveCreditsGridBusinessBean.class);

    @Autowired
    private ClientDataService clientDataService;

    private List<CreditItem> activeCreditItems = new ArrayList<CreditItem>(0);


    @Transactional(propagation = Propagation.REQUIRED)
    public void executeSearch() throws SCDBusinessException {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        UserContext userContext = (UserContext) session.getAttribute("userContext");
        Long clientId = userContext.getClient().getClientId();
        try {
            List<CreditItem> creditItems = clientDataService.getCreditItems(clientId, CreditItemStateEnum.ACTIVE);
            setActiveCreditItems(creditItems);
        } catch (SCDTechnicalException e) {
            throw new SCDBusinessException("getActiveCreditItems", e);
        }

    }

    public List<CreditItem> getActiveCreditItems() {
        try {
            executeSearch();
        } catch (SCDBusinessException e) {
            e.printStackTrace();
        }
        return activeCreditItems;
    }

    public void setActiveCreditItems(List<CreditItem> activeCreditItems) {
        this.activeCreditItems = activeCreditItems;
    }
}
