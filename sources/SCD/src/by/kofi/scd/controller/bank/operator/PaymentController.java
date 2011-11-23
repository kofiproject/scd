package by.kofi.scd.controller.bank.operator;

import by.kofi.scd.business.UserBusinessBean;
import by.kofi.scd.business.client.ClientBusinessBean;
import by.kofi.scd.entity.Client;
import by.kofi.scd.entity.SCDUser;
import by.kofi.scd.exceptions.SCDBusinessException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author harchevnikov_m
 *         Date: 23/11/11
 *         Time: 22:00
 */
@Controller
public class PaymentController {

    private static final Logger LOGGER = Logger.getLogger(PaymentController.class);
    private Long clientIdentity;
    private Client client;

    @Autowired
    private UserBusinessBean userBusinessBean;

    public Long getClientIdentity() {
        return clientIdentity;
    }

    public void setClientIdentity(Long clientIdentity) throws SCDBusinessException {
        this.clientIdentity = clientIdentity;
        SCDUser user = userBusinessBean.getUserByIdentityId(clientIdentity);
        setClient(user.getClient());
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
