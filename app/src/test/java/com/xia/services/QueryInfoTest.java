package com.xia.services;

import com.xia.services.po.Employee;

import org.junit.Test;
import org.ksoap2.serialization.SoapObject;

import java.util.List;

/**
 * Created by Andy on 2016/10/5.
 */
public class QueryInfoTest {
    @Test
    public void queryBasics(){
        SoapObject detail = Query.queryBasicIndexById("1");
        List<String> result = Query.parseBasicInfo(detail);
        for (int i = 1; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
    }
    @Test
    public  void updateBasics(){
        Query.updateBasicIndexById("107","90","172","95","55");
    }
    @Test
    public void testGetAll(){

        List<Employee> result = Query.queryAllPersonalInfo();
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i).getName().toString());
            System.out.println(result.get(i).getPassword().toString());
            System.out.println(result.get(i).getId());
        }
    }
}
