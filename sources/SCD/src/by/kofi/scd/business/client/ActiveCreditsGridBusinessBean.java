package by.kofi.scd.business.client;

import by.kofi.scd.business.grid.AbstractGridBusinessBean;
import by.kofi.scd.business.grid.GridHeader;
import by.kofi.scd.business.grid.ResultRowField;
import by.kofi.scd.common.FacesUtil;
import by.kofi.scd.dataservice.client.ClientDataService;
import by.kofi.scd.dto.UserContext;
import by.kofi.scd.dto.client.ActiveCreditsResultRow;
import by.kofi.scd.entity.*;
import by.kofi.scd.exceptions.SCDBusinessException;
import by.kofi.scd.exceptions.SCDTechnicalException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
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
    public List<ActiveCreditsResultRow> executeSearch() throws SCDBusinessException {
        UserContext userContext = FacesUtil.getUserContext();
        Long clientId = userContext.getClient().getClientId();
        try {
            List<CreditItem> creditItems = clientDataService.getCreditItems(clientId, CreditItemStateEnum.ACTIVE);

            List<ActiveCreditsResultRow> result = new ArrayList<ActiveCreditsResultRow>(creditItems.size());
            for (CreditItem creditItem : creditItems) {
                result.add(new ActiveCreditsResultRow(creditItem));
            }

            return result;

        } catch (SCDTechnicalException e) {
            LOGGER.error(e.getMessage());
            throw new SCDBusinessException("getActiveCreditItems", e);
        }
    }

    @Override
    public GridHeader[] getHeaders() {
        return new GridHeader[]{
                GridHeader.ISSUENCE_DATE,
                GridHeader.CREDIT_NAME,
                GridHeader.ACCOUNT_NUMBER,
                GridHeader.SUM,
                GridHeader.TERM,
                GridHeader.SUM_TO_PAY,
                GridHeader.ACCOUNT_NUMBER};
    }

    @Override
    public ResultRowField[] getFields() {
        return new ResultRowField[]{
                ResultRowField.ISSUENCE_DATE,
                ResultRowField.CREDIT_NAME,
                ResultRowField.ACCOUNT_NUMBER,
                ResultRowField.SUM,
                ResultRowField.TERM,
                ResultRowField.SUM_TO_PAY,
                ResultRowField.ACCOUNT_NUMBER};
    }
}