package by.kofi.scd.business.client;

import by.kofi.scd.business.grid.AbstractGridBusinessBean;
import by.kofi.scd.business.grid.GridColumn;
import by.kofi.scd.common.FacesUtil;
import by.kofi.scd.dataservice.client.ClientDataService;
import by.kofi.scd.dto.UserContext;
import by.kofi.scd.dto.client.CreditItemResultRow;
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
public class ActiveCreditsGridBusinessBean extends AbstractGridBusinessBean {

    private static final Logger LOGGER = Logger.getLogger(ActiveCreditsGridBusinessBean.class);

    @Autowired
    private ClientDataService clientDataService;

    @Transactional(propagation = Propagation.REQUIRED)
    public List<CreditItemResultRow> executeSearch() throws SCDBusinessException {
        UserContext userContext = FacesUtil.getUserContext();
        Long clientId = userContext.getClient().getClientId();
        try {
            List<CreditItem> creditItems = clientDataService.getCreditItems(clientId, CreditItemStateEnum.ACTIVE);

            List<CreditItemResultRow> result = new ArrayList<CreditItemResultRow>(creditItems.size());
            for (CreditItem creditItem : creditItems) {
                result.add(new CreditItemResultRow(creditItem));
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
                GridColumn.ISSUENCE_DATE,
                GridColumn.CREDIT_NAME,
                GridColumn.ACCOUNT_NUMBER,
                GridColumn.SUM,
                GridColumn.TERM,
                GridColumn.SUM_TO_PAY,
                GridColumn.SUM_PAYED};
    }
}