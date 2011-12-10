package by.kofi.scd.controller.admin;

import by.kofi.scd.business.client.ClientBusinessBean;
import by.kofi.scd.common.FacesUtil;
import by.kofi.scd.common.constants.NavigationActionEnum;
import by.kofi.scd.controller.login.RegistrationController;
import by.kofi.scd.entity.Client;
import by.kofi.scd.entity.SCDUser;
import by.kofi.scd.exceptions.SCDBusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 * @author harchevnikov_m
 *         Date: 26.09.11
 *         Time: 22:34
 */
@Controller
@Scope("request")
public class UserManagementController {
    private Long clientId;
    private Client client;

    @Autowired
    private ClientBusinessBean clientBusinessBean;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void loadClientDetails() throws SCDBusinessException {
        try {
            Client client = clientBusinessBean.getClientById(getClientId());
            setClient(client);
        } catch (SCDBusinessException e) {
            throw new SCDBusinessException(e.getMessage(), e);
        }
    }

    public void blockClient() throws SCDBusinessException {
        try {
            long clientId = getClientIdFromRequest();

            clientBusinessBean.blockClient(clientId, true);
        } catch (SCDBusinessException e) {
            throw new SCDBusinessException(e.getMessage(), e);
        }
    }

    public void unblockClient() throws SCDBusinessException {
        try {
            long clientId = getClientIdFromRequest();

            clientBusinessBean.blockClient(clientId, false);
        } catch (SCDBusinessException e) {
            throw new SCDBusinessException(e.getMessage(), e);
        }
    }


    public void deleteClient() throws SCDBusinessException {
        try {
            long clientId = getClientIdFromRequest();

            clientBusinessBean.deleteClient(clientId);
        } catch (SCDBusinessException e) {
            throw new SCDBusinessException(e.getMessage(), e);
        }
    }

    private long getClientIdFromRequest() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest myRequest = (HttpServletRequest) context.getExternalContext().getRequest();
        return Long.parseLong(myRequest.getParameter("clientId"));
    }

}
