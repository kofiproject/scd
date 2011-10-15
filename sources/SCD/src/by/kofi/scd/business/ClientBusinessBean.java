package by.kofi.scd.business;

import by.kofi.scd.dataservice.AbstractDataServiceBean;
import by.kofi.scd.dataservice.CRUDDataService;
import by.kofi.scd.dataservice.client.ClientDataService;
import by.kofi.scd.entity.Client;
import by.kofi.scd.entity.Role;
import by.kofi.scd.entity.SCDUser;
import by.kofi.scd.exceptions.SCDBusinessException;
import by.kofi.scd.exceptions.SCDTechnicalException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author harchevnikov_m
 *         Date: 15/10/11
 *         Time: 18:55
 */
@Service
public class ClientBusinessBean extends AbstractBusinessBean {

    private static final Logger LOGGER = Logger.getLogger(ClientBusinessBean.class);

    @Autowired
    private ClientDataService clientDataService;

    @Transactional(propagation = Propagation.REQUIRED)
    public Client registerClient(Client client, String password) throws SCDBusinessException {
        try {

            CRUDDataService dataService = getCRUDDataService();
            //get client role
            Role clientRole = dataService.find(Role.class, RoleBusinessBean.CLIENT_ROLE_ID);
            //create row in User table
            SCDUser user = new SCDUser();
            user.setPassword(password);
            user.setRole(clientRole);
            user = dataService.merge(user);

            //store client information
            client.setUser(user);
            client = dataService.merge(client);

            return client;
        } catch (SCDTechnicalException e) {
            throw new SCDBusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Client getClientByPassportData(String passportSeries, Long passportNo) throws SCDBusinessException {
        try {
            return this.clientDataService.getClientByPassportData(passportSeries, passportNo);
        } catch (SCDTechnicalException e) {
            throw new SCDBusinessException(e);
        }
    }

}
