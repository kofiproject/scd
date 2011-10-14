package by.kofi.scd.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

/**
 * @author harchevnikov_m
 *         Date: 08/10/11
 *         Time: 23:08
 */
@Entity
@Table(name = "CREDIT")
@SequenceGenerator(name = "SQ_CREDIT", sequenceName = "SQ_CREDIT")
public class Credit extends AbstractEntity{
    private long creditId;

    @javax.persistence.Column(name = "CREDIT_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CREDIT")
    public long getCreditId() {
        return creditId;
    }

    public void setCreditId(long creditId) {
        this.creditId = creditId;
    }

    private String departmentNo;

    @javax.persistence.Column(name = "DEPARTMENT_NO")
    @Basic
    public String getDepartmentNo() {
        return departmentNo;
    }

    public void setDepartmentNo(String departmentNo) {
        this.departmentNo = departmentNo;
    }

    private String description;

    @javax.persistence.Column(name = "DESCRIPTION")
    @Lob
    @Basic
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private BigDecimal percent;

    @javax.persistence.Column(name = "PERCENT")
    @Basic
    public BigDecimal getPercent() {
        return percent;
    }

    public void setPercent(BigDecimal percent) {
        this.percent = percent;
    }

    private BigDecimal penaltyPercent;

    @javax.persistence.Column(name = "PENALTY_PERCENT")
    @Basic
    public BigDecimal getPenaltyPercent() {
        return penaltyPercent;
    }

    public void setPenaltyPercent(BigDecimal penaltyPercent) {
        this.penaltyPercent = penaltyPercent;
    }

    @Override
    public boolean entityEquals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Credit credit = (Credit) o;

        if (creditId != credit.creditId) return false;
        if (penaltyPercent != credit.penaltyPercent) return false;
        if (departmentNo != null ? !departmentNo.equals(credit.departmentNo) : credit.departmentNo != null)
            return false;
        if (description != null ? !description.equals(credit.description) : credit.description != null) return false;
        if (percent != null ? !percent.equals(credit.percent) : credit.percent != null) return false;

        return true;
    }

    @Override
    public int entityHashCode() {
        int result = (int) (creditId ^ (creditId >>> 32));
        result = 31 * result + (departmentNo != null ? departmentNo.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (percent != null ? percent.hashCode() : 0);
        result = 31 * result + (penaltyPercent != null ? penaltyPercent.hashCode() : 0);
        return result;
    }

    private Set<CreditRequest> creditRequests;

    @OneToMany(mappedBy = "credit")
    public Set<CreditRequest> getCreditRequests() {
        return creditRequests;
    }

    public void setCreditRequests(Set<CreditRequest> creditRequests) {
        this.creditRequests = creditRequests;
    }

    @Override
    @Transient
    public Serializable getEntityId() {
        return getCreditId();
    }
}
