package by.kofi.scd.business.client;

import by.kofi.scd.business.AbstractBusinessBean;
import by.kofi.scd.business.AbstractGridBusinessBean;
import by.kofi.scd.business.RoleBusinessBean;
import by.kofi.scd.dataservice.CRUDDataService;
import by.kofi.scd.dataservice.client.ClientDataService;
import by.kofi.scd.dto.UserContext;
import by.kofi.scd.entity.*;
import by.kofi.scd.exceptions.SCDBusinessException;
import by.kofi.scd.exceptions.SCDTechnicalException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
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

    private List<CreditItem> activeCreditItems = new ArrayList<CreditItem>(0);


    @Transactional(propagation = Propagation.REQUIRED)
    public void executeSearch() throws SCDBusinessException {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        UserContext userContext = (UserContext) session.getAttribute("userContext");
        Long clientId = userContext.getClient().getClientId();
        try {
            List<CreditItem> creditItems = clientDataService.getCreditItems(clientId, CreditItemStateEnum.ACTIVE);
            setActiveCreditItems(creditItems);
        } catch (SCDTechnicalException e) {
            throw new SCDBusinessException("getActiveCreditItems", e);
        }

    }

    public List<CreditItem> getActiveCreditItems() {
        try {
            executeSearch();
        } catch (SCDBusinessException e) {
            e.printStackTrace();
        }
        return activeCreditItems;
    }

    public void setActiveCreditItems(List<CreditItem> activeCreditItems) {
        this.activeCreditItems = activeCreditItems;
    }

    public Object generateReport() throws IOException {
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
        return null;
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
