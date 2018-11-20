package com.kzhong.mc10.model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Studies {

    private String displayName;
    private String title;

    @SerializedName("createdTs")
    private String timeStamp;

    public Studies(String displayName, String title, String timeStamp) {
        this.displayName = displayName;
        this.title = title;
        this.timeStamp = timeStamp;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getTimeStamp() {
        return timeStamp;
    }
}
