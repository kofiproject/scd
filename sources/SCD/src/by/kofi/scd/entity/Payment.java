package by.kofi.scd.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 * @author harchevnikov_m
 *         Date: 08/10/11
 *         Time: 23:08
 */
@Entity
@Table(name = "PAYMENT")
@SequenceGenerator(name = "SQ_PAYMENT", sequenceName = "SQ_PAYMENT")
public class Payment extends AbstractEntity {
    private long paymentId;

    @Column(name = "PAYMENT_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PAYMENT")
    public long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(long paymentId) {
        this.paymentId = paymentId;
    }

    private Date paymentDate;

    @Column(name = "PAYMENT_DATE")
    @Basic
    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    private BigDecimal amount;

    @Column(name = "AMOUNT")
    @Basic
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    private Client client;


    @ManyToOne
    @JoinColumn(name = "CLIENT_ID", referencedColumnName = "CLIENT_ID")
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    private Account account;

    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID", referencedColumnName = "ACCOUNT_ID")
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_ID", referencedColumnName = "EMPLOYEE_ID")
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    private CreditItem creditItem;

    @ManyToOne
    @JoinColumn(name = "CREDIT_ITEM_ID", referencedColumnName = "CREDIT_ITEM_ID")
    public CreditItem getCreditItem() {
        return creditItem;
    }

    public void setCreditItem(CreditItem creditItem) {
        this.creditItem = creditItem;
    }

    @Override
    public boolean entityEquals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Payment payment = (Payment) o;

        if (paymentId != payment.paymentId) return false;
        if (account != null ? !account.equals(payment.account) : payment.account != null) return false;
        if (amount != null ? !amount.equals(payment.amount) : payment.amount != null) return false;
        if (client != null ? !client.equals(payment.client) : payment.client != null) return false;
        if (employee != null ? !employee.equals(payment.employee) : payment.employee != null) return false;
        if (paymentDate != null ? !paymentDate.equals(payment.paymentDate) : payment.paymentDate != null) return false;

        return true;
    }

    @Override
    public int entityHashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (paymentId ^ (paymentId >>> 32));
        result = 31 * result + (paymentDate != null ? paymentDate.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (client != null ? client.hashCode() : 0);
        result = 31 * result + (account != null ? account.hashCode() : 0);
        result = 31 * result + (employee != null ? employee.hashCode() : 0);
        return result;
    }

    @Override
    @Transient
    public Serializable getEntityId() {
        return getPaymentId();
    }
}
