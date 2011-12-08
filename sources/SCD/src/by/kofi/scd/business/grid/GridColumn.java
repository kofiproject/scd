package by.kofi.scd.business.grid;

/**
 * Result row class properties
 *
 * @author harchevnikov_m
 *         Date: 25/10/11
 *         Time: 23:44
 */
public enum GridColumn {
    ISSUENCE_DATE("issuanceDate", "grid.client.credits.date", true),
    CREDIT_NAME("creditName", "grid.client.credits.credit", true, false),
    CLIENT_NAME("clientName", "grid.bank.client", false, true),
    ACCOUNT_NUMBER("accountNumber", "grid.client.credits.accountNo"),
    SUM("amount", "grid.client.credits.sum"),
    TERM("term", "grid.client.credits.term"),
    SUM_TO_PAY("calculatedAmount", "grid.client.credits.sumToPay"),
    SUM_PAYED("paidAmount", "grid.client.credits.sumPayed"),

    PROCESSING_DATE("processingDate", "grid.client.request.processingDate", true),
    DESCRIPTION("description", "grid.client.request.description"),
    CREDIT_EXPERT("expert", "grid.client.request.expert"),

    CLIENT_IDENTITY_NO("identityNo", "grid.client.identity"),
    CLIENT_REGISTRATION_DATE("registrationDate", "grid.client.registrationDate", true),
    CLIENT_PASSPORT("passport", "grid.client.passport"),
    CLIENT_SEX("sex", "grid.client.sex"),
    CLIENT_PERMANENT_RESIDENCE("permanentResidence", "grid.client.permanentResidence"),
    CLIENT_CURRENT_RESIDENCE("currentResidence", "grid.client.currentResidence"),
    CLIENT_PHONE_MOBILE("phoneMobile", "grid.client.mobile"),
    CLIENT_PHONE("phone", "grid.client.phone"),
    CLIENT_EMAIL("email", "grid.client.email"),
    CLIENT_BIRTHDAY("birthday", "grid.client.birthday", true),
    CLIENT_JOB_PLACE("jobPlace", "grid.client.jobPlace"),
    CLIENT_JOB_POSITION("jobPosition", "grid.client.jobPosition"),

    CREDIT_DETAILS_NAME("name", "grid.credit.name", true, false),
    CREDIT_PERCENT("percent", "grid.credit.percent"),
    CREDIT_PENALTY_PERCENT("penaltyPercent", "grid.credit.penaltyPercent"),
    CREDIT_MAX_TERM("maxTerm", "grid.credit.maxTerm");

    private String text;
    private String header;
    private boolean creditField;
    private boolean clientField;
    private boolean dateField;

    private GridColumn(String text, String header) {
        this.text = text;
        this.header = header;
    }

    private GridColumn(String text, String header, boolean dateField) {
        this(text, header);
        this.dateField = dateField;
    }

    private GridColumn(String text, String header, boolean creditField, boolean clientField) {
        this(text, header);
        this.creditField = creditField;
        this.clientField = clientField;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean getCreditField() {
        return creditField;
    }

    public void setCreditField(boolean creditField) {
        this.creditField = creditField;
    }

    public boolean getClientField() {
        return clientField;
    }

    public void setClientField(boolean clientField) {
        this.clientField = clientField;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public boolean isDateField() {
        return dateField;
    }

    public void setDateField(boolean dateField) {
        this.dateField = dateField;
    }
}
