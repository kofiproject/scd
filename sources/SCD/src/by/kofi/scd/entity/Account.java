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

    private Set<CreditRequest> creditRequests;

    @OneToMany(mappedBy = "account")
    public Set<CreditRequest> getCreditRequests() {
        return creditRequests;
    }

    public void setCreditRequests(Set<CreditRequest> creditRequests) {
        this.creditRequests = creditRequests;
    }

    @Override
    public boolean entityEquals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Account account = (Account) o;

        if (accountId != account.accountId) return false;
        if (accountNumber != account.accountNumber) return false;
        if (creditRequests != null ? !creditRequests.equals(account.creditRequests) : account.creditRequests != null)
            return false;

        return true;
    }

    @Override
    public int entityHashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (accountId ^ (accountId >>> 32));
        result = 31 * result + (int) (accountNumber ^ (accountNumber >>> 32));
        result = 31 * result + (creditRequests != null ? creditRequests.hashCode() : 0);
        return result;
    }

    @Override
    @Transient
    public Serializable getEntityId() {
        return getAccountId();
    }
}
