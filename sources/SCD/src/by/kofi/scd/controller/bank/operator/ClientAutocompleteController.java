package by.kofi.scd.controller.bank.operator;

import by.kofi.scd.business.AbstractBusinessBean;
import by.kofi.scd.business.client.ClientBusinessBean;
import by.kofi.scd.common.i18n.I18nSupport;
import by.kofi.scd.entity.Client;
import by.kofi.scd.exceptions.SCDBusinessException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

/**
 * @author harchevnikov_m
 *         Date: 23/11/11
 *         Time: 22:00
 */
@Controller
public class ClientAutocompleteController {

    private static final Logger LOGGER = Logger.getLogger(ClientAutocompleteController.class);
    private List<Client> clients;
    private List<Client> returnList;

    @Autowired
    private ClientBusinessBean clientBusinessBean;

    @PostConstruct
    public void init() throws SCDBusinessException {
        clients = clientBusinessBean.getClients();
        returnList = new ArrayList<Client>();
    }

    public List<Client> suggestClient(String clientIdentityIn) {
        returnList = new ArrayList<Client>();
        this.returnList.clear();
        for (Client client : getClients()) {
            String clientIdentity = client.getUser().getUserId().toString();
            if (clientIdentity.startsWith(clientIdentityIn)) {
                returnList.add(client);
            }
        }

        return returnList;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<Client> getReturnList() {
        return returnList;
    }

    public void setReturnList(List<Client> returnList) {
        this.returnList = returnList;
    }

    public void validateUserIdentity(FacesContext facesContext, UIComponent uiComponent, Object o) {
        String value = o != null ? o.toString() : "";

        boolean isCorrectValue = false;

        for (Client client : getClients()) {
            String clientIdentity = client.getUser().getUserId().toString();
            if (clientIdentity.equals(value)) {
                isCorrectValue = true;
                break;
            }
        }

        if (!isCorrectValue) {
            ((UIInput) uiComponent).setValid(false);
            FacesMessage message = new FacesMessage("Invalid user identity");
            facesContext.addMessage(uiComponent.getClientId(facesContext), message);
        }
    }

}
