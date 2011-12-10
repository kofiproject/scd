package by.kofi.scd.business;

import by.kofi.scd.dataservice.CRUDDataService;
import by.kofi.scd.dataservice.payment.PaymentDataService;
import by.kofi.scd.entity.*;
import by.kofi.scd.exceptions.SCDBusinessException;
import by.kofi.scd.exceptions.SCDTechnicalException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
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
    public CreditItem makePayment(CreditItem item, Employee employee, BigDecimal paymentSum, Integer arrearSum) throws SCDBusinessException {

        BigDecimal paidAmount = item.getPaidAmount();
        paidAmount = paidAmount.add(paymentSum);
        item.setPaidAmount(paidAmount);

        if (paymentSum.intValue() >= arrearSum) {
            item.setState(CreditItemStateEnum.PAYED);
        }

        Payment payment = new Payment();
        payment.setAccount(item.getAccount());
        payment.setAmount(paymentSum);
        payment.setClient(item.getClient());
        payment.setEmployee(employee);
        payment.setPaymentDate(new Date());

        try {

            CRUDDataService crudDataService = getCRUDDataService();
            crudDataService.merge(payment);
            return crudDataService.merge(item);
        } catch (SCDTechnicalException e) {
            throw new SCDBusinessException(e.getMessage(), e);
        }
    }
}