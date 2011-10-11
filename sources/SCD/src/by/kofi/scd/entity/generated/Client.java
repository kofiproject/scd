package by.kofi.scd.entity.generated;

import by.kofi.scd.dto.registration.GenderEnum;
import by.kofi.scd.entity.AbstractEntity;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author harchevnikov_m
 *         Date: 08/10/11
 *         Time: 23:08
 */
@Entity
@Table(name = "CLIENT")
@SequenceGenerator(name = "SQ_CLIENT", sequenceName = "SQ_CLIENT")
public class Client extends AbstractEntity {
    private long userId;

    @javax.persistence.Column(name = "USER_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CLIENT")
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    private long userIdentityId;

    @javax.persistence.Column(name = "USER_IDENTITY_ID")
    @Basic
//    @SequenceGenerator(name = "SQ_USER_IDENTITY", sequenceName = "SQ_USER_IDENTITY")
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_USER_IDENTITY")
    public long getUserIdentityId() {
        return userIdentityId;
    }

    public void setUserIdentityId(long userIdentityId) {
        this.userIdentityId = userIdentityId;
    }

    private String email = "qwe@qw.qw";

    @javax.persistence.Column(name = "EMAIL")
    @Basic
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private boolean isBlocked;

    @javax.persistence.Column(name = "IS_BLOCKED")
    @Basic
    public boolean getBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    private String password = "123";

    @javax.persistence.Column(name = "PASSWORD")
    @Basic
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String passportSeries = "ab";

    @javax.persistence.Column(name = "PASSPORT_SERIES")
    @Basic
    public String getPassportSeries() {
        return passportSeries;
    }

    public void setPassportSeries(String passportSeries) {
        this.passportSeries = passportSeries;
    }

    private Long passportNo = 1222222222L;

    @javax.persistence.Column(name = "PASSPORT_NO")
    @Basic
    public Long getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(Long passportNo) {
        this.passportNo = passportNo;
    }

    private String name = "aaaaaaaaaaaa";

    @javax.persistence.Column(name = "NAME")
    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String middleName = "aaaaaaaaaaaaaaaaa";

    @javax.persistence.Column(name = "MIDDLE_NAME")
    @Basic
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    private String surname = "aaaaaaaaaaaaaaaa";

    @javax.persistence.Column(name = "SURNAME")
    @Basic
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    private Date birthday;

    @javax.persistence.Column(name = "BIRTHDAY")
    @Basic
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    private GenderEnum sex;

    @javax.persistence.Column(name = "SEX")
    @Enumerated
    public GenderEnum getSex() {
        return sex;
    }

    public void setSex(GenderEnum sex) {
        this.sex = sex;
    }

    private String permanentResidence = "aaaaaaaaaaaaaaaaaa";

    @javax.persistence.Column(name = "PERMANENT_RESIDENCE")
    @Basic
    public String getPermanentResidence() {
        return permanentResidence;
    }

    public void setPermanentResidence(String permanentResidence) {
        this.permanentResidence = permanentResidence;
    }

    private String currentResidence = "aaaaaaaaaaaaaaaaaaaaaaaaaa";

    @javax.persistence.Column(name = "CURRENT_RESIDENCE")
    @Basic
    public String getCurrentResidence() {
        return currentResidence;
    }

    public void setCurrentResidence(String currentResidence) {
        this.currentResidence = currentResidence;
    }

    private Long phone = 11111111L;

    @javax.persistence.Column(name = "PHONE")
    @Basic
    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    private Long phoneMobile = 11111111111L;

    @javax.persistence.Column(name = "PHONE_MOBILE")
    @Basic
    public Long getPhoneMobile() {
        return phoneMobile;
    }

    public void setPhoneMobile(Long phoneMobile) {
        this.phoneMobile = phoneMobile;
    }

    private BigDecimal monthlyCacheIncome;

    @javax.persistence.Column(name = "MONTHLY_CACHE_INCOME")
    @Basic
    public BigDecimal getMonthlyCacheIncome() {
        return monthlyCacheIncome;
    }

    public void setMonthlyCacheIncome(BigDecimal monthlyCacheIncome) {
        this.monthlyCacheIncome = monthlyCacheIncome;
    }

    private String jobPlace = "aaaaaaaaaaaaaaaaaaaaa";

    @javax.persistence.Column(name = "JOB_PLACE")
    @Basic
    public String getJobPlace() {
        return jobPlace;
    }

    public void setJobPlace(String jobPlace) {
        this.jobPlace = jobPlace;
    }

    private String jobPosition = "vvvvvvvvvvvvvvvvvvvvvv";

    @javax.persistence.Column(name = "JOB_POSITION")
    @Basic
    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
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

    @Override
    @Transient
    public Serializable getEntityId() {
        return getUserId();
    }


    @Override
    public boolean entityEquals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Client client = (Client) o;

        if (passportNo != client.passportNo) return false;
        if (phone != client.phone) return false;
        if (phoneMobile != client.phoneMobile) return false;
        if (userId != client.userId) return false;
        if (userIdentityId != client.userIdentityId) return false;
        if (birthday != null ? !birthday.equals(client.birthday) : client.birthday != null) return false;
        if (currentResidence != null ? !currentResidence.equals(client.currentResidence) : client.currentResidence != null)
            return false;
        if (email != null ? !email.equals(client.email) : client.email != null) return false;
        if (jobPlace != null ? !jobPlace.equals(client.jobPlace) : client.jobPlace != null) return false;
        if (jobPosition != null ? !jobPosition.equals(client.jobPosition) : client.jobPosition != null) return false;
        if (middleName != null ? !middleName.equals(client.middleName) : client.middleName != null) return false;
        if (monthlyCacheIncome != null ? !monthlyCacheIncome.equals(client.monthlyCacheIncome) : client.monthlyCacheIncome != null)
            return false;
        if (name != null ? !name.equals(client.name) : client.name != null) return false;
        if (passportSeries != null ? !passportSeries.equals(client.passportSeries) : client.passportSeries != null)
            return false;
        if (password != null ? !password.equals(client.password) : client.password != null) return false;
        if (permanentResidence != null ? !permanentResidence.equals(client.permanentResidence) : client.permanentResidence != null)
            return false;
        if (role != null ? !role.equals(client.role) : client.role != null) return false;
        if (sex != client.sex) return false;
        if (surname != null ? !surname.equals(client.surname) : client.surname != null) return false;

        return true;
    }

    @Override
    public int entityHashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (int) (userIdentityId ^ (userIdentityId >>> 32));
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (passportSeries != null ? passportSeries.hashCode() : 0);
        result = 31 * result + (int) (passportNo ^ (passportNo >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (permanentResidence != null ? permanentResidence.hashCode() : 0);
        result = 31 * result + (currentResidence != null ? currentResidence.hashCode() : 0);
        result = 31 * result + (int) (phone ^ (phone >>> 32));
        result = 31 * result + (int) (phoneMobile ^ (phoneMobile >>> 32));
        result = 31 * result + (monthlyCacheIncome != null ? monthlyCacheIncome.hashCode() : 0);
        result = 31 * result + (jobPlace != null ? jobPlace.hashCode() : 0);
        result = 31 * result + (jobPosition != null ? jobPosition.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }
}
