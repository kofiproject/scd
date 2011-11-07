package by.kofi.scd.business.grid;

/**
 * Result row class properties
 *
 * @author harchevnikov_m
 *         Date: 25/10/11
 *         Time: 23:44
 */
public enum ResultRowField {
    ISSUENCE_DATE("issuanceDate"),
    CREDIT_NAME("creditName"),
    ACCOUNT_NUMBER("accountNumber"),
    SUM("amount"),
    TERM("term"),
    SUM_TO_PAY("calculatedAmount"),
    SUM_PAYED("paidAmount"),

    PROCESSING_DATE("processingDate"),
    DESCRIPTION("description"),
    CREDIT_EXPERT("expert");

    private String text;

    private ResultRowField(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
