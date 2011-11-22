package by.kofi.scd.business.client.creditRequest;

import by.kofi.scd.business.grid.AbstractGridBusinessBean;
import by.kofi.scd.business.grid.GridColumn;
import by.kofi.scd.common.FacesUtil;
import by.kofi.scd.dataservice.credit.request.CreditRequestDataService;
import by.kofi.scd.dto.UserContext;
import by.kofi.scd.dto.client.creditRequest.CreditRequestResultRow;
import by.kofi.scd.entity.CreditRequest;
import by.kofi.scd.entity.CreditRequestStateEnum;
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
public class ConfirmedCreditRequestGridBean extends AbstractGridBusinessBean {

    private static final Logger LOGGER = Logger.getLogger(ConfirmedCreditRequestGridBean.class);

    @Autowired
    private CreditRequestDataService creditRequestDataService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<CreditRequestResultRow> executeSearch() throws SCDBusinessException {
        UserContext userContext = FacesUtil.getUserContext();
        Long clientId = userContext.getClient().getClientId();
        try {
            List<CreditRequest> creditRequests = creditRequestDataService.getCreditRequests(clientId, CreditRequestStateEnum.CONFIRMED);

            List<CreditRequestResultRow> result = new ArrayList<CreditRequestResultRow>(creditRequests.size());
            for (CreditRequest creditRequest : creditRequests) {
                result.add(new CreditRequestResultRow(creditRequest));
            }

            return result;

        } catch (SCDTechnicalException e) {
            LOGGER.error(e.getMessage());
            throw new SCDBusinessException("executeSearch", e);
        }
    }


    @Override
    public GridColumn[] getColumns() {
        return new GridColumn[]{
                GridColumn.ISSUENCE_DATE,
                GridColumn.PROCESSING_DATE,
                GridColumn.CREDIT_NAME,
                GridColumn.ACCOUNT_NUMBER,
                GridColumn.SUM,
                GridColumn.TERM,
                GridColumn.CREDIT_EXPERT
        };
    }
}