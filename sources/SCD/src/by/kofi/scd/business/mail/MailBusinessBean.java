package by.kofi.scd.business.mail;

import by.kofi.scd.business.AbstractBusinessBean;
import by.kofi.scd.entity.Client;
import by.kofi.scd.entity.CreditItem;
import by.kofi.scd.entity.CreditRequest;
import by.kofi.scd.entity.Employee;
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
        String toEmail = client.getEmail();
        String subject = this.mailBundle.getString("mail.registration.subject");
        String body = MessageFormat.format(
                this.mailBundle.getString("mail.registration.body"),
                client.getUser().getUserId(),
                client.getUser().getPassword());


        sendEmail(toEmail, subject, body);
    }

    /**
     * Send email notification on  credit request complete
     *
     * @param creditRequest creditRequest instance
     * @param client        Client
     * @throws SCDBusinessException email send error
     */
    public void sendCreditRequestNotificationMail(CreditRequest creditRequest, Client client) throws SCDBusinessException {
        String toEmail = client.getEmail();
        //mail.creditRequest.body = Credit request:\n credit: {0}\n sum: {1} BY\n term: {2} months\n issuance date: {3}
        String subject = this.mailBundle.getString("mail.creditRequest.subject");
        String body = MessageFormat.format(
                this.mailBundle.getString("mail.creditRequest.body"),
                creditRequest.getCredit().getName(),
                creditRequest.getAmount(),
                creditRequest.getTerm(),
                creditRequest.getIssuanceDate());

        sendEmail(toEmail, subject, body);
    }

    /**
     * notification on creditRequest confirmation
     *
     * @param creditRequest credit request
     * @throws SCDBusinessException mail send error
     */
    public void sendCreditRequestConfirmMail(CreditRequest creditRequest) throws SCDBusinessException {
        String toEmail = creditRequest.getClient().getEmail();
        String subject = this.mailBundle.getString("mail.creditRequest.confirm.subject");

        Employee employee = creditRequest.getEmployee();
        String employeeName = employee.getName() + " " + employee.getSurname();
        String body = MessageFormat.format(
                this.mailBundle.getString("mail.creditRequest.confirm.body"),
                creditRequest.getCredit().getName(),
                creditRequest.getAmount(),
                creditRequest.getTerm(),
                creditRequest.getIssuanceDate(),
                creditRequest.getProcessingDate(),
                employeeName);

        sendEmail(toEmail, subject, body);
    }

    /**
     * notification on creditRequest rejection
     *
     * @param creditRequest credit request
     * @throws SCDBusinessException mail send error
     */
    public void sendCreditRequestRejectMail(CreditRequest creditRequest) throws SCDBusinessException {
        String toEmail = creditRequest.getClient().getEmail();
        String subject = this.mailBundle.getString("mail.creditRequest.confirm.subject");

        Employee employee = creditRequest.getEmployee();
        String employeeName = employee.getName() + " " + employee.getSurname();
        String body = MessageFormat.format(
                this.mailBundle.getString("mail.creditRequest.confirm.body"),
                creditRequest.getCredit().getName(),
                creditRequest.getAmount(),
                creditRequest.getTerm(),
                creditRequest.getIssuanceDate(),
                creditRequest.getProcessingDate(),
                employeeName,
                creditRequest.getDescription());

        sendEmail(toEmail, subject, body);
    }


    private void sendEmail(String toEmail, String subject, String body) {
        String fromEmail = this.mailBundle.getString("mail.username");
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
