package by.kofi.scd.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author harchevnikov_m
 *         Date: 18.09.11
 *         Time: 18:03
 */
@Entity
@Table(name = "DEPARTMENT")
@SequenceGenerator(name = "SQ_DEPARTMENT", sequenceName = "SQ_DEPARTMENT")
public class Department extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_DEPARTMENT")
    @Column(name = "DEPARTMENT_ID")
    private Long departmentId;

    @Column(name = "DEPARTMENT_NO")
    @Basic
    private String departmentNo;

    @Column(name = "ADDRESS")
    @Basic
    private String address;

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentNo() {
        return departmentNo;
    }

    public void setDepartmentNo(String departmentNo) {
        this.departmentNo = departmentNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public Serializable getEntityId() {
        return getDepartmentId();
    }

    @Override
    public boolean entityEquals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Department that = (Department) o;

        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (departmentId != null ? !departmentId.equals(that.departmentId) : that.departmentId != null) return false;
        if (departmentNo != null ? !departmentNo.equals(that.departmentNo) : that.departmentNo != null) return false;

        return true;
    }

    @Override
    public int entityHashCode() {
        int result = super.hashCode();
        result = 31 * result + (departmentId != null ? departmentId.hashCode() : 0);
        result = 31 * result + (departmentNo != null ? departmentNo.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

}
