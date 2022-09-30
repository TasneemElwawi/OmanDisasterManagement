package com.tasneem.omandisastermanagement.models;

public class VolunteerRequest extends volunteer{
    String status ;
    private String time;


    public VolunteerRequest() {
    }

    public VolunteerRequest(String email, String work, String carType, String skills, String status, String time) {
        super(email , work , carType , skills);
        this.status = status;
        this.time = time;
    }

    public VolunteerRequest(String email, String work, String carType, String skills) {
        super(email, work, carType, skills);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
