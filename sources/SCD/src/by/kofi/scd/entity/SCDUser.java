package by.kofi.scd.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author harchevnikov_m
 *         Date: 08/10/11
 *         Time: 23:08
 */
@Entity
@Table(name = "SCD_USER")
@SequenceGenerator(name = "SQ_USER", sequenceName = "SQ_USER")
public class SCDUser extends AbstractEntity {
    private long userId;


    @Column(name = "SCD_USER_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_USER")
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }


    private String password;

    @Column(name = "PASSWORD")
    @Basic
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private Role role;

    @ManyToOne
    @JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID")
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    private Client client;

    @OneToOne(mappedBy = "user")
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    private Employee employee;

    @OneToOne(mappedBy = "user")
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }


    @Override
    public boolean entityEquals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        SCDUser user = (SCDUser) o;

        if (userId != user.userId) return false;
        if (client != null ? !client.equals(user.client) : user.client != null) return false;
        if (employee != null ? !employee.equals(user.employee) : user.employee != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (role != null ? !role.equals(user.role) : user.role != null) return false;

        return true;
    }

    @Override
    public int entityHashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (client != null ? client.hashCode() : 0);
        result = 31 * result + (employee != null ? employee.hashCode() : 0);
        return result;
    }

    @Override
    @Transient
    public Serializable getEntityId() {
        return getUserId();
    }

}
