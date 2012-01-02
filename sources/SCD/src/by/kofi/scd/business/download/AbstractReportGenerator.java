package by.kofi.scd.business.download;

import by.kofi.scd.business.AccountBusinessBean;
import by.kofi.scd.business.PaymentBusinessBean;
import by.kofi.scd.dto.UserContext;
import by.kofi.scd.entity.Account;
import by.kofi.scd.entity.Client;
import by.kofi.scd.entity.CreditItem;
import by.kofi.scd.entity.Payment;
import by.kofi.scd.exceptions.SCDBusinessException;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author harchevnikov_m
 *         Date: 27/10/11
 *         Time: 21:53
 */
public abstract class AbstractReportGenerator {
    private static final String DATE_FORMAT = "yyyyMMdd";

    /**
     * Create empty file with predefined name
     *
     * @param userContext userContext
     * @return file
     * @throws java.io.IOException file creation error
     */
    public File createFile(UserContext userContext, String extension) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        Calendar calendar = Calendar.getInstance(); // today

        String fileName = userContext.hashCode() +
                "_" +
                sdf.format(calendar.getTime()) +
                extension;
        File file = new File(getRootPath(), fileName);
        file.createNewFile();
        return file;
    }

    /**
     * @return root path for generated file
     */
    public String getRootPath() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        return externalContext.getRealPath("../");
    }

}
