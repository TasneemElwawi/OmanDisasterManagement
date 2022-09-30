package com.tasneem.omandisastermanagement.models;

import java.io.Serializable;

public class User implements Serializable {

    private String ID ;
    private String fullName ;
    private String phone ;
    private String email ;
    private String gender ;
    private String age ;
//    private String agee ;
    private String lat_desc;
    private String long_desc;

    public User() {
    }

    public User(String fullName, String phone, String email, String gender, String age, String lat_desc, String long_desc) {
        this.ID = ID;
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.age = age;
        this.lat_desc = lat_desc;
        this.long_desc = long_desc;
    }


//    public User(String phone, String fullName, String email, String gender, String age, String lat_desc, String long_desc) {
//        this.ID = ID;
//        this.fullName = fullName;
//        this.phone = phone;
//        this.email = email;
//        this.gender = gender;
//        this.agee = age;
//        this.lat_desc = lat_desc;
//        this.long_desc = long_desc;
//    }



    public User(String age, String email,String fullName,  String gender, String id , String lat_desc, String long_desc, String phone) {
        this.ID = id;
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.age = age;
        this.lat_desc = lat_desc;
        this.long_desc = long_desc;
    }

//    public User( String age, String email) {
//        this.email = email;
//        this.age = age;
//    }

    public User(String name, String email , String phone)
    {
        this.fullName = name;
        this.email = email;
        this.phone = phone;
    }
    public User(String email)
    {
        this.email = email;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getLat_desc() {
        return lat_desc;
    }

    public void setLat_desc(String lat_desc) {
        this.lat_desc = lat_desc;
    }

    public String getLong_desc() {
        return long_desc;
    }

    public void setLong_desc(String long_desc) {
        this.long_desc = long_desc;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
