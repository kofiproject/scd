package by.kofi.scd.business.download.contract;

import by.kofi.scd.business.AccountBusinessBean;
import by.kofi.scd.business.PaymentBusinessBean;
import by.kofi.scd.business.download.AbstractReportGenerator;
import by.kofi.scd.business.download.ReportGenerator;
import by.kofi.scd.dto.UserContext;
import by.kofi.scd.entity.*;
import by.kofi.scd.exceptions.SCDBusinessException;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author harchevnikov_m
 *         Date: 27/10/11
 *         Time: 21:53
 */
@Service
public class ContractGeneratorService extends AbstractReportGenerator {
    private static final Logger LOGGER = Logger.getLogger(ContractGeneratorService.class);
    private static final String REPORT_DATE_FORMAT = "dd-MM-yyyy HH:mm";

    public File generateReport(CreditRequest creditRequest, UserContext userContext) throws SCDBusinessException {

        //todo generate report

        FileOutputStream out = null;
        File file = null;
        try {
            file = createFile(userContext, ".doc");
            out = new FileOutputStream(file);
        } catch (IOException e) {
            LOGGER.error(e);
            throw new SCDBusinessException("FileOutputStream error", e);
        }


        return file;
    }

}
