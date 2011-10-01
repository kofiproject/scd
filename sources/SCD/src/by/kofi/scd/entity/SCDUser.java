package by.kofi.scd.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author harchevnikov_m
 *         Date: 18.09.11
 *         Time: 18:28
 */

@Entity
@Table(name = "USERS")
@SequenceGenerator(name = "SQ_USERS", sequenceName = "SQ_USERS")
public class SCDUser extends  AbstractEntity{

    @Id
    @GeneratedValue(strategy =  GenerationType.SEQUENCE, generator = "SQ_USERS")
    @Column(name = "USER_ID")
    private Long userId;

    @Basic
    @Column(name = "LOGIN", unique = true)
    private String login;

    @Basic
    @Column(name = "PASSWORD")
    private String password;

    @ManyToOne
    @JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID")
    private Role role;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public Serializable getEntityId() {
        return getUserId();
    }

    @Override
    public boolean entityEquals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        SCDUser scdUser = (SCDUser) o;

        if (login != null ? !login.equals(scdUser.login) : scdUser.login != null) return false;
        if (password != null ? !password.equals(scdUser.password) : scdUser.password != null) return false;
        if (role != null ? !role.equals(scdUser.role) : scdUser.role != null) return false;
        if (userId != null ? !userId.equals(scdUser.userId) : scdUser.userId != null) return false;

        return true;
    }

    @Override
    public int entityHashCode() {
        int result = super.hashCode();
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }
}
