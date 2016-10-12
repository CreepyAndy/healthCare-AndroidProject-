package com.xia.services.stub;

import com.xia.services.po.Employee;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andy on 2016/10/10.
 */
public class Query {
    public static List<Employee> queryAllPersonalInfo(){
        List<Employee> employees = new ArrayList<Employee>();
        Employee e = new Employee();
        e.setId(1);e.setName("Andy");e.setPassword("1234");
        employees.add(e);
        e.setId(2);e.setName("Wangrun");e.setPassword("1234");
        employees.add(e);
        return  employees;
    };
}
