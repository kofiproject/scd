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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author harchevnikov_m
 *         Date: 27/10/11
 *         Time: 21:53
 */
@Service
public class ReportGeneratorService implements ReportGenerator {
    private static final Logger LOGGER = Logger.getLogger(ReportGeneratorService.class);
    private static final String DATE_FORMAT = "yyyyMMdd";
    private static final String REPORT_DATE_FORMAT = "dd-MM-yyyy HH:MM";

    @Autowired
    private AccountBusinessBean accountBusinessBean;

    @Autowired
    private PaymentBusinessBean paymentBusinessBean;

    public File generateReport(Long accountNumber, UserContext userContext) throws SCDBusinessException {

        Account account = accountBusinessBean.getAccountByNumber(accountNumber);
        List<Payment> paymentsByAccount = paymentBusinessBean.getPaymentsByAccount(accountNumber);

        SimpleDateFormat dateFormat = new SimpleDateFormat(REPORT_DATE_FORMAT);

        FileOutputStream out = null;
        File file = null;
        try {
            file = createFile(userContext);
            out = new FileOutputStream(file);
        } catch (IOException e) {
            LOGGER.error(e);
            throw new SCDBusinessException("FileOutputStream error", e);
        }


        // create a new workbook
        Workbook wb = new HSSFWorkbook();
        // create a new sheet
        Sheet sheet = wb.createSheet();
        // declare a row object reference
        Row row = null;
        // declare a cell object reference
        Cell cell = null;

        Client client = userContext.getClient();
        sheet.setColumnWidth(0, 6000);
        sheet.setColumnWidth(1, 3000);
        sheet.setColumnWidth(2, 6000);

        row = sheet.createRow(0);
        cell = row.createCell(0);
        cell.setCellValue(client.getSurname());
        cell = row.createCell(1);
        cell.setCellValue(client.getName());
        cell = row.createCell(2);
        cell.setCellValue(client.getMiddleName());

        row = sheet.createRow(1);
        cell = row.createCell(0);
        cell.setCellValue(client.getUser().getUserId());

        row = sheet.createRow(3); // номер счета
        cell = row.createCell(0);
        cell.setCellValue("№ счета");

        cell = row.createCell(1);
        cell.setCellValue(account.getAccountNumber());

        row = sheet.createRow(4); // кредитный план
        cell = row.createCell(0);
        cell.setCellValue("Кредитный план");

        cell = row.createCell(1);
        CreditItem creditItem = account.getCreditItem();
        cell.setCellValue(creditItem.getCredit().getName());

        row = sheet.createRow(5); // сумма кредита
        cell = row.createCell(0);
        cell.setCellValue("Сумма кредита");

        cell = row.createCell(1);
        cell.setCellValue(creditItem.getAmount().toString());

        row = sheet.createRow(6); // срок
        cell = row.createCell(0);
        cell.setCellValue("Срок");

        cell = row.createCell(1);
        cell.setCellValue(creditItem.getTerm().toString());

        row = sheet.createRow(7); // сумма для погашения
        cell = row.createCell(0);
        cell.setCellValue("Сумма для погашения");

        cell = row.createCell(1);
        cell.setCellValue(creditItem.getCalculatedAmount().toString());

        row = sheet.createRow(8); // погашенная сумма
        cell = row.createCell(0);
        cell.setCellValue("Погашенная сумма");

        cell = row.createCell(1);
        cell.setCellValue(creditItem.getPaidAmount().toString());

        row = sheet.createRow(9); // пеня
        cell = row.createCell(0);
        cell.setCellValue("Пеня");

        cell = row.createCell(1);
        cell.setCellValue(creditItem.getPenaltyAmount().toString());

        row = sheet.createRow(10); // состояние
        cell = row.createCell(0);
        cell.setCellValue("Состояние");

        cell = row.createCell(1);
        Date closingDate = creditItem.getClosingDate();
        if (closingDate != null) {
            cell.setCellValue(closingDate);
        }

        row = sheet.createRow(12); // отчет сформирован
        cell = row.createCell(0);
        cell.setCellValue("Отчет сформирован");
        cell = row.createCell(1);
        java.util.Date currentDate = new java.util.Date();
        cell.setCellValue(dateFormat.format(currentDate));

        row = sheet.createRow(14); // начало таблицы платежей

        CellStyle headerStyle = wb.createCellStyle();
        headerStyle.setBorderTop(CellStyle.BORDER_THIN);
        headerStyle.setBorderLeft(CellStyle.BORDER_THIN);
        headerStyle.setBorderRight(CellStyle.BORDER_THIN);
        headerStyle.setBorderBottom(CellStyle.BORDER_THIN);

        cell = row.createCell(0);
        cell.setCellStyle(headerStyle);
        cell.setCellValue("Дата");

        cell = row.createCell(1);
        cell.setCellStyle(headerStyle);
        cell.setCellValue("Сумма");

        cell = row.createCell(2);
        cell.setCellStyle(headerStyle);
        cell.setCellValue("Отделение");

        headerStyle = null;

        // 3и стиля ячеек таблицы
        CellStyle leftCellStyle = wb.createCellStyle();
        leftCellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        leftCellStyle.setBorderBottom(CellStyle.BORDER_THIN);

        CellStyle centerCellStyle = wb.createCellStyle();
        centerCellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        centerCellStyle.setBorderRight(CellStyle.BORDER_THIN);
        centerCellStyle.setBorderBottom(CellStyle.BORDER_THIN);

        CellStyle rightCellStyle = wb.createCellStyle();
        rightCellStyle.setBorderRight(CellStyle.BORDER_THIN);
        rightCellStyle.setBorderBottom(CellStyle.BORDER_THIN);

        int i = 0;
        for (Payment payment : paymentsByAccount) {
            row = sheet.createRow(15 + i);
            cell = row.createCell(0);
            cell.setCellStyle(leftCellStyle);
            cell.setCellValue(dateFormat.format(payment.getPaymentDate()));

            cell = row.createCell(1);
            cell.setCellStyle(centerCellStyle);
            cell.setCellValue(payment.getAmount().toString());

            cell = row.createCell(2);
            cell.setCellStyle(rightCellStyle);
            cell.setCellValue(payment.getEmployee().getDepartment().getAddress());

            i++;
        }

        try {
            wb.write(out);
            out.close();
        } catch (IOException e) {
            LOGGER.error(e);
            throw new SCDBusinessException(e.getMessage(), e);
        }

        return file;
    }

    /**
     * Create empty file with predefined name
     *
     * @param userContext userContext
     * @return file
     * @throws IOException file creation error
     */
    private File createFile(UserContext userContext) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        Calendar calendar = Calendar.getInstance(); // today

        String fileName = userContext.getClient().getUser().getUserId() +
                "_" +
                sdf.format(calendar.getTime()) +
                ".xls";
        File file = new File(getRootPath(), fileName);
        file.createNewFile();
        return file;
    }

    /**
     * @return root path for generated file
     */
    private String getRootPath() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        return externalContext.getRealPath("../");
    }

}
