package by.kofi.scd.business.credit;

import by.kofi.scd.business.grid.AbstractGridBusinessBean;
import by.kofi.scd.business.grid.GridColumn;
import by.kofi.scd.common.FacesUtil;
import by.kofi.scd.dataservice.client.ClientDataService;
import by.kofi.scd.dataservice.credit.CreditDataService;
import by.kofi.scd.dataservice.credit.CreditDataServiceBean;
import by.kofi.scd.dto.UserContext;
import by.kofi.scd.dto.client.ClientResultRow;
import by.kofi.scd.dto.credit.CreditResultRow;
import by.kofi.scd.entity.Client;
import by.kofi.scd.entity.Credit;
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
public class CreditGridBusinessBean extends AbstractGridBusinessBean {
    private static final Logger LOGGER = Logger.getLogger(CreditGridBusinessBean.class);

    @Autowired
    private CreditBusinessBean creditBusinessBean;

    @Transactional(propagation = Propagation.REQUIRED)
    public List<CreditResultRow> executeSearch() throws SCDBusinessException {
        List<Credit> credits = creditBusinessBean.getCredits();

        List<CreditResultRow> result = new ArrayList<CreditResultRow>(credits.size());
        for (Credit credit : credits) {
            result.add(new CreditResultRow(credit));
        }

        return result;
    }

    @Override
    public GridColumn[] getColumns() {
        return new GridColumn[]{
                GridColumn.CREDIT_DETAILS_NAME,
                GridColumn.CREDIT_PERCENT,
                GridColumn.CREDIT_PENALTY_PERCENT,
                GridColumn.CREDIT_MAX_TERM
        };
    }
}