package by.kofi.scd.dto;

import by.kofi.scd.entity.Client;
import by.kofi.scd.entity.Employee;
import by.kofi.scd.entity.Role;
import by.kofi.scd.entity.SCDUser;

/**
 * User context
 *
 * @author harchevnikov_m
 *         Date: 16/10/11
 *         Time: 16:23
 */
public class UserContext extends AbstractModel {
    private Client client;
    private Employee employee;

    public UserContext(Client client, Employee employee) {
        this.client = client;
        this.employee = employee;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        Client client = getClient();
        Employee employee = getEmployee();

        if(client == null && employee == null) {
            return "";
        }

        String name = client != null ? client.getName() : employee.getName();
        String surName = client != null ? client.getSurname() : employee.getSurname();
        String middleName = client != null ? client.getMiddleName() : employee.getMiddleName();

        return surName + ' ' + name + ' ' + (middleName == null? "" : middleName);
    }

    public String getEmail() {
        Client client = getClient();
        Employee employee = getEmployee();

        return client != null ? client.getEmail() : employee.getEmail();
    }
}


