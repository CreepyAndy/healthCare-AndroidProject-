package com.xia.services;

import android.util.Log;

import com.xia.services.stub.*;

import org.junit.Test;

/**
 * Created by Andy on 2016/10/5.
 */
public class VerifyTest {
    @Test
    public void testQueryPersonalInfoById(){

        System.out.println(com.xia.services.Verify.verifyUser("100","4321"));
    }
    @Test
    public void testUpdateBasicIndexById(){
        Query.updateBasicIndexById("1","123","46","51","42");
    }
    @Test
    public void testCreateAccount(){
        Verify.createAccount("54","aaa","1234","1994","4","3");
    }
}