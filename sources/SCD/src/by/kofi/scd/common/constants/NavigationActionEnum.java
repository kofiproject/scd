package by.kofi.scd.common.constants;

import by.kofi.scd.controller.login.LoginControllerBean;
import org.springframework.stereotype.Service;

/**
 * Navigation actions names
 *
 * @author harchevnikov_m
 *         Date: 08/10/11
 *         Time: 18:30
 */
public enum NavigationActionEnum {
    LOGIN("login"),
    LOGIN_FAIL("fail"),

    REGISTRATION_SUCCESS("success");

    private String action;

    /**
     * @param action action
     */
    private NavigationActionEnum(String action) {
        this.action = action;
    }

    public String getValue() {
        return this.action;
    }
}
