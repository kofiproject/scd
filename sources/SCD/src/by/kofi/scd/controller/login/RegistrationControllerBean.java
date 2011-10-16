package by.kofi.scd.controller.login;

import by.kofi.scd.business.ClientBusinessBean;
import by.kofi.scd.business.mail.MailBusinessBean;
import by.kofi.scd.common.constants.NavigationActionEnum;
import by.kofi.scd.entity.GenderEnum;
import by.kofi.scd.entity.Client;
import by.kofi.scd.exceptions.SCDBusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;

/**
 * @author harchevnikov_m
 *         Date: 26.09.11
 *         Time: 22:34
 */
@Controller("registrationBean")
@Scope("request")
public class RegistrationControllerBean {
    @Autowired
    private ClientBusinessBean clientBusinessBean;

    @Autowired
    private MailBusinessBean mailBusinessBean;

    private String password;
    private String confirmPassword;
    private Client client;

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        try {
            //store client
            Client registeredClient = clientBusinessBean.registerClient(getClient(), getPassword());
            //send email
            this.mailBusinessBean.sendRegistrationNotificationMail(registeredClient);

            return NavigationActionEnum.REGISTRATION_SUCCESS.getValue();
        } catch (SCDBusinessException e) {
            throw new SCDBusinessException("registrationAction", e);
        }
    }
}
