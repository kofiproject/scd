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
@Table(name = "ROLE", schema = "SCD")
@SequenceGenerator(name = "SQ_ROLE", sequenceName = "SQ_ROLE")
public class Role extends AbstractEntity {
    private long roleId;

    @javax.persistence.Column(name = "ROLE_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ROLE")
    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
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

    private Set<SCDUser> users;

    @OneToMany(mappedBy = "role")
    public Set<SCDUser> getUsers() {
        return users;
    }

    public void setUsers(Set<SCDUser> users) {
        this.users = users;
    }


    @Override
    public boolean entityEquals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Role role = (Role) o;

        if (roleId != role.roleId) return false;
        if (name != null ? !name.equals(role.name) : role.name != null) return false;

        return true;
    }

    @Override
    public int entityHashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (roleId ^ (roleId >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    @Transient
    public Serializable getEntityId() {
        return getRoleId();
    }
}
