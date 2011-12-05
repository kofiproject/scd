package by.kofi.scd.controller.contactUs;

import by.kofi.scd.business.mail.MailBusinessBean;
import by.kofi.scd.common.FacesUtil;
import by.kofi.scd.dto.UserContext;
import by.kofi.scd.exceptions.SCDBusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by IntelliJ IDEA.
 * User: Admin
 * Date: 04.12.11
 * Time: 16:31
 * To change this template use File | Settings | File Templates.
 */
@Service
public class ContactUsController {
    private String email;
    private String message;
    private String name;

    @Autowired
    private MailBusinessBean mailBusinessBean;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public String getMessage() {
        return message;
    }

    public void init(){
        UserContext userContext = FacesUtil.getUserContext();
        message = "";
        if (userContext == null){
            name = "";
            email = "";
        } else {
            name = userContext.toString();
            email = userContext.getEmail();
        }
    }

    public String contuctUsSendAction() throws SCDBusinessException {
        mailBusinessBean.sendMessageFromContactUs(message, name, email);
        return "";
    }
}
