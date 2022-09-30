package com.tasneem.omandisastermanagement.models;

import java.io.Serializable;

public class Video implements Serializable {
    private String video_id;
    private String video_title;
    private String video_URL;
    private String video_IMG;


    public Video()  {
    }

    public Video(String video_title, String video_URL) {
        this.video_title = video_title;
        this.video_URL = video_URL;
    }
    public Video(String video_title, String video_URL , String video_IMG) {
        this.video_title = video_title;
        this.video_URL = video_URL;
        this.video_IMG = video_IMG;
    }


    public Video(String title) {
        this.video_title = title;
    }

    public String getVideo_id() {
        return video_id;
    }

    public void setVideo_id(String video_id) {
        this.video_id = video_id;
    }

    public String getVideo_title() {
        return video_title;
    }

    public void setVideo_title(String video_title) {
        this.video_title = video_title;
    }

    public String getVideo_URL() {
        return video_URL;
    }

    public void setVideo_URL(String video_URL) {
        this.video_URL = video_URL;
    }

    public String getVideo_IMG() {
        return video_IMG;
    }

    public void setVideo_IMG(String video_IMG) {
        this.video_IMG = video_IMG;
    }
}
