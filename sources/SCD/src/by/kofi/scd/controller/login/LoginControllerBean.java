package by.kofi.scd.controller.login;

import by.kofi.scd.business.DepartmentBusinessBean;
import by.kofi.scd.common.constants.NavigationActionEnum;
import by.kofi.scd.exceptions.SCDBusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.Transient;
import java.math.BigInteger;
import java.util.ArrayList;

/**
 * @author harchevnikov_m
 *         Date: 26.09.11
 *         Time: 22:34
 */
@Controller("loginBean")
@Scope("request")
public class LoginControllerBean {

    private BigInteger uniqueId;
    private String password;
    private Boolean isLoginFailed;

    public Boolean getLoginFailed() {
        return isLoginFailed;
    }

    public void setLoginFailed(Boolean loginFailed) {
        isLoginFailed = loginFailed;
    }

    public BigInteger getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(BigInteger uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    @Autowired
//    private DepartmentBusinessBean departmentBusinessBean;

    //    @Transient
    public String loginAction() {
        if (getPassword().length() > 10) {
            return NavigationActionEnum.LOGIN.getValue();
        } else {
            setLoginFailed(true);
            return NavigationActionEnum.LOGIN_FAIL.getValue();
        }
//        throw new SCDBusinessException("Error");//uncomment to test error page
//        return departmentBusinessBean.processItems(new ArrayList<Long>(0));
    }

}
