package by.kofi.scd.business.bank.expert.creditRequest;

import by.kofi.scd.business.grid.AbstractGridBusinessBean;
import by.kofi.scd.business.grid.GridColumn;
import by.kofi.scd.common.FacesUtil;
import by.kofi.scd.dataservice.credit.CreditRequestDataService;
import by.kofi.scd.dto.UserContext;
import by.kofi.scd.dto.bank.ExpertCreditRequestResultRow;
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
public class ExpertActiveCreditRequestGridBean extends AbstractGridBusinessBean {

    private static final Logger LOGGER = Logger.getLogger(ExpertActiveCreditRequestGridBean.class);

    @Autowired
    private CreditRequestDataService creditRequestDataService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<ExpertCreditRequestResultRow> executeSearch() throws SCDBusinessException {
        UserContext userContext = FacesUtil.getUserContext();
        try {
            List<CreditRequest> creditRequests = creditRequestDataService.getCreditRequests(
                    CreditRequestStateEnum.IN_PROCESS);

            List<ExpertCreditRequestResultRow> result = new ArrayList<ExpertCreditRequestResultRow>(creditRequests.size());
            for (CreditRequest creditRequest : creditRequests) {
                result.add(new ExpertCreditRequestResultRow(creditRequest));
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
                GridColumn.CLIENT_NAME,
                GridColumn.SUM,
                GridColumn.TERM
        };
    }
}