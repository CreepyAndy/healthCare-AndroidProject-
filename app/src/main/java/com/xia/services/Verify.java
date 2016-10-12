package com.xia.services;


import android.util.Log;

import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.util.Calendar;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

/**
 * Created by Andy on 2016/10/6.
 */
public class Verify {
    public static String WSDL_URI = "http://172.25.65.60:8080/healthServerWeb/verify?wsdl";//wsdl 的uri
    public static String namespace = "http://services.xia.com/";//namespace
    public static String methodName = "verifyUser";//要调用的方法名称
    public static String verifyUser(String id,String pwd) {
        SoapObject request = new SoapObject(namespace, methodName);
        // 设置需调用WebService接口需要传入的两个参数mobileCode、userId
        request.addProperty("arg0",id);
        request.addProperty("arg1",pwd);
        //创建SoapSerializationEnvelope 对象，同时指定soap版本号(之前在wsdl中看到的)
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapSerializationEnvelope.VER11);
        envelope.bodyOut = request;//由于是发送请求，所以是设置bodyOut
        //envelope.dotNet = true;//由于是.net开发的webservice，所以这里要设置为true
        HttpTransportSE httpTransportSE = new HttpTransportSE(WSDL_URI);
        try {
            httpTransportSE.call(namespace+methodName, envelope);//调用
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        // 获取返回的数据
        SoapObject object = (SoapObject) envelope.bodyIn;
        // 获取返回的结果
        String result = object.getProperty(0).toString();
        return result;
    }
    public static String createAccount(String id, String name, String pwd, String year,String month,String day) {
        String methodName="createUser";
        SoapObject request = new SoapObject(namespace, methodName);
        // 设置需调用WebService接口需要传入的两个参数mobileCode、userId

        request.addProperty("arg0",id);
        request.addProperty("arg1",name);
        request.addProperty("arg2",pwd);
        request.addProperty("arg3",year);
        request.addProperty("arg4",month);
        request.addProperty("arg5",day);
        //创建SoapSerializationEnvelope 对象，同时指定soap版本号(之前在wsdl中看到的)
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapSerializationEnvelope.VER11);
        envelope.bodyOut = request;//由于是发送请求，所以是设置bodyOut
        //envelope.dotNet = true;//由于是.net开发的webservice，所以这里要设置为true
        HttpTransportSE httpTransportSE = new HttpTransportSE(WSDL_URI);
        try {
            httpTransportSE.call(namespace+methodName, envelope);//调用
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return "ok";
    }
}
