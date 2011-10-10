package by.kofi.scd.validator.registration;

import by.kofi.scd.common.i18n.I18nSupport;
import org.springframework.stereotype.Service;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validators for registration form
 *
 * @author harchevnikov_m
 *         Date: 10/10/11
 *         Time: 22:18
 */
@Service("registrationValidator")
public class RegistrationValidator {
    /**
     * Min length for validateShortString
     */
    private static final Integer SHORT_STRING_MIN_LENGTH = 3;
    /**
     * Min length for validateString
     */
    private static final Integer STRING_MIN_LENGTH = 6;

    /**
     * Phone min length
     */
    private static final Integer PHONE_MIN_LENGTH = 6;
    /**
     * Password min length
     */
    private static final Integer PASSWORD_MIN_LENGTH = 3;
    /**
     * Cache income min length
     */
    private static final Integer CACHE_INCOME_MIN_LENGTH = 5;
    /**
     * Email pattern
     */
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[\\\\w\\\\-]([\\\\.\\\\w])+[\\\\w]+@([\\\\w\\\\-]+\\\\.)+[A-Z]{2,4}$");

    /**
     * JSF input id
     */
    private static final String PASSWORD_UI_ID = "password";

    /**
     * @param facesContext Faces context
     * @param uiComponent  UI component
     * @param o            object to validate
     */
    public void validateShortString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        validateStringLength(facesContext, uiComponent, o,
                I18nSupport.getText("registration.validator.shortStringMsg", SHORT_STRING_MIN_LENGTH),
                SHORT_STRING_MIN_LENGTH);
    }

    /**
     * @param facesContext Faces context
     * @param uiComponent  UI component
     * @param o            object to validate
     */
    public void validateString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        validateStringLength(facesContext, uiComponent, o,
                I18nSupport.getText("registration.validator.stringMsg", STRING_MIN_LENGTH),
                STRING_MIN_LENGTH);
    }

    /**
     * @param facesContext Faces context
     * @param uiComponent  UI component
     * @param o            object to validate
     */
    public void validatePhone(FacesContext facesContext, UIComponent uiComponent, Object o) {
        validateStringLength(facesContext, uiComponent, o,
                I18nSupport.getText("registration.validator.stringMsg", PHONE_MIN_LENGTH),
                PHONE_MIN_LENGTH);
    }

    /**
     * @param facesContext Faces context
     * @param uiComponent  UI component
     * @param o            object to validate
     */
    public void validatePassword(FacesContext facesContext, UIComponent uiComponent, Object o) {
        validateStringLength(facesContext, uiComponent, o,
                I18nSupport.getText("registration.validator.stringMsg", PASSWORD_MIN_LENGTH),
                PASSWORD_MIN_LENGTH);
    }

    /**
     * @param facesContext Faces context
     * @param uiComponent  UI component
     * @param o            object to validate
     */
    public void validateConfirmPassword(FacesContext facesContext, UIComponent uiComponent, Object o) {
        UIInput passwordComponent = (UIInput) facesContext.getViewRoot().findComponent(PASSWORD_UI_ID);
        Object passwordVal = passwordComponent.getValue();
        String password = passwordVal != null ? passwordVal.toString() : "";

        String confirmPassword = o != null ? o.toString() : "";

        if (!password.equals(confirmPassword)) {
            ((UIInput) uiComponent).setValid(false);
            FacesMessage message = new FacesMessage(I18nSupport.getText("registration.validator.confirmPassword"));
            facesContext.addMessage(uiComponent.getClientId(facesContext), message);
        }
    }

    /**
     * @param facesContext Faces context
     * @param uiComponent  UI component
     * @param o            object to validate
     */
    public void validateEmail(FacesContext facesContext, UIComponent uiComponent, Object o) {
        String value = o != null ? o.toString() : "";
        Matcher matcher = EMAIL_PATTERN.matcher(value);

        if (!matcher.matches()) {
            ((UIInput) uiComponent).setValid(false);
            FacesMessage message = new FacesMessage(I18nSupport.getText("registration.validator.emailMsg"));
            facesContext.addMessage(uiComponent.getClientId(facesContext), message);
        }
    }

    /**
     * @param facesContext Faces context
     * @param uiComponent  UI component
     * @param o            object to validate
     */
    public void validateCacheIncome(FacesContext facesContext, UIComponent uiComponent, Object o) {
        validateStringLength(facesContext, uiComponent, o,
                I18nSupport.getText("registration.validator.stringMsg", CACHE_INCOME_MIN_LENGTH),
                CACHE_INCOME_MIN_LENGTH);
    }

    private void validateStringLength(FacesContext facesContext, UIComponent uiComponent, Object o,
                                      String messageStr, Integer minLength) {
        String value = o != null ? o.toString() : "";

        if (value.length() < minLength) {
            ((UIInput) uiComponent).setValid(false);
            FacesMessage message = new FacesMessage(messageStr);
            facesContext.addMessage(uiComponent.getClientId(facesContext), message);
        }
    }
}