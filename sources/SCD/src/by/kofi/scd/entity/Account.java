package by.kofi.scd.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * @author harchevnikov_m
 *         Date: 08/10/11
 *         Time: 23:08
 */
@Entity
@Table(name = "ACCOUNT")
@SequenceGenerator(name = "SQ_ACCOUNT", sequenceName = "SQ_ACCOUNT")
public class Account extends AbstractEntity {
    private long accountId;

    @javax.persistence.Column(name = "ACCOUNT_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ACCOUNT")
    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    private long accountNumber;

    @javax.persistence.Column(name = "ACCOUNT_NUMBER")
    @Basic
    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    private CreditRequest creditRequest;

    @OneToOne(mappedBy = "account")
    public CreditRequest getCreditRequest() {
        return creditRequest;
    }

    public void setCreditRequest(CreditRequest creditRequest) {
        this.creditRequest = creditRequest;
    }

    private CreditItem creditItem;

    @OneToOne(mappedBy = "account")
    public CreditItem getCreditItem() {
        return creditItem;
    }

    public void setCreditItem(CreditItem creditItem) {
        this.creditItem = creditItem;
    }

    private Set<Payment> payments;

    @OneToMany(mappedBy = "account")
    public Set<Payment> getPayments() {
        return payments;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }

    @Override
    public boolean entityEquals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Account account = (Account) o;

        if (accountId != account.accountId) return false;
        if (accountNumber != account.accountNumber) return false;
        if (creditRequest != null ? !creditRequest.equals(account.creditRequest) : account.creditRequest != null)
            return false;

        return true;
    }

    @Override
    public int entityHashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (accountId ^ (accountId >>> 32));
        result = 31 * result + (int) (accountNumber ^ (accountNumber >>> 32));
        result = 31 * result + (creditRequest != null ? creditRequest.hashCode() : 0);
        return result;
    }

    @Override
    @Transient
    public Serializable getEntityId() {
        return getAccountId();
    }
}
