package by.kofi.scd.business.grid;

/**
 * Grid header bundle keys enum
 *
 * @author harchevnikov_m
 *         Date: 25/10/11
 *         Time: 23:44
 */
public enum GridHeader {
    ISSUENCE_DATE("grid.client.credits.date"),
    CREDIT_NAME("grid.client.credits.credit"),
    ACCOUNT_NUMBER("grid.client.credits.accountNo"),
    SUM("grid.client.credits.sum"),
    TERM("grid.client.credits.term"),
    SUM_TO_PAY("grid.client.credits.sumToPay"),
    SUM_PAYED("grid.client.credits.sumPayed");

    private String text;

    private GridHeader(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


}
