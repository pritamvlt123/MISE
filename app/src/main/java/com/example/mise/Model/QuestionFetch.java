package com.example.mise.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class QuestionFetch implements Serializable {

    @SerializedName("SR_ID")
    @Expose
    private String sRID;
    @SerializedName("SR_Class")
    @Expose
    private String sRClass;
    @SerializedName("SR_Medium")
    @Expose
    private String sRMedium;
    @SerializedName("SR_Package")
    @Expose
    private String sRPackage;
    @SerializedName("subjectId")
    @Expose
    private String subjectId;
    @SerializedName("level")
    @Expose
    private String level;

    public String getSRID() {
        return sRID;
    }

    public void setSRID(String sRID) {
        this.sRID = sRID;
    }

    public String getSRClass() {
        return sRClass;
    }

    public void setSRClass(String sRClass) {
        this.sRClass = sRClass;
    }

    public String getSRMedium() {
        return sRMedium;
    }

    public void setSRMedium(String sRMedium) {
        this.sRMedium = sRMedium;
    }

    public String getSRPackage() {
        return sRPackage;
    }

    public void setSRPackage(String sRPackage) {
        this.sRPackage = sRPackage;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

}
