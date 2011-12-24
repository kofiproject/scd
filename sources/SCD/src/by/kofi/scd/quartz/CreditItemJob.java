package by.kofi.scd.quartz;

import by.kofi.scd.business.credit.CreditItemBusinessBean;
import by.kofi.scd.entity.Account;
import by.kofi.scd.entity.CreditItem;
import by.kofi.scd.entity.CreditItemStateEnum;
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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

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

    public CreditItemBusinessBean getCreditItemBusinessBean() {
        return creditItemBusinessBean;
    }

    public void setCreditItemBusinessBean(CreditItemBusinessBean creditItemBusinessBean) {
        this.creditItemBusinessBean = creditItemBusinessBean;
    }

    /*  public AccountBusinessBean getAccountBusinessBean() {
            return accountBusinessBean;
        }

        public void setAccountBusinessBean(AccountBusinessBean accountBusinessBean) {
            this.accountBusinessBean = accountBusinessBean;
        }
    */
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Calendar currentDate = new GregorianCalendar();
        currentDate.setTime(new Date());

        BigDecimal daysInYear = new BigDecimal(DatesUtil.getDaysInYear(currentDate));
        BigDecimal percentDivider = new BigDecimal(INT_100).multiply(daysInYear);


        try {
            List<CreditItem> creditItems = this.creditItemBusinessBean.getCreditItemsByState(CreditItemStateEnum.ACTIVE);
            for (CreditItem creditItem : creditItems) {
                processCreditItem(creditItem, currentDate, percentDivider);
            }
        } catch (SCDBusinessException e) {
            //e.printStackTrace();
        }
//        System.out.println("----------------------------------------------------");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    private void processCreditItem(CreditItem creditItem, Calendar currentDate, BigDecimal percentDivider) throws SCDBusinessException {
        Calendar itemLastUpdated = new GregorianCalendar();
        itemLastUpdated.setTime(creditItem.getLastUpdated());

        long daysBetween = DatesUtil.getDaysBetween(itemLastUpdated, currentDate);
        if (daysBetween < 0) {
            LOGGER.error("processCreditItem: negative days delta form creditItem id= " + creditItem.getCreditItemId());
            return;
        }

        BigDecimal creditSum = creditItem.getSum();
        BigDecimal percent = creditItem.getCredit().getPercent();
        BigDecimal oneDayPercent = percent.divide(percentDivider, MATH_CONTEXT);

        // debt by percents
        BigDecimal percentsDebt = oneDayPercent.multiply(creditSum);
        //debt by penalty
        BigDecimal penaltyDays = new BigDecimal(getOverdueDaysToPay(creditItem, currentDate, itemLastUpdated));
        BigDecimal penaltyDebt = creditItem.getCredit().getPenaltyPercent().multiply(creditSum).multiply(penaltyDays);


        creditItem.setLastUpdated(currentDate.getTime());

        BigDecimal commonDebt = percentsDebt.add(penaltyDebt);

        Account creditAccount = creditItem.getCreditAccount();
        creditAccount.setSum(creditAccount.getSum().add(commonDebt));

/*

        creditItem.setCalculatedAmount(creditItem.getCalculatedAmount().add(percentsDebt).add(penaltyDebt));
        creditItem.setPenaltyAmount(creditItem.getPenaltyAmount().add(penaltyDebt));
*/

        creditItemBusinessBean.storeCreditItem(creditItem);
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
}
