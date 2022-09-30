package com.tasneem.omandisastermanagement.models;

import java.io.Serializable;

public class volunteer extends User implements Serializable {
    private String work ;
    private String carType ;
    private String skills ;


    public volunteer(String work, String carType, String skills) {
        this.work = work;
        this.carType = carType;
        this.skills = skills;
    }

    public volunteer() {
    }

    public volunteer(String email) {
        super(email);
    }

    public volunteer(String name ,String email,String phone, String work, String carType, String skills) {
        super(name, email , phone);
        this.work = work;
        this.carType = carType;
        this.skills = skills;
    }

    public volunteer(String email, String work, String carType, String skills) {
        super(email);
        this.work = work;
        this.carType = carType;
        this.skills = skills;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

}
