package by.kofi.scd.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author harchevnikov_m
 *         Date: 08/10/11
 *         Time: 23:08
 */
@Entity
@Table(name = "EMPLOYEE", schema = "SCD")
@SequenceGenerator(name = "SQ_EMPLOYEE", sequenceName = "SQ_EMPLOYEE")
public class Employee extends AbstractEntity {
    private long employeeId;

    @javax.persistence.Column(name = "EMPLOYEE_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_EMPLOYEE")
    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    private Department department;

    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_ID", referencedColumnName = "DEPARTMENT_ID")
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }


    private String email;

    @javax.persistence.Column(name = "EMAIL")
    @Basic
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String name;

    @javax.persistence.Column(name = "NAME")
    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String middleName;

    @javax.persistence.Column(name = "MIDDLE_NAME")
    @Basic
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    private String surname;

    @javax.persistence.Column(name = "SURNAME")
    @Basic
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    private SCDUser user;

    @OneToOne
    @JoinColumn(name = "SCD_USER_ID", referencedColumnName = "SCD_USER_ID")
    public SCDUser getUser() {
        return user;
    }

    public void setUser(SCDUser user) {
        this.user = user;
    }

    @Override
    public boolean entityEquals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Employee employee = (Employee) o;

        if (employeeId != employee.employeeId) return false;
        if (department != null ? !department.equals(employee.department) : employee.department != null) return false;
        if (email != null ? !email.equals(employee.email) : employee.email != null) return false;
        if (middleName != null ? !middleName.equals(employee.middleName) : employee.middleName != null) return false;
        if (name != null ? !name.equals(employee.name) : employee.name != null) return false;
        if (surname != null ? !surname.equals(employee.surname) : employee.surname != null) return false;
        if (user != null ? !user.equals(employee.user) : employee.user != null) return false;

        return true;
    }

    @Override
    public int entityHashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (employeeId ^ (employeeId >>> 32));
        result = 31 * result + (department != null ? department.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    @Override
    @Transient
    public Serializable getEntityId() {
        return getEmployeeId();
    }
}
