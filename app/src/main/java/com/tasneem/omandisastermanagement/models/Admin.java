package com.tasneem.omandisastermanagement.models;

public class Admin {
    private String ID ;
    private String email ;
    private String Name ;

    public Admin() {
    }

    public Admin(String ID, String email) {
        this.ID = ID;
        this.email = email;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
