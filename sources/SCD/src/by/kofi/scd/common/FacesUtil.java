package by.kofi.scd.common;

import by.kofi.scd.dto.UserContext;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 * FacesCOntext Util methods
 *
 * @author harchevnikov_m
 *         Date: 29/10/11
 *         Time: 13:44
 */
public class FacesUtil {

    /**
     *
     * @return Current http session
     */
    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    }

    /**
     *
     * @return UserContext from session
     */
    public static UserContext getUserContext() {
        return (UserContext) getSession().getAttribute("userContext");
    }
}
