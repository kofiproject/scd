package by.kofi.scd.quartz;

import by.kofi.scd.business.credit.CreditItemBusinessBean;
import by.kofi.scd.entity.*;
import by.kofi.scd.exceptions.SCDBusinessException;
import by.kofi.scd.util.DatesUtil;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.*;

/**
 * Job to update creditItems sum to pay
 * Job is performed in 12 pm avery day
 *
 * @author harchevnikov_m
 *         Date: 16/11/11
 *         Time: 22:15
 */
@Component
public class CreditItemJob extends QuartzJobBean {

    private static final Logger LOGGER = Logger.getLogger(CreditItemJob.class);
    private static final MathContext MATH_CONTEXT = new MathContext(20, RoundingMode.HALF_UP);
    private static final int INT_100 = 100;

    private CreditItemBusinessBean creditItemBusinessBean;
//    private AccountBusinessBean accountBusinessBean;
//    private AccountBusinessBean accountBusinessBean;

    public CreditItemBusinessBean getCreditItemBusinessBean() {
        return creditItemBusinessBean;
    }

    public void setCreditItemBusinessBean(CreditItemBusinessBean creditItemBusinessBean) {
        this.creditItemBusinessBean = creditItemBusinessBean;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            Date date = new Date();

            final Calendar currentDate = new GregorianCalendar();
            currentDate.clear();
            currentDate.setTime(date);

            BigDecimal daysInYear = new BigDecimal(DatesUtil.getDaysInYear(currentDate));
            BigDecimal percentDivider = new BigDecimal(INT_100).multiply(daysInYear);


            try {
                List<CreditItem> creditItems = this.creditItemBusinessBean.getCreditItemsWithPaymentsByState(CreditItemStateEnum.ACTIVE);
                for (CreditItem creditItem : creditItems) {
                    processCreditItem(creditItem, currentDate, percentDivider);
                }
            } catch (SCDBusinessException e) {
                //e.printStackTrace();
            }
        } catch (Throwable e) {
        }
    }

    private void clearTimepart(Calendar date) {
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    private void processCreditItem(CreditItem creditItem, Calendar currentDate, BigDecimal percentDivider) throws SCDBusinessException {
        Calendar itemLastUpdated = new GregorianCalendar();
        itemLastUpdated.setTime(creditItem.getLastUpdated());
        clearTimepart(itemLastUpdated);

        Calendar currentCleared = Calendar.getInstance();
        currentCleared.setTime(currentDate.getTime());
        clearTimepart(currentCleared);

        Calendar penaltyDeadLine = new GregorianCalendar();
        penaltyDeadLine.setTime(creditItem.getIssuanceDate());
        penaltyDeadLine.add(Calendar.MONTH, creditItem.getTerm().intValue());

        Set<Payment> payments = creditItem.getPayments();
        Account creditAccount = creditItem.getCreditAccount();
        Account debtAccount = creditItem.getDebitAccount();

        long daysBetween = DatesUtil.getDaysBetween(itemLastUpdated, currentCleared);

        BigDecimal creditSum = debtAccount.getSum();
        BigDecimal totalDebtSum = creditAccount.getSum();
        BigDecimal percent = creditItem.getCredit().getPercent();
        BigDecimal oneDayPercent = percent.divide(percentDivider, MATH_CONTEXT);

        for (int i = 1; i <= daysBetween; i++) {
            itemLastUpdated.add(Calendar.DAY_OF_YEAR, 1);

            BigDecimal payedSum = getPaymentSumIncludingDate(payments, itemLastUpdated);
            BigDecimal debtSum = totalDebtSum.subtract(payedSum);

            if(debtSum.compareTo(creditSum) < 0) {
                creditSum = debtSum;
            }

            debtSum = debtSum.min(creditSum);
            debtSum = debtSum.multiply(oneDayPercent);

            //penalty
            if (itemLastUpdated.after(penaltyDeadLine)) {
                debtSum = debtSum.add(creditSum.multiply(creditItem.getCredit().getPenaltyPercent()));
            }

            totalDebtSum = totalDebtSum.add(debtSum);

            PercentHistory history = new PercentHistory();
            history.setChargeDate(itemLastUpdated.getTime());
            history.setCreditItem(creditItem);
            history.setDebtSum(creditSum);
            history.setPercentSum(debtSum);
            creditItemBusinessBean.storePercentHistory(history);
        }

//        creditAccount.setSum(totalDebtSum);
//        debtAccount.setSum(creditSum);

        creditItem.setLastUpdated(currentDate.getTime());
        creditItemBusinessBean.storeCreditItem(creditItem);

/*
        if (daysBetween > 0) {
            BigDecimal creditSum = creditItem.getSum();
            BigDecimal percent = creditItem.getCredit().getPercent();
            BigDecimal oneDayPercent = percent.divide(percentDivider, MATH_CONTEXT);

            // debt by percents
            BigDecimal percentsDebt = oneDayPercent.multiply(creditSum).multiply(new BigDecimal(daysBetween));
            //debt by penalty
            BigDecimal penaltyDays = new BigDecimal(getOverdueDaysToPay(creditItem, currentDate, itemLastUpdated));
            BigDecimal penaltyDebt = creditItem.getCredit().getPenaltyPercent().multiply(creditSum).multiply(penaltyDays);

            BigDecimal commonDebt = percentsDebt.add(penaltyDebt);

            Account creditAccount = creditItem.getCreditAccount();
            creditAccount.setSum(creditAccount.getSum().add(commonDebt));

            PercentHistory history = new PercentHistory();
            history.setChargeDate(currentDate.getTime());
            history.setCreditItem(creditItem);
            history.setDebtSum(creditSum);
            history.setPercentSum(commonDebt);
            creditItemBusinessBean.storePercentHistory(history);
        }

        creditItem.setLastUpdated(currentDate.getTime());
        creditItemBusinessBean.storeCreditItem(creditItem);
*/
    }

    /**
     * Overdue days to pay penalty
     *
     * @param creditItem      creditItem
     * @param currentDate     date
     * @param itemLastUpdated item last updated date
     * @return item  overdue days to pay
     */
    private long getOverdueDaysToPay(CreditItem creditItem, Calendar currentDate, Calendar itemLastUpdated) {
        Calendar itemIssuanceDate = new GregorianCalendar();
        itemIssuanceDate.setTime(creditItem.getIssuanceDate());
        itemIssuanceDate.add(Calendar.MONTH, creditItem.getTerm().intValue());
        if (itemIssuanceDate.after(currentDate)) {
            return 0;
        }

        if (itemIssuanceDate.after(itemLastUpdated)) {
            return DatesUtil.getDaysBetween(itemIssuanceDate, currentDate);
        } else {
            return DatesUtil.getDaysBetween(itemIssuanceDate, currentDate);
        }
    }

    private BigDecimal getPaymentSumIncludingDate(Set<Payment> payments, Calendar calendar) {
        Calendar dateC = Calendar.getInstance();
        dateC.setTimeInMillis(calendar.getTimeInMillis());
        dateC.add(Calendar.DAY_OF_YEAR, 1);
        Date date = dateC.getTime();

        BigDecimal result = BigDecimal.ZERO;
        for (Payment payment : payments) {
            if (payment.getPaymentDate().before(date)) {
                result = result.add(payment.getAmount());
            }
        }

        return result;
    }

}
