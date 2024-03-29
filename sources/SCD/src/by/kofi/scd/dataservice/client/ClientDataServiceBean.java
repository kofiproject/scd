package by.kofi.scd.dataservice.client;

import by.kofi.scd.dataservice.AbstractDataServiceBean;
import by.kofi.scd.entity.Client;
import by.kofi.scd.entity.CreditItem;
import by.kofi.scd.entity.CreditItemStateEnum;
import by.kofi.scd.entity.Department;
import by.kofi.scd.exceptions.SCDTechnicalException;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * @author harchevnikov_m
 *         Date: 15/10/11
 *         Time: 21:06
 */
@Service
public class ClientDataServiceBean extends AbstractDataServiceBean implements ClientDataService {
    /**
     * {@inheritDoc}
     */
    @Transactional(propagation = Propagation.MANDATORY)
    public Client getClientByPassportData(String passportSeries, Long passportNo) throws SCDTechnicalException {
        try {
            int index = 0;
            Client result = (Client) getSession().createQuery(ClientQueryBuilder.buildClientByPassportDataQuery())
                    .setString("passportSeries", passportSeries)
                    .setLong("passportNo", passportNo).uniqueResult();

            return result;
        } catch (HibernateException e) {
            throw new SCDTechnicalException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public List<CreditItem> getCreditItems(Long clientId, CreditItemStateEnum status) throws SCDTechnicalException {
        try {
            return getSession().createQuery(ClientQueryBuilder.buildCreditItemsQuery())
                    .setLong("clientId", clientId)
                    .setLong("state", status.ordinal())
                    .list();
        } catch (HibernateException e) {
            throw new SCDTechnicalException(e);
        }
    }
    /**
     * {@inheritDoc}
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public List<CreditItem> getCreditItems(CreditItemStateEnum status) throws SCDTechnicalException {
        try {
            return getSession().createQuery(ClientQueryBuilder.buildCreditItemsByStateQuery())
                    .setLong("state", status.ordinal())
                    .list();
        } catch (HibernateException e) {
            throw new SCDTechnicalException(e);
        }
    }
    /**
     * {@inheritDoc}
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public List<CreditItem> getCreditItems() throws SCDTechnicalException {
        try {
            return getSession().createQuery(ClientQueryBuilder.buildCreditItems())
                    .list();
        } catch (HibernateException e) {
            throw new SCDTechnicalException(e);
        }
    }


    /**
     * {@inheritDoc}
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public int getCreditItemsCount(Long clientId, CreditItemStateEnum status) throws SCDTechnicalException {
        try {
            return (Integer) getSession().createQuery("select count(*) " + ClientQueryBuilder.buildCreditItemsQuery())
                    .setLong("clientId", clientId)
                    .setLong("state", status.ordinal())
                    .uniqueResult();
        } catch (HibernateException e) {
            throw new SCDTechnicalException(e);
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Client> getClients(boolean blocked) throws SCDTechnicalException {
        try {
            return getSession().createQuery(" from Client cl where cl.blocked = :blocked ")
                    .setBoolean("blocked", blocked)
                    .list();
        } catch (HibernateException e) {
            throw new SCDTechnicalException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void blockClient(Long clientId, boolean blocked) throws SCDTechnicalException {
        try {
            Client client = getHibernateCRUDService().find(Client.class, clientId);
            client.setBlocked(blocked);
        } catch (HibernateException e) {
            throw new SCDTechnicalException(e);
        }
    }
}
