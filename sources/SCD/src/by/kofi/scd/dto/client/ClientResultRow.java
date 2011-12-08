package by.kofi.scd.dto.client;

import by.kofi.scd.business.grid.ResultRow;
import by.kofi.scd.entity.Client;
import by.kofi.scd.entity.CreditItem;

/**
 * @author harchevnikov_m
 *         Date: 25/10/11
 *         Time: 22:50
 */
public class ClientResultRow extends Client implements ResultRow {
    private Long identityNo;
    private String passport;
    private String clientName;

    public ClientResultRow(Client client) {
        setClientId(client.getClientId());
        setIdentityNo(client.getUser().getUserId());
        setRegistrationDate(client.getRegistrationDate());
        setPassport(client.getPassportSeries() + " " + client.getPassportNo());
        setSex(client.getSex());
        setPermanentResidence(client.getPermanentResidence());
        setCurrentResidence(client.getCurrentResidence());
        setPhoneMobile(client.getPhoneMobile());
        setPhone(client.getPhone());
        setEmail(client.getEmail());
        setBirthday(client.getBirthday());
        setJobPlace(client.getJobPlace());
        setJobPosition(client.getJobPosition());

        setClientName(client.toString());
    }

    public Long getIdentityNo() {
        return identityNo;
    }

    public void setIdentityNo(Long identityNo) {
        this.identityNo = identityNo;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    @Override
    public Long getRowId() {
        return getClientId();
    }

    @Override
    public long getCreditId() {
        return -1L;
    }
}
