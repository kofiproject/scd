package by.kofi.scd.entity.generated;

import javax.persistence.*;

/**
 * @author harchevnikov_m
 *         Date: 08/10/11
 *         Time: 23:08
 */
@Entity
@Table(name = "EMPLOYEE", schema = "SCD")
@SequenceGenerator(name = "SQ_EMPLOYEE", sequenceName = "SQ_EMPLOYEE")
public class Employee {
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

    private long departmentId;

    @javax.persistence.Column(name = "DEPARTMENT_ID")
    @Basic
    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    private long roleId;

    @javax.persistence.Column(name = "ROLE_ID")
    @Basic
    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    private long employeeIdentityId;

    @javax.persistence.Column(name = "EMPLOYEE_IDENTITY_ID")
    @Basic
    @SequenceGenerator(name = "SQ_USER_IDENTITY", sequenceName = "SQ_USER_IDENTITY")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_USER_IDENTITY")
    public long getEmployeeIdentityId() {
        return employeeIdentityId;
    }

    public void setEmployeeIdentityId(long employeeIdentityId) {
        this.employeeIdentityId = employeeIdentityId;
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

    private String password;

    @javax.persistence.Column(name = "PASSWORD")
    @Basic
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (departmentId != employee.departmentId) return false;
        if (employeeId != employee.employeeId) return false;
        if (employeeIdentityId != employee.employeeIdentityId) return false;
        if (roleId != employee.roleId) return false;
        if (email != null ? !email.equals(employee.email) : employee.email != null) return false;
        if (middleName != null ? !middleName.equals(employee.middleName) : employee.middleName != null) return false;
        if (name != null ? !name.equals(employee.name) : employee.name != null) return false;
        if (password != null ? !password.equals(employee.password) : employee.password != null) return false;
        if (surname != null ? !surname.equals(employee.surname) : employee.surname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (employeeId ^ (employeeId >>> 32));
        result = 31 * result + (int) (departmentId ^ (departmentId >>> 32));
        result = 31 * result + (int) (roleId ^ (roleId >>> 32));
        result = 31 * result + (int) (employeeIdentityId ^ (employeeIdentityId >>> 32));
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        return result;
    }
}
