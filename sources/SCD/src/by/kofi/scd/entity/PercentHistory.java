package by.kofi.scd.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Book
 * Date: 01.01.12
 * Time: 20:38
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "PERCENT_HISTORY")
@SequenceGenerator(name = "SQ_PERCENT_HISTORY", sequenceName = "SQ_PERCENT_HISTORY")
public class PercentHistory extends AbstractEntity {
    private long percentHistoryId;

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PERCENT_HISTORY")
    public long getPercentHistoryId() {
        return percentHistoryId;
    }

    public void setPercentHistoryId(long percentHistoryId) {
        this.percentHistoryId = percentHistoryId;
    }

    private Date chargeDate;

    @Column(name = "charge_date")
    @Basic
    public Date getChargeDate() {
        return chargeDate;
    }

    public void setChargeDate(Date chargeDate) {
        this.chargeDate = chargeDate;
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

    private BigDecimal percentSum;

    @Column(name = "percent_sum")
    @Basic
    public BigDecimal getPercentSum() {
        return percentSum;
    }

    public void setPercentSum(BigDecimal percentSum) {
        this.percentSum = percentSum;
    }

    private BigDecimal debtSum;

    @Column(name = "debt_sum")
    @Basic
    public BigDecimal getDebtSum() {
        return debtSum;
    }

    public void setDebtSum(BigDecimal debtSum) {
        this.debtSum = debtSum;
    }

    @Override
    public boolean entityEquals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PercentHistory value = (PercentHistory) o;

        if (percentHistoryId != value.percentHistoryId) return false;
        if (chargeDate != value.chargeDate) return false;
        if (!creditItem.entityEquals(value.creditItem)) return false;
        if (percentSum != value.percentSum) return false;
        if (debtSum != value.debtSum) return false;

        return true;
    }

    @Override
    public int entityHashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (percentHistoryId ^ (percentHistoryId >>> 32));
        return result;
    }

    @Override
    @Transient
    public Serializable getEntityId() {
        return getPercentHistoryId();
    }
}
