package by.kofi.scd.controller;

import by.kofi.scd.business.DepartmentBusinessBean;
import by.kofi.scd.exceptions.SCDBusinessException;
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
@Scope("request")
public class TestControllerBean {

    @Autowired
    private DepartmentBusinessBean departmentBusinessBean;

    private String name;

    public String getName() throws SCDBusinessException {
//        throw new SCDBusinessException("Error");//uncomment to test error page
        return departmentBusinessBean.processItems(new ArrayList<Long>(0));
    }

    public void setName(String name) {
        this.name = name;
    }
}
