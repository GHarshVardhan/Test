
package com.example.harsh.harshgopettingtestapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("startDate")
    @Expose
    private String startDate;
    @SerializedName("objType")
    @Expose
    private String objType;
    @SerializedName("endDate")
    @Expose
    private String endDate;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("loginRequired")
    @Expose
    private Boolean loginRequired;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("venue")
    @Expose
    private Venue venue;
    @SerializedName("icon")
    @Expose
    private String icon;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getObjType() {
        return objType;
    }

    public void setObjType(String objType) {
        this.objType = objType;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getLoginRequired() {
        return loginRequired;
    }

    public void setLoginRequired(Boolean loginRequired) {
        this.loginRequired = loginRequired;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}
