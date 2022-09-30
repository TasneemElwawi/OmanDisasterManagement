package com.tasneem.omandisastermanagement.models;

public class Case {

    private int ID ;
    private String familyName ;
    private String volunteerName ;
    private String address ;
    private String road_Description ;
    private int totalNumber ;
    private int old_Number ;
    private int teenager_Number ;
    private int children_Number ;
    private int critical_Case_N ;
    private int death_Number ;
    private int injuries_Number ;
    private int missing_Number ;
    private String neededAid ;
    private String providedAid ;
    private String un_paid_aid ;

    public Case() {
    }
    public static int incrementID = 1;

    public Case(int incrementID, String familyName, String volunteerName, String address, String road_Description,
                int totalNumber, int old_Number, int teenager_Number, int children_Number, int critical_Case_N,
                int death_Number, int injuries_Number, int missing_Number, String neededAid, String providedAid,
                String un_paid_aid) {
        this.ID= ++incrementID;
        this.familyName = familyName;
        this.volunteerName = volunteerName;
        this.address = address;
        this.road_Description = road_Description;
        this.totalNumber = totalNumber;
        this.old_Number = old_Number;
        this.teenager_Number = teenager_Number;
        this.children_Number = children_Number;
        this.critical_Case_N = critical_Case_N;
        this.death_Number = death_Number;
        this.injuries_Number = injuries_Number;
        this.missing_Number = missing_Number;
        this.neededAid = neededAid;
        this.providedAid = providedAid;
        this.un_paid_aid = un_paid_aid;
    }

    public Case(String familyName,String address, String road_Description,
                int critical_Case_N, int missing_Number, String providedAid,String volunteerName , String un_paid_aid) {
        this.familyName = familyName;
        this.volunteerName = volunteerName;
        this.address = address;
        this.road_Description = road_Description;
        this.critical_Case_N = critical_Case_N;
        this.missing_Number = missing_Number;
        this.neededAid = neededAid;
        this.providedAid = providedAid;
        this.un_paid_aid = un_paid_aid;
    }

    public Case(int critical_Case_N, int missing_Number, int injuries_Number , int death_Number ) {
        this.critical_Case_N = critical_Case_N;
        this.missing_Number = missing_Number;
        this.death_Number = death_Number;
        this.injuries_Number = injuries_Number;
    }

    public int getID() {
        return incrementID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getVolunteerName() {
        return volunteerName;
    }

    public void setVolunteerName(String volunteerName) {
        this.volunteerName = volunteerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRoad_Description() {
        return road_Description;
    }

    public void setRoad_Description(String road_Description) {
        this.road_Description = road_Description;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public int getOld_Number() {
        return old_Number;
    }

    public void setOld_Number(int old_Number) {
        this.old_Number = old_Number;
    }

    public int getTeenager_Number() {
        return teenager_Number;
    }

    public void setTeenager_Number(int teenager_Number) {
        this.teenager_Number = teenager_Number;
    }

    public int getChildren_Number() {
        return children_Number;
    }

    public void setChildren_Number(int children_Number) {
        this.children_Number = children_Number;
    }

    public int getCritical_Case_N() {
        return critical_Case_N;
    }

    public void setCritical_Case_N(int critical_Case_N) {
        this.critical_Case_N = critical_Case_N;
    }

    public int getDeath_Number() {
        return death_Number;
    }

    public void setDeath_Number(int death_Number) {
        this.death_Number = death_Number;
    }

    public int getInjuries_Number() {
        return injuries_Number;
    }

    public void setInjuries_Number(int injuries_Number) {
        this.injuries_Number = injuries_Number;
    }

    public int getMissing_Number() {
        return missing_Number;
    }

    public void setMissing_Number(int missing_Number) {
        this.missing_Number = missing_Number;
    }

    public String getNeededAid() {
        return neededAid;
    }

    public void setNeededAid(String neededAid) {
        this.neededAid = neededAid;
    }

    public String getProvidedAid() {
        return providedAid;
    }

    public void setProvidedAid(String providedAid) {
        this.providedAid = providedAid;
    }

    public String getUn_paid_aid() {
        return un_paid_aid;
    }

    public void setUn_paid_aid(String un_paid_aid) {
        this.un_paid_aid = un_paid_aid;
    }
}
