package by.kofi.scd.entity.generated;

import javax.persistence.*;

/**
 * @author harchevnikov_m
 *         Date: 08/10/11
 *         Time: 23:08
 */
@Entity
@Table(name = "ACCOUNT")
@SequenceGenerator(name = "SQ_ACCOUNT", sequenceName = "SQ_ACCOUNT")
public class Account {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (accountId != account.accountId) return false;
        if (accountNumber != account.accountNumber) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (accountId ^ (accountId >>> 32));
        result = 31 * result + (int) (accountNumber ^ (accountNumber >>> 32));
        return result;
    }
}
