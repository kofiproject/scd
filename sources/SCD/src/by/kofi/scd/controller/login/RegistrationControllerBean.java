package by.kofi.scd.controller.login;

import by.kofi.scd.common.constants.NavigationActionEnum;
import by.kofi.scd.dataservice.CRUDDataService;
import by.kofi.scd.dto.registration.GenderEnum;
import by.kofi.scd.entity.generated.Client;
import by.kofi.scd.exceptions.SCDBusinessException;
import by.kofi.scd.exceptions.SCDTechnicalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.InitBinder;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

/**
 * @author harchevnikov_m
 *         Date: 26.09.11
 *         Time: 22:34
 */
@Controller("registrationBean")
@Scope("request")
public class RegistrationControllerBean {
    @Autowired(required = true)
    @Qualifier("hibernateCRUDService")
    private CRUDDataService hibernateCRUDDataService;

    private String confirmPassword;
    private Client client;

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * @return genders list
     */
    public GenderEnum[] getGenders() {
        return GenderEnum.values();
    }

    @PostConstruct
    public void init() {
        this.client = new Client();
        this.client.setSex(GenderEnum.MALE);
    }

    /**
     * perform client registration
     *
     * @return registration action
     * @throws SCDBusinessException technical exception
     */
    public String registrationAction() throws SCDBusinessException {
        Object session = FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        Client newClient = getClient();
//        try {
//            newClient = hibernateCRUDDataService.merge(newClient);
        return NavigationActionEnum.REGISTRATION_SUCCESS.getValue();
//        } catch (SCDTechnicalException e) {
//            throw new SCDBusinessException("registrationAction", e);
//        }

    }
}
