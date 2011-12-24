package by.kofi.scd.controller.calculator;

import by.kofi.scd.business.credit.CreditBusinessBean;
import by.kofi.scd.common.i18n.I18nSupport;
import by.kofi.scd.entity.Credit;
import by.kofi.scd.exceptions.SCDBusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.Bidi;
import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Admin
 * Date: 02.11.11
 * Time: 20:20
 * To change this template use File | Settings | File Templates.
 */
@Service("creditCalculatorController")
public class CreditCalculatorController {
    private BigDecimal creditSum = new BigDecimal(0);
    private Credit credit;
    private String creditName;
    private long term = 0;
    private boolean maxSumMode;
    private BigDecimal result = new BigDecimal(0);
    private BigDecimal everageMonthlyIncome = new BigDecimal(0);
    private static final BigDecimal con2 = new BigDecimal(2);
    private List<Credit> credits;
    private String mode = "0";

    public void isValidTerm(FacesContext facesContext, UIComponent uiComponent, Object o,
                                      String messageStr, Integer minLength) {
        validateStringLength(facesContext, uiComponent, o,
                I18nSupport.getText("registration.validator.stringMsg", 2),
                1);
    }

    public void isValidIncome(FacesContext facesContext, UIComponent uiComponent, Object o,
                                      String messageStr, Integer minLength) {
       validateStringLength(facesContext, uiComponent, o,
                I18nSupport.getText("registration.validator.stringMsg", 5),
                1);
    }

    public void isValidCreditSum(FacesContext facesContext, UIComponent uiComponent, Object o,
                                      String messageStr, Integer minLength) {
        validateStringLength(facesContext, uiComponent, o,
                I18nSupport.getText("registration.validator.stringMsg", 5),
                1);
    }

    private void validateStringLength(FacesContext facesContext, UIComponent uiComponent, Object o,
                                      String messageStr, Integer minLength) {
        String value = o != null ? o.toString() : "";

        if (value.length() < minLength) {
            ((UIInput) uiComponent).setValid(false);
            FacesMessage message = new FacesMessage(messageStr);
            facesContext.addMessage(uiComponent.getClientId(facesContext), message);
        }
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }

    public Credit getCredit() {
        return credit;
    }

    public String getCreditName() {
        return creditName;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }

    public void setCreditName(String creditName) throws SCDBusinessException {
        this.creditName = creditName;
        credit = creditBusinessBean.getCreditByName(creditName);
    }

    @Autowired
    private CreditBusinessBean creditBusinessBean;

    @PostConstruct
    public void init() throws SCDBusinessException {
        credits = creditBusinessBean.getCredits();
        credit = credits.get(0);
        creditName = credit.getName();
    }

    public void setCredits(List<Credit> credits) {
        this.credits = credits;
    }

    public List<Credit> getCredits() {
        return credits;
    }

    public void setEverageMonthlyIncome(BigDecimal everageMonthlyIncome) {
        this.everageMonthlyIncome = everageMonthlyIncome;
    }

    public BigDecimal getEverageMonthlyIncome() {
        return everageMonthlyIncome;
    }

    public BigDecimal getResult() {
        return result;
    }

    public void setResult(BigDecimal result) {
        this.result = result;
    }

    public void setCreditSum(BigDecimal creditSum) {
        this.creditSum = creditSum;
    }

    public void setTerm(long term) {
        this.term = term;
    }

    public void setMaxSumMode(boolean maxSumMode) {
        this.maxSumMode = maxSumMode;
    }

    public BigDecimal getCreditSum() {
        return creditSum;
    }

    public long getTerm() {
        return term;
    }

    public boolean isMaxSumMode() {
        return maxSumMode;
    }

    public String calculateAction() throws SCDBusinessException {
        BigDecimal temp = new BigDecimal(term);
        BigDecimal con600 = new BigDecimal(600);
        BigDecimal chislitel;
        BigDecimal znamenatel;
        BigDecimal kofficient;
        BigDecimal con1200 = new BigDecimal(1200);
        BigDecimal con100 = new BigDecimal(100);
        BigDecimal con1 = new BigDecimal(1);
        MathContext mathContext = new MathContext(2);

        if(mode.equals("0")){
            chislitel = everageMonthlyIncome.multiply(temp).multiply(con600);
            znamenatel = credit.getPercent().multiply(temp).add(con1200);
            kofficient = credit.getMaxSumPercent().add(con100).divide(con100,mathContext);
            result = chislitel.divide(znamenatel,mathContext).multiply(kofficient);
        } else if(mode.equals("1")){
            chislitel = con1.add(credit.getPercent().divide(con100,mathContext)).multiply(creditSum);
            znamenatel = temp;
            result = chislitel.divide(znamenatel, mathContext);
        }
        return "";
    }
}