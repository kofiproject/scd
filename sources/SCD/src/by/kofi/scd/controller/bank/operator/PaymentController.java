package by.kofi.scd.controller.bank.operator;

import by.kofi.scd.business.AccountBusinessBean;
import by.kofi.scd.business.PaymentBusinessBean;
import by.kofi.scd.business.UserBusinessBean;
import by.kofi.scd.business.grid.AbstractGridBusinessBean;
import by.kofi.scd.business.grid.GridColumn;
import by.kofi.scd.common.FacesUtil;
import by.kofi.scd.dataservice.client.ClientDataService;
import by.kofi.scd.dto.client.CreditItemResultRow;
import by.kofi.scd.entity.*;
import by.kofi.scd.exceptions.SCDBusinessException;
import by.kofi.scd.exceptions.SCDTechnicalException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author harchevnikov_m
 *         Date: 23/11/11
 *         Time: 22:00
 */
@Controller
//todo code spagetti ))
public class PaymentController extends AbstractGridBusinessBean {

    private static final Logger LOGGER = Logger.getLogger(PaymentController.class);
    private Long clientIdentity;
    private Client client;

    private CreditItem creditItem;
    private BigDecimal paymentSum;
    private int sumArrear;
    private Long accountNo;

    @Autowired
    private UserBusinessBean userBusinessBean;
    @Autowired
    private AccountBusinessBean accountBusinessBean;
    @Autowired
    private PaymentBusinessBean paymentBusinessBean;

    public BigDecimal getPaymentSum() {
        return paymentSum;
    }

    public void setPaymentSum(BigDecimal paymentSum) {
        this.paymentSum = paymentSum;
    }

    public Integer getSumArrear() {
        return sumArrear;
    }

    public void setSumArrear(Integer sumArrear) {
        this.sumArrear = sumArrear;
    }

    public Long getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(Long accountNo) {
        this.accountNo = accountNo;
    }

    public Long getClientIdentity() {
        return clientIdentity;
    }

    public void setClientIdentity(Long clientIdentity) throws SCDBusinessException {
        this.clientIdentity = clientIdentity;
        SCDUser user = userBusinessBean.getUserByIdentityId(clientIdentity);
        setClient(user.getClient());
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }


    public CreditItem getCreditItem() {
        return creditItem;
    }

    public void setCreditItem(CreditItem creditItem) {
        this.creditItem = creditItem;
    }

    public void loadCreditItem() throws SCDBusinessException {
        Account account = this.accountBusinessBean.getAccountByNumber(getAccountNo());
        CreditItem item = account.getCreditItem();
        setCreditItem(item);

        BigDecimal arrear = item.getCalculatedAmount().subtract(item.getPaidAmount()).subtract(item.getPenaltyAmount());
        setSumArrear(arrear.intValue());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void makePayment() throws SCDBusinessException {
        if (getPaymentSum() == null) {
            return;
        }

        Employee employee = FacesUtil.getUserContext().getEmployee();

        CreditItem item = paymentBusinessBean.makePayment(getCreditItem(), employee, getPaymentSum(), getSumArrear());
        setCreditItem(item);

        setPaymentSum(null);
    }


    @Autowired
    private ClientDataService clientDataService;

    @Transactional(propagation = Propagation.REQUIRED)
    public List<CreditItemResultRow> executeSearch() throws SCDBusinessException {
        Long clientId = getClient().getClientId();
        try {
            List<CreditItem> creditItems = clientDataService.getCreditItems(clientId, CreditItemStateEnum.ACTIVE);

            List<CreditItemResultRow> result = new ArrayList<CreditItemResultRow>(creditItems.size());
            for (CreditItem creditItem : creditItems) {
                result.add(new CreditItemResultRow(creditItem));
            }

            return result;

        } catch (SCDTechnicalException e) {
            LOGGER.error(e.getMessage());
            throw new SCDBusinessException("getActiveCreditItems", e);
        }
    }

    @Override
    public GridColumn[] getColumns() {
        return new GridColumn[]{
                GridColumn.ISSUENCE_DATE,
                GridColumn.CREDIT_NAME,
                GridColumn.ACCOUNT_NUMBER,
                GridColumn.SUM,
                GridColumn.TERM,
                GridColumn.SUM_TO_PAY,
                GridColumn.SUM_PAYED};
    }

}
