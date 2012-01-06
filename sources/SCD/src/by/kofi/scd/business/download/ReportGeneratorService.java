package by.kofi.scd.business.download;

import by.kofi.scd.business.AccountBusinessBean;
import by.kofi.scd.business.PaymentBusinessBean;
import by.kofi.scd.business.credit.CreditItemBusinessBean;
import by.kofi.scd.dto.UserContext;
import by.kofi.scd.entity.*;
import by.kofi.scd.exceptions.SCDBusinessException;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author harchevnikov_m
 *         Date: 27/10/11
 *         Time: 21:53
 */
@Service
public class ReportGeneratorService extends AbstractReportGenerator implements ReportGenerator {
    private static final Logger LOGGER = Logger.getLogger(ReportGeneratorService.class);
    private static final String REPORT_DATE_FORMAT = "dd-MM-yyyy HH:mm";

    @Autowired
    private AccountBusinessBean accountBusinessBean;

    @Autowired
    private PaymentBusinessBean paymentBusinessBean;

    @Autowired
    @Qualifier("ciBB")
    private CreditItemBusinessBean creditItemBusinessBean;

    public File generateReport(Long accountNumber, UserContext userContext) throws SCDBusinessException {

        Account account = accountBusinessBean.getAccountByNumber(accountNumber);
//        CreditItem creditItem = account.getCreditItem();
        CreditItem creditItem = creditItemBusinessBean.getCreditItemById(account.getCreditItem().getCreditItemId()); //account.getCreditItem();


        account = account.getCreditItem().getPaymentsAccount();
        List<Payment> paymentsByAccount = paymentBusinessBean.getPaymentsByAccount(account.getAccountNumber());

        SimpleDateFormat dateFormat = new SimpleDateFormat(REPORT_DATE_FORMAT);

        FileOutputStream out = null;
        File file = null;
        try {
            file = createFile(userContext, ".xls");
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

        Client client = creditItem.getClient();
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
        cell.setCellValue("Account");

        cell = row.createCell(1);
        cell.setCellValue(account.getAccountNumber());

        row = sheet.createRow(4); // кредитный план
        cell = row.createCell(0);
        cell.setCellValue("Credit");

        cell = row.createCell(1);
        cell.setCellValue(creditItem.getCredit().getName());

        row = sheet.createRow(5); // сумма кредита
        cell = row.createCell(0);
        cell.setCellValue("Credit sum");

        cell = row.createCell(1);
        cell.setCellValue(creditItem.getSum().toString());

        row = sheet.createRow(6); // срок
        cell = row.createCell(0);
        cell.setCellValue("Term");

        cell = row.createCell(1);
        cell.setCellValue(creditItem.getTerm().toString());

        row = sheet.createRow(7); // сумма для погашения
        cell = row.createCell(0);
        cell.setCellValue("Sum to pay");

        cell = row.createCell(1);
        BigDecimal sumToPay = creditItem.getCreditAccount().getSum();
        cell.setCellValue(sumToPay.toString());

        row = sheet.createRow(8); // погашенная сумма
        cell = row.createCell(0);
        cell.setCellValue("Paid sum");

        cell = row.createCell(1);
        cell.setCellValue(creditItem.getPaymentsAccount().getSum().toString());

        row = sheet.createRow(9); // пеня
        cell = row.createCell(0);
        cell.setCellValue("Penalty");

        cell = row.createCell(1);
        cell.setCellValue(sumToPay.subtract(creditItem.getSum()).toString());

        row = sheet.createRow(10); // состояние
        cell = row.createCell(0);
        cell.setCellValue("State");

        cell = row.createCell(1);
        Date closingDate = creditItem.getClosingDate();
        if (closingDate != null) {
            cell.setCellValue(closingDate);
        }

        row = sheet.createRow(12); // отчет сформирован
        cell = row.createCell(0);
        cell.setCellValue("Report was generated");
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
        cell.setCellValue("Date");

        cell = row.createCell(1);
        cell.setCellStyle(headerStyle);
        cell.setCellValue("Sum");

        cell = row.createCell(2);
        cell.setCellStyle(headerStyle);
        cell.setCellValue("Department");

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


        Employee employee = userContext.getEmployee();
        if (employee != null && employee.getUser().getRole().getRoleId() == 5) /* if role manager*/ {
            sheet = wb.createSheet();

            sheet.setColumnWidth(0, 6000);
            sheet.setColumnWidth(1, 6000);
            sheet.setColumnWidth(2, 6000);

            row = sheet.createRow(0);
            cell = row.createCell(0);
            cell.setCellValue("Report for the percent");

            row = sheet.createRow(2);
            cell = row.createCell(0);
            cell.setCellValue("Account number");

            cell = row.createCell(1);
            cell.setCellValue(accountNumber.toString());

            row = sheet.createRow(5);
            cell = row.createCell(0);
            cell.setCellValue("Date");

            cell = row.createCell(1);
            cell.setCellValue("Amount of debt");

            cell = row.createCell(2);
            cell.setCellValue("Percent sum");

            int incRow = 0;

            List<PercentHistory> percentHistoryList = null;

            try {
                percentHistoryList = new ArrayList<PercentHistory>(creditItem.getPercentHistories());
                Collections.sort(percentHistoryList, new Comparator<PercentHistory>() {
                    @Override
                    public int compare(PercentHistory percentHistory, PercentHistory percentHistory1) {
                        return percentHistory.getChargeDate().compareTo(percentHistory1.getChargeDate());
                    }
                });
            } catch (Exception ex) {
                percentHistoryList = null;
            }

            if (percentHistoryList != null) {
                for (PercentHistory percentHistory : percentHistoryList) {
                    row = sheet.createRow(6 + incRow);
                    cell = row.createCell(0);
                    cell.setCellValue(dateFormat.format(percentHistory.getChargeDate()));

                    cell = row.createCell(1);
                    cell.setCellValue(percentHistory.getDebtSum().toString());

                    cell = row.createCell(2);
                    cell.setCellValue(percentHistory.getPercentSum().toString());

                    incRow++;
                }
            }

            row = sheet.createRow(8 + incRow); // Р С•РЎвЂљРЎвЂЎР ВµРЎвЂљ РЎРѓРЎвЂћР С•РЎР‚Р СР С‘РЎР‚Р С•Р Р†Р В°Р Р…
            cell = row.createCell(0);
            cell.setCellValue("Report was generated");
            cell = row.createCell(1);
            cell.setCellValue(dateFormat.format(currentDate));
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

}
