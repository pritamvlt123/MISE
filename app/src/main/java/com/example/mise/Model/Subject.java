package com.example.mise.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Subject {
    @SerializedName("SM_ID")
    @Expose
    private String sMID;
    @SerializedName("SM_CM_ID")
    @Expose
    private String sMCMID;
    @SerializedName("SM_Subject_Name")
    @Expose
    private String sMSubjectName;
    @SerializedName("ON_OFF")
    @Expose
    private String oNOFF;
    @SerializedName("SM_Steam")
    @Expose
    private String sMSteam;

    public String getSMID() {
        return sMID;
    }

    public void setSMID(String sMID) {
        this.sMID = sMID;
    }

    public String getSMCMID() {
        return sMCMID;
    }

    public void setSMCMID(String sMCMID) {
        this.sMCMID = sMCMID;
    }

    public String getSMSubjectName() {
        return sMSubjectName;
    }

    public void setSMSubjectName(String sMSubjectName) {
        this.sMSubjectName = sMSubjectName;
    }

    public String getONOFF() {
        return oNOFF;
    }

    public void setONOFF(String oNOFF) {
        this.oNOFF = oNOFF;
    }

    public String getSMSteam() {
        return sMSteam;
    }

    public void setSMSteam(String sMSteam) {
        this.sMSteam = sMSteam;
    }
}
