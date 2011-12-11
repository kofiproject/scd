package by.kofi.scd.common.constants;

/**
 * Navigation actions names
 *
 * @author harchevnikov_m
 *         Date: 08/10/11
 *         Time: 18:30
 */
public enum NavigationActionEnum {
    LOGIN_CLIENT("login-client"),
    LOGIN_EXPERT("login-expert"),
    LOGIN_OPERATOR("login-operator"),
    LOGIN_ADMIN("login-admin"),
    LOGIN_MANAGER("login-manager"),

    LOGOUT("logout"),
    LOGIN_FAIL("fail"),

    REGISTRATION_SUCCESS("success"),

    CLIENT_UPDATE_PROFILE("client-update-profile"),

    CLIENT_CREDIT_DETAILS("client-credit-details"),
    CLIENT_CREDIT_REQUEST_SEND_COMPLETE("client-send-request-complete"),

    EXPERT_CREDIT_REQUEST_LIST("expert-requests");

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
