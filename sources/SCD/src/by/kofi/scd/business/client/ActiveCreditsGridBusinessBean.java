package by.kofi.scd.business.client;

import by.kofi.scd.business.grid.AbstractGridBusinessBean;
import by.kofi.scd.business.grid.GridHeader;
import by.kofi.scd.business.grid.ResultRowField;
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
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        UserContext userContext = (UserContext) session.getAttribute("userContext");
        Long clientId = userContext.getClient().getClientId();
        try {
            List<CreditItem> creditItems = clientDataService.getCreditItems(clientId, CreditItemStateEnum.ACTIVE);

            List<ActiveCreditsResultRow> result = new ArrayList<ActiveCreditsResultRow>(creditItems.size());
            for (CreditItem creditItem : creditItems) {
                result.add(new ActiveCreditsResultRow(creditItem));
            }

            return result;

        } catch (SCDTechnicalException e) {
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

    public void downloadReport() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String realPath = externalContext.getRealPath("/");
        File file = new File(realPath, "test.tmp");
        FileInputStream fileIn = new FileInputStream(file);
/*
        try {
            file.createNewFile();
        } catch (IOException e) {

        }
*/
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
        response.setDateHeader("Expires", 0);
        response.setContentType("application/ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
        ServletOutputStream outputStream = response.getOutputStream();
        fastChannelCopy(Channels.newChannel(new FileInputStream(file)), Channels.newChannel(outputStream));

        outputStream.close();
    }

    public void generateReport() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String realPath = externalContext.getRealPath("/");
        File file = new File(realPath, "test.tmp");
        try {
            file.createNewFile();
        } catch (IOException e) {

        }
    }

    void fastChannelCopy(final ReadableByteChannel src, final WritableByteChannel dest) throws IOException {
        final ByteBuffer buffer = ByteBuffer.allocateDirect(16 * 1024);
        while (src.read(buffer) != -1) {
            buffer.flip();
            dest.write(buffer);
            buffer.compact();
        }
        buffer.flip();
        while (buffer.hasRemaining()) {
            dest.write(buffer);
        }
    }
}
