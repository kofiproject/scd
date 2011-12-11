package by.kofi.scd.business.client;

import by.kofi.scd.business.grid.AbstractGridBusinessBean;
import by.kofi.scd.business.grid.GridColumn;
import by.kofi.scd.common.FacesUtil;
import by.kofi.scd.dataservice.client.ClientDataService;
import by.kofi.scd.dto.UserContext;
import by.kofi.scd.dto.client.ClientResultRow;
import by.kofi.scd.dto.client.CreditItemResultRow;
import by.kofi.scd.entity.Client;
import by.kofi.scd.entity.CreditItem;
import by.kofi.scd.entity.CreditItemStateEnum;
import by.kofi.scd.exceptions.SCDBusinessException;
import by.kofi.scd.exceptions.SCDTechnicalException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author harchevnikov_m
 *         Date: 15/10/11
 *         Time: 18:55
 */
@Service
public class BlockedClientsGridBusinessBean extends AbstractGridBusinessBean {
    private static final Logger LOGGER = Logger.getLogger(BlockedClientsGridBusinessBean.class);

    @Autowired
    private ClientDataService clientDataService;

    protected boolean isForBlocked() {
        return true;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<ClientResultRow> executeSearch() throws SCDBusinessException {
        try {
            List<Client> clients = clientDataService.getClients(isForBlocked());

            List<ClientResultRow> result = new ArrayList<ClientResultRow>(clients.size());
            for (Client client : clients) {
                result.add(new ClientResultRow(client));
            }

            return result;

        } catch (SCDTechnicalException e) {
            LOGGER.error(e.getMessage());
            throw new SCDBusinessException("getActiveCreditItems", e);
        }
    }

    @Override
    public GridColumn[] getColumns() {
        return new GridColumn[]{
                GridColumn.CLIENT_IDENTITY_NO,
                GridColumn.CLIENT_REGISTRATION_DATE,
                GridColumn.CLIENT_NAME,
                GridColumn.CLIENT_EMAIL
/*
                GridColumn.CLIENT_PASSPORT,
                GridColumn.CLIENT_SEX,
                GridColumn.CLIENT_PERMANENT_RESIDENCE,
                GridColumn.CLIENT_CURRENT_RESIDENCE,
                GridColumn.CLIENT_PHONE_MOBILE,
                GridColumn.CLIENT_PHONE,
                GridColumn.CLIENT_EMAIL,
                GridColumn.CLIENT_BIRTHDAY,
                GridColumn.CLIENT_JOB_PLACE,
                GridColumn.CLIENT_JOB_POSITION
*/
        };
    }
}