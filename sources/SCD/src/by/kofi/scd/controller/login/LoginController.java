package by.kofi.scd.controller.login;

import by.kofi.scd.business.employee.EmployeeBusinessBean;
import by.kofi.scd.business.mail.MailBusinessBean;
import by.kofi.scd.business.UserBusinessBean;
import by.kofi.scd.common.FacesUtil;
import by.kofi.scd.common.constants.NavigationActionEnum;
import by.kofi.scd.dto.UserContext;
import by.kofi.scd.entity.Client;
import by.kofi.scd.entity.Employee;
import by.kofi.scd.entity.Role;
import by.kofi.scd.entity.SCDUser;
import by.kofi.scd.exceptions.SCDBusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 * @author harchevnikov_m
 *         Date: 26.09.11
 *         Time: 22:34
 */
@Controller("loginBean")
@Scope("request")
public class LoginController {
    private static final Long CLIENT_ROLE_ID = 1L;
    private static final Long EXPERT_ROLE_ID = 2L;
    private static final Long OPERATOR_ROLE_ID = 3L;

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
    @Autowired
    private EmployeeBusinessBean employeeBusinessBean;

    public String loginAction() throws SCDBusinessException {
        SCDUser user = this.userBusinessBean.getUserByIdentityId(getUniqueId());
        if (user != null && user.getPassword().equals(getPassword())) {
            Client client = user.getClient();
            Employee employee = user.getEmployee();
            UserContext userContext = new UserContext(client, employee);
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            session.setAttribute("userContext", userContext);

            Role role = user.getRole();
            long roleId = role.getRoleId();

            if (roleId == CLIENT_ROLE_ID) {
                return NavigationActionEnum.LOGIN_CLIENT.getValue();
            } else if (roleId == EXPERT_ROLE_ID) {
                return NavigationActionEnum.LOGIN_EXPERT.getValue();
            } else if (roleId == OPERATOR_ROLE_ID) {
                return NavigationActionEnum.LOGIN_OPERATOR.getValue();
            } else {
                return "";
            }
        } else {
            //to avoid 0 in input if uniqueId is null (jsf initialize it with 0)
            if (getUniqueId().equals(0L)) {
                setUniqueId(null);
            }
            setLoginFailed(true);
            return NavigationActionEnum.LOGIN_FAIL.getValue();
        }
    }

    public String logoutAction() throws SCDBusinessException {
        UserContext userContext = FacesUtil.getUserContext();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.invalidate();
        //clear all locks
        Employee employee = userContext.getEmployee();
        if (employee != null) {
            employeeBusinessBean.unlockAllCreditRequestsByEmployee(employee.getEmployeeId());
        }

        return NavigationActionEnum.LOGOUT.getValue();
    }
}
