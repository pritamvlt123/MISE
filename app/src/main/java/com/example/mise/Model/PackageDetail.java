package com.example.mise.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PackageDetail {
    @SerializedName("packageID")
    @Expose
    private String packageID;
    @SerializedName("packageName")
    @Expose
    private String packageName;
    @SerializedName("packageCostID")
    @Expose
    private String packageCostID;
    @SerializedName("classID")
    @Expose
    private String classID;
    @SerializedName("className")
    @Expose
    private String className;
    @SerializedName("packagePrice")
    @Expose
    private String packagePrice;
    @SerializedName("PM_No_Of_Subject")
    @Expose
    private String pMNoOfSubject;
    @SerializedName("Type")
    @Expose
    private String type;

    public String getPackageID() {
        return packageID;
    }

    public void setPackageID(String packageID) {
        this.packageID = packageID;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPackageCostID() {
        return packageCostID;
    }

    public void setPackageCostID(String packageCostID) {
        this.packageCostID = packageCostID;
    }

    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPackagePrice() {
        return packagePrice;
    }

    public void setPackagePrice(String packagePrice) {
        this.packagePrice = packagePrice;
    }

    public String getPMNoOfSubject() {
        return pMNoOfSubject;
    }

    public void setPMNoOfSubject(String pMNoOfSubject) {
        this.pMNoOfSubject = pMNoOfSubject;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
