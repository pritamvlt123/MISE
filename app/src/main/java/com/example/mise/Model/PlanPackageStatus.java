package com.example.mise.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlanPackageStatus  {


    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("PackageDetails")
    @Expose
    private List<PackageDetail> packageDetails = null;
    @SerializedName("Subjects")
    @Expose
    private List<Subject> subjects = null;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<PackageDetail> getPackageDetails() {
        return packageDetails;
    }

    public void setPackageDetails(List<PackageDetail> packageDetails) {
        this.packageDetails = packageDetails;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
}
