package by.kofi.scd.business.mail;

import by.kofi.scd.business.AbstractBusinessBean;
import by.kofi.scd.entity.Client;
import by.kofi.scd.exceptions.SCDBusinessException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * @author harchevnikov_m
 *         Date: 16/10/11
 *         Time: 14:39
 */
@Service
public class MailBusinessBean extends AbstractBusinessBean {
    @Autowired(required = true)
    @Qualifier("mailSender")
    private MailSender mailSender;

    private ResourceBundle mailBundle = ResourceBundle.getBundle("mail");
    private static final Logger LOGGER = Logger.getLogger(MailBusinessBean.class);

    /**
     * Send notification email after successful registration
     *
     * @param client Client
     * @throws SCDBusinessException email send error
     */
    public void sendRegistrationNotificationMail(Client client) throws SCDBusinessException {
        String fromEmail = this.mailBundle.getString("mail.username");
        String toEmail = client.getEmail();
        String subject = this.mailBundle.getString("mail.registration.subject");
        String body = MessageFormat.format(
                this.mailBundle.getString("mail.registration.body"),
                client.getUser().getUserId(),
                client.getUser().getPassword());


        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(fromEmail);
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);

        try {
            mailSender.send(message);
        } catch (MailException e) {
            LOGGER.error(e.getMessage());
            //todo uncomment !!
//            throw new SCDBusinessException("Registration email error", e);
        }
    }
}
