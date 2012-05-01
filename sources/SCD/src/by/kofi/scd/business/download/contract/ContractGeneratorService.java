package by.kofi.scd.business.download.contract;

import by.kofi.scd.business.download.AbstractReportGenerator;
import by.kofi.scd.dto.UserContext;
import by.kofi.scd.entity.CreditRequest;
import by.kofi.scd.exceptions.SCDBusinessException;
import org.apache.log4j.Logger;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author harchevnikov_m
 *         Date: 27/10/11
 *         Time: 21:53
 */
@Service
public class ContractGeneratorService extends AbstractReportGenerator {
    private static final Logger LOGGER = Logger.getLogger(ContractGeneratorService.class);
    private static final String REPORT_DATE_FORMAT = "dd.MM.yyyy";

    public File generateReport(CreditRequest creditRequest, UserContext userContext) throws SCDBusinessException {


        SimpleDateFormat dateFormat = new SimpleDateFormat(REPORT_DATE_FORMAT);

        File file = null;
        try {
            //todo generate report
            String name = creditRequest.getClient().toString();
            String oldName = "_name_";
            String summ = creditRequest.getSum().toString();
            String oldSum = "_summ_";
            String percent = creditRequest.getCredit().getPercent().toString();
            String oldPer = "_percent_";
            String penu = creditRequest.getCredit().getPenaltyPercent().toString();
            String oldPenu = "_penu_";

            String addressStr = creditRequest.getClient().getPermanentResidence();
            String address = "_address_";

            String currDate = "_currentdate_";
            String currDateStr = dateFormat.format(new Date());

            Calendar cl = Calendar.getInstance();
            cl.setTime(new Date());
            cl.add(Calendar.MONTH, (int)creditRequest.getTerm());
            String toDate = "_todate_";
            String toDateStr = dateFormat.format(cl.getTime());


            File template = new File(getRootPath() + "/contract.doc");

            file = createFile(userContext, ".doc");

            FileWriter fos = new FileWriter(file);
            FileInputStream fis = new FileInputStream(template);
            HWPFDocument doc = new HWPFDocument(fis);

            WordExtractor extractor = new WordExtractor(doc);

            String[] dataArray = extractor.getParagraphText();
            for (int i = 0; i < dataArray.length; i++) {
                dataArray[i] = dataArray[i].replaceAll(oldName, name);
                dataArray[i] = dataArray[i].replaceAll(oldSum, summ);
                dataArray[i] = dataArray[i].replaceAll(oldPer, percent);
                dataArray[i] = dataArray[i].replaceAll(oldPenu, penu);

                dataArray[i] = dataArray[i].replaceAll(address, addressStr);
                dataArray[i] = dataArray[i].replaceAll(currDate, currDateStr);
                dataArray[i] = dataArray[i].replaceAll(toDate, toDateStr);

                fos.write(dataArray[i]);
            }
            fos.close();
            fis.close();
        } catch (IOException e) {
            LOGGER.error(e);
            throw new SCDBusinessException("FileOutputStream error", e);
        }

        return file;
    }

}
