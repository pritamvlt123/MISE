package com.example.mise.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlanPackageFetch {
    @SerializedName("SR_Class")
    @Expose
    private String sRClass;
    @SerializedName("SR_Source")
    @Expose
    private String sRSource;
    @SerializedName("SR_Medium")
    @Expose
    private String sRMedium;

    public String getSRClass() {
        return sRClass;
    }

    public void setSRClass(String sRClass) {
        this.sRClass = sRClass;
    }

    public String getSRSource() {
        return sRSource;
    }

    public void setSRSource(String sRSource) {
        this.sRSource = sRSource;
    }

    public String getSRMedium() {
        return sRMedium;
    }

    public void setSRMedium(String sRMedium) {
        this.sRMedium = sRMedium;
    }

}
