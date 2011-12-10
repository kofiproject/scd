package by.kofi.scd.business.client;

import by.kofi.scd.business.AbstractBusinessBean;
import by.kofi.scd.business.RoleBusinessBean;
import by.kofi.scd.dataservice.CRUDDataService;
import by.kofi.scd.dataservice.client.ClientDataService;
import by.kofi.scd.entity.Client;
import by.kofi.scd.entity.CreditItemStateEnum;
import by.kofi.scd.entity.Role;
import by.kofi.scd.entity.SCDUser;
import by.kofi.scd.exceptions.SCDBusinessException;
import by.kofi.scd.exceptions.SCDTechnicalException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

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
            client.setRegistrationDate(new Date());
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

    @Transactional(propagation = Propagation.REQUIRED)
    public Client updateProfile(Client client) throws SCDBusinessException {
        try {
            CRUDDataService crudDataService = getCRUDDataService();
            crudDataService.merge(client.getUser());
            return crudDataService.merge(client);
        } catch (SCDTechnicalException e) {
            throw new SCDBusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int getActiveCreditsCount(Client client) throws SCDBusinessException {
        try {
            return this.clientDataService.getCreditItemsCount(client.getClientId(), CreditItemStateEnum.ACTIVE);
        } catch (SCDTechnicalException e) {
            throw new SCDBusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Client getClientById(Long clientId) throws SCDBusinessException {
        try {
            return getCRUDDataService().find(Client.class, clientId);
        } catch (SCDTechnicalException e) {
            throw new SCDBusinessException(e);
        }
    }

    public List<Client> getClients() throws SCDBusinessException {
        try {
            return getCRUDDataService().list(Client.class);
        } catch (SCDTechnicalException e) {
            throw new SCDBusinessException(e);
        }
    }

    /**
     * Block/unblock client
     *
     * @param clientId
     * @param blocked
     * @throws SCDBusinessException
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void blockClient(Long clientId, boolean blocked) throws SCDBusinessException {
        try {
            clientDataService.blockClient(clientId, blocked);
        } catch (SCDTechnicalException e) {
            throw new SCDBusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteClient(Long clientId) throws SCDBusinessException {
        try {
            //
            CRUDDataService crudDataService = getCRUDDataService();
            Client client = crudDataService.find(Client.class, clientId);

            client.getCreditItems();
            client.getCreditRequests();
            SCDUser user = client.getUser();
            client.getPayments();

            crudDataService.delete(Client.class, clientId);
        } catch (SCDTechnicalException e) {
            throw new SCDBusinessException(e);
        }
    }
}
