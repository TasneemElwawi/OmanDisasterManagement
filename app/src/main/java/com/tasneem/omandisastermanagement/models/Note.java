package com.tasneem.omandisastermanagement.models;

public class Note {
    private String id;
    private String title;
    private String details;
    private String time;
    private String SenderEmail;
    private boolean isDelete;


    public Note(String id, String title, String details , Boolean isDelete , String time) {
        this.id = id ;
        this.title = title;
        this.details = details;
        this.isDelete = isDelete;
        this.time = time;

    }

    public Note(String title, String details , Boolean isDelete , String time) {
        this.title = title;
        this.details = details;
        this.isDelete = isDelete;
        this.time = time;
    }

    public Note(String title, String details,  boolean isDelete , String time , String SenderEmail) {
        this.title = title;
        this.details = details;
        this.isDelete = isDelete;
        this.time = time;
        this.SenderEmail = SenderEmail;

    }
//    public Note(String title, String details) {
//        this.title = title;
//        this.details = details;
//    }

    public Note() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSenderName() {
        return SenderEmail;
    }

    public void setSenderName(String senderName) {
        SenderEmail = senderName;
    }
}
