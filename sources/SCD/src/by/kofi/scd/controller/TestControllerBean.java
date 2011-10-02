package by.kofi.scd.controller;

import by.kofi.scd.business.DepartmentBusinessBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;

/**
 * @author harchevnikov_m
 *         Date: 26.09.11
 *         Time: 22:34
 */
@Controller("testControllerBean")
@Scope("session")
public class TestControllerBean {

    @Autowired
    private DepartmentBusinessBean departmentBusinessBean;

    public DepartmentBusinessBean getDepartmentBusinessBean() {
        return departmentBusinessBean;
    }

    public void setDepartmentBusinessBean(DepartmentBusinessBean departmentBusinessBean) {
        this.departmentBusinessBean = departmentBusinessBean;
    }

    private String name;

    public String getName() {
        return getDepartmentBusinessBean().processItems(new ArrayList<Long>(0));
    }

    public void setName(String name) {
        this.name = name;
    }
}
