package by.kofi.scd.validator.registration;

import by.kofi.scd.business.client.ClientBusinessBean;
import by.kofi.scd.common.FacesUtil;
import by.kofi.scd.common.i18n.I18nSupport;
import by.kofi.scd.dto.UserContext;
import by.kofi.scd.entity.Client;
import by.kofi.scd.exceptions.SCDBusinessException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private ClientBusinessBean clientBusinessBean;

    private static final Logger LOGGER = Logger.getLogger(RegistrationValidator.class);
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
     * Passport number length
     */
    private static final Integer PASSPORT_NO_LENGTH = 7;
    /**
     * Email pattern
     */
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

    /**
     * JSF input id
     */
    private static final String PASSWORD_UI_ID = "registration-form:reg-form:password";
    /**
     * passport series ID
     */
    private static final String PASSPORT_SERIES_UI_ID = "registration-form:reg-form:passportSeries";


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
        Matcher matcher = EMAIL_PATTERN.matcher(value.trim());

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


    /**
     * @param facesContext Faces context
     * @param uiComponent  UI component
     * @param o            object to validate
     */
    public void validatePassportData(FacesContext facesContext, UIComponent uiComponent, Object o) {
        //first check passport length
        Long passportNo = o != null ? Long.parseLong(o.toString()) : null;
        if (passportNo.toString().length() != PASSPORT_NO_LENGTH) {
            ((UIInput) uiComponent).setValid(false);
            FacesMessage message = new FacesMessage(I18nSupport.getText("registration.validator.stringMsg", PASSPORT_NO_LENGTH));
            facesContext.addMessage(uiComponent.getClientId(facesContext), message);

            return;
        }

        UIInput passportSeriesComponent = (UIInput) facesContext.getViewRoot()
                .findComponent(PASSPORT_SERIES_UI_ID);
        Object passportSeriesVal = passportSeriesComponent.getValue();
        String passportSeries = passportSeriesVal != null ? passportSeriesVal.toString() : "";

        //validate passport series
        if (passportSeries.isEmpty()) {
            ((UIInput) uiComponent).setValid(false);
            FacesMessage message = new FacesMessage(I18nSupport.getText("registration.validator.required"));
            facesContext.addMessage(uiComponent.getClientId(facesContext), message);

            return;
        }

        //check for existing client with the same passport data
        Client client = null;
        try {
            client = this.clientBusinessBean.getClientByPassportData(passportSeries, passportNo);
        } catch (SCDBusinessException e) {
            LOGGER.error(e.getMessage());
        }

        if (client != null) {
            UserContext userContext = FacesUtil.getUserContext();
            if ((userContext != null) && (client.getClientId().equals(userContext.getClient().getClientId()))) {
                return;
            }

            ((UIInput) uiComponent).setValid(false);
            FacesMessage message = new FacesMessage(I18nSupport.getText("registration.validator.existingPassport"));
            facesContext.addMessage(uiComponent.getClientId(facesContext), message);
        }
    }


    private void validateStringLength(FacesContext facesContext, UIComponent uiComponent, Object o,
                                      String messageStr, Integer minLength) {
        String value = o != null ? o.toString().trim() : "";

        if (value.length() < minLength) {
            ((UIInput) uiComponent).setValid(false);
            FacesMessage message = new FacesMessage(messageStr);
            facesContext.addMessage(uiComponent.getClientId(facesContext), message);
        }
    }
}