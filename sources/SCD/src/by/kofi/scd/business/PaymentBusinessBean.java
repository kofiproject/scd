package by.kofi.scd.business;

import by.kofi.scd.dataservice.payment.PaymentDataService;
import by.kofi.scd.entity.Account;
import by.kofi.scd.entity.CreditItem;
import by.kofi.scd.entity.CreditItemStateEnum;
import by.kofi.scd.entity.Payment;
import by.kofi.scd.exceptions.SCDBusinessException;
import by.kofi.scd.exceptions.SCDTechnicalException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;


@Service
public class PaymentBusinessBean extends AbstractBusinessBean {

    private static final Logger LOGGER = Logger.getLogger(PaymentBusinessBean.class);

    @Autowired
    private PaymentDataService paymentDataService;

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Payment> getPaymentsByAccount(Long accountNumber) throws SCDBusinessException {
        try {
            return paymentDataService.getPaymentsByAccount(accountNumber);
        } catch (SCDTechnicalException e) {
            throw new SCDBusinessException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public CreditItem makePayment(CreditItem item, BigDecimal paymentSum, Integer arrearSum) throws SCDBusinessException {

        BigDecimal paidAmount = item.getPaidAmount();
        paidAmount = paidAmount.add(paymentSum);
        item.setPaidAmount(paidAmount);

        if (paymentSum.intValue() >= arrearSum) {
            item.setState(CreditItemStateEnum.PAYED);
        }

        try {
            return getCRUDDataService().merge(item);
        } catch (SCDTechnicalException e) {
            throw new SCDBusinessException(e.getMessage(), e);
        }
    }
}