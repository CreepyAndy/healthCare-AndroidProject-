package com.xia.services;

import com.xia.services.po.Employee;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andy on 2016/10/8.
 */
public class Query {
    public static String WSDL_URI = "http://172.25.65.60:8080/healthServerWeb/queryInfo?wsdl";//wsdl 的uri
    public static String namespace = "http://services.xia.com/";//namespace

    public static SoapObject queryBasicIndexById(String id) {
        String methodName = "queryBasicIndexById";//要调用的方法名称
        SoapObject request = new SoapObject(namespace, methodName);
        // 设置需调用WebService接口需要传入的两个参数mobileCode、userId

        request.addProperty("arg0",id);

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
        // String result = object.getProperty(0).toString();
        SoapObject sObject = (SoapObject) object.getProperty(0);
        //String result = object.toString();
        return sObject;
    }


    public static void updateBasicIndexById(String id, String heartRate,
                                                  String height, String oxygen, String weight) {
        String methodName = "updateBasicIndexById";//要调用的方法名称
        SoapObject request = new SoapObject(namespace, methodName);
        // 设置需调用WebService接口需要传入的两个参数mobileCode、userId
        request.addProperty("arg0",id);
        request.addProperty("arg1",heartRate);
        request.addProperty("arg2",height);
        request.addProperty("arg3",oxygen);
        request.addProperty("arg4",weight);
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
    }

    public static List<Employee> queryAllPersonalInfo(){
        List<Employee> employees = new ArrayList<Employee>();
        String methodName = "queryPersonalInfoById";//要调用的方法名称
        SoapObject request = new SoapObject(namespace, methodName);
        // 设置需调用WebService接口需要传入的两个参数mobileCode、userId
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
        SoapObject objects = (SoapObject) envelope.bodyIn;

        // 获取返回的结果

        //SoapObject sObject = (SoapObject) object.getProperty(0);


        for (int i = 0; i < objects.getPropertyCount(); i++) {
            SoapObject sEmployee = (SoapObject)objects.getProperty(i);
            Employee e = new Employee();
            e.setId(Integer.parseInt(sEmployee.getProperty("id").toString()));
            e.setName(sEmployee.getProperty("name").toString());
            e.setPassword(sEmployee.getProperty("password").toString());

            employees.add(e);
        }
        return employees;
    }

    public static List<String> parseBasicInfo(SoapObject detail) {
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < detail.getPropertyCount(); i++) {
            // 解析出每个省份
            result.add(detail.getProperty(i).toString());
        }
        return result;
    }
}
