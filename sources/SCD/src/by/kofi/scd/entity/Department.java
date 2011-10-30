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
@Table(name = "DEPARTMENT")
@SequenceGenerator(name = "SQ_DEPARTMENT", sequenceName = "SQ_DEPARTMENT")
public class Department extends AbstractEntity{
    private long departmentId;

    @javax.persistence.Column(name = "DEPARTMENT_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_DEPARTMENT")
    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
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

    private String address;

    @javax.persistence.Column(name = "ADDRESS")
    @Basic
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean entityEquals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Department that = (Department) o;

        if (departmentId != that.departmentId) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (departmentNo != null ? !departmentNo.equals(that.departmentNo) : that.departmentNo != null) return false;

        return true;
    }

    @Override
    public int entityHashCode() {
        int result = (int) (departmentId ^ (departmentId >>> 32));
        result = 31 * result + (departmentNo != null ? departmentNo.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    private Set<Employee> employees;

    @OneToMany(mappedBy = "department")
    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

      @Override
    @Transient
    public Serializable getEntityId() {
        return getDepartmentId();
    }
}
