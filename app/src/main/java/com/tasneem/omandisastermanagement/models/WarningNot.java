package com.tasneem.omandisastermanagement.models;

public class WarningNot {

    private String id;
    private String senderName;
    private String email ;
    private String lat_desc;
    private String long_desc;
    private String time;

    public WarningNot(String email,String senderName, String lat_desc, String long_desc) {
        this.senderName = senderName;
        this.email = email;
        this.lat_desc = lat_desc;
        this.long_desc = long_desc;
    }

    public WarningNot(String senderName, String email, String lat_desc, String long_desc, String time) {
        this.senderName = senderName;
        this.email = email;
        this.lat_desc = lat_desc;
        this.long_desc = long_desc;
        this.time = time;
    }

    public WarningNot(String ID ,String senderName, String email, String lat_desc, String long_desc, String time) {
        this.id = ID;
        this.senderName = senderName;
        this.email = email;
        this.lat_desc = lat_desc;
        this.long_desc = long_desc;
        this.time = time;
    }

    public WarningNot() {
    }

    public String getUserName() {
        return senderName;
    }

    public void setUserName(String userName) {
        this.senderName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
