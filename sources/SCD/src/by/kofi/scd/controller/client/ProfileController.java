package by.kofi.scd.controller.client;

import by.kofi.scd.business.client.ClientBusinessBean;
import by.kofi.scd.business.mail.MailBusinessBean;
import by.kofi.scd.common.FacesUtil;
import by.kofi.scd.common.constants.NavigationActionEnum;
import by.kofi.scd.controller.login.RegistrationController;
import by.kofi.scd.entity.Client;
import by.kofi.scd.entity.GenderEnum;
import by.kofi.scd.entity.SCDUser;
import by.kofi.scd.exceptions.SCDBusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

/**
 * @author harchevnikov_m
 *         Date: 26.09.11
 *         Time: 22:34
 */
@Controller
@Scope("request")
public class ProfileController extends RegistrationController {

    @PostConstruct
    @Override
    public void init() {
        setClient(FacesUtil.getUserContext().getClient());

    }

    /**
     * Update client profile
     *
     * @return client home page
     * @throws SCDBusinessException
     */
    public String updateProfileAction() throws SCDBusinessException {
        try {
            Client client = getClient();
            SCDUser user = client.getUser();
            user.setPassword(getPassword());

            getClientBusinessBean().updateProfile(client);
            return NavigationActionEnum.CLIENT_UPDATE_PROFILE.getValue();
        } catch (SCDBusinessException e) {
            throw new SCDBusinessException("registrationAction", e);
        }
    }

}
