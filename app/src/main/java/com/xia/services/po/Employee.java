package com.xia.services.po;

import java.util.Calendar;

/**
 * Created by Andy on 2016/10/10.
 */
public class Employee {
    private int id;
    private String name;
    private String idCard;//身份证号
    private int gender;//性别，0代表女，1代表男
    private Calendar birthday;
    private String department;
    private String category;
    private String password;

    public int getId() {
        return id;
    }




    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getIdCard() {
        return idCard;
    }
    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
    public int getGender() {
        return gender;
    }
    public void setGender(int gender) {
        this.gender = gender;
    }
    public Calendar getBirthday() {
        return birthday;
    }
    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
