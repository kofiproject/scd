package by.kofi.scd.controller.login;

import by.kofi.scd.business.mail.MailBusinessBean;
import by.kofi.scd.business.UserBusinessBean;
import by.kofi.scd.common.constants.NavigationActionEnum;
import by.kofi.scd.entity.Client;
import by.kofi.scd.entity.Role;
import by.kofi.scd.entity.SCDUser;
import by.kofi.scd.exceptions.SCDBusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.faces.context.FacesContext;

/**
 * @author harchevnikov_m
 *         Date: 26.09.11
 *         Time: 22:34
 */
@Controller("loginBean")
@Scope("request")
public class LoginControllerBean {
    private Long uniqueId = null;
    private String password;
    private Boolean isLoginFailed;

    public Boolean getLoginFailed() {
        return isLoginFailed;
    }

    public void setLoginFailed(Boolean loginFailed) {
        isLoginFailed = loginFailed;
    }

    public Long getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(Long uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Autowired
    private UserBusinessBean userBusinessBean;

    public String loginAction() throws SCDBusinessException {
        Object session = FacesContext.getCurrentInstance().getExternalContext().getSession(false);

        SCDUser user = this.userBusinessBean.getUserByIdentityId(getUniqueId());
        if (user != null && user.getPassword().equals(getPassword())) {
            Role role = user.getRole();
            //todo switch for role and save in session role and client
            return NavigationActionEnum.LOGIN.getValue();
        } else {
            //to avoid 0 in input if uniqueId is null (jsf initialize it with 0)
            if (getUniqueId().equals(0L)) {
                setUniqueId(null);
            }
            setLoginFailed(true);
            return NavigationActionEnum.LOGIN_FAIL.getValue();
        }
    }
}
