package com.xia.services.stub;

/**
 * Created by Andy on 2016/10/7.
 */
public class Verify {
    public static String verifyUser(String id,String pwd){
        if(id.equals("1")&&pwd.equals("1234"))
            return "true";
        else if(id.equals("2")&&pwd.equals("4321"))
            return "true";
        return "false";
    }
}
