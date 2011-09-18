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
@Table(name = "ROLES")
@SequenceGenerator(name = "SQ_ROLES", sequenceName = "SQ_ROLES")
public class Role extends  AbstractEntity{
    @Id
    @GeneratedValue(strategy =  GenerationType.SEQUENCE, generator = "SQ_ROLES")
    @Column(name = "ROLE_ID")
    private  Long roleId;

    @Column(name = "NAME")
    @Basic
    private String name;

    @OneToMany(mappedBy = "role")
    private List<SCDUser> users;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SCDUser> getUsers() {
        return users;
    }

    public void setUsers(List<SCDUser> users) {
        this.users = users;
    }

    @Override
    public Serializable getEntityId() {
        return getRoleId();
    }

    @Override
    public boolean entityEquals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Role role = (Role) o;

        if (name != null ? !name.equals(role.name) : role.name != null) return false;
        if (roleId != null ? !roleId.equals(role.roleId) : role.roleId != null) return false;
        if (users != null ? !users.equals(role.users) : role.users != null) return false;

        return true;
    }

    @Override
    public int entityHashCode() {
        int result = super.hashCode();
        result = 31 * result + (roleId != null ? roleId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (users != null ? users.hashCode() : 0);
        return result;
    }
}
