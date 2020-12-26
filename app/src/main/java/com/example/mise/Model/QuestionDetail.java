package com.example.mise.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class QuestionDetail implements Serializable {
    @SerializedName("PM_ID")
    @Expose
    private String pMID;
    @SerializedName("PM_Class_ID")
    @Expose
    private String pMClassID;
    @SerializedName("PM_Language")
    @Expose
    private String pMLanguage;
    @SerializedName("PM_Subject_ID")
    @Expose
    private String pMSubjectID;
    @SerializedName("PM_Question_No")
    @Expose
    private String pMQuestionNo;
    @SerializedName("PM_Question_Desc")
    @Expose
    private String pMQuestionDesc;
    @SerializedName("PM_Question_Marks")
    @Expose
    private String pMQuestionMarks;
    @SerializedName("PM_First_Option")
    @Expose
    private String pMFirstOption;
    @SerializedName("PM_Second_Option")
    @Expose
    private String pMSecondOption;
    @SerializedName("PM_Third_Option")
    @Expose
    private String pMThirdOption;
    @SerializedName("PM_Fourth_Option")
    @Expose
    private String pMFourthOption;
    @SerializedName("PM_Answare")
    @Expose
    private String pMAnsware;
    @SerializedName("PM_Question_For")
    @Expose
    private String pMQuestionFor;
    @SerializedName("PM_SET_NO")
    @Expose
    private String pMSETNO;
    @SerializedName("PM_Image")
    @Expose
    private String pMImage;
    @SerializedName("Create_Date")
    @Expose
    private String createDate;
    @SerializedName("Online_Offline")
    @Expose
    private Object onlineOffline;

    public String getPMID() {
        return pMID;
    }

    public void setPMID(String pMID) {
        this.pMID = pMID;
    }

    public String getPMClassID() {
        return pMClassID;
    }

    public void setPMClassID(String pMClassID) {
        this.pMClassID = pMClassID;
    }

    public String getPMLanguage() {
        return pMLanguage;
    }

    public void setPMLanguage(String pMLanguage) {
        this.pMLanguage = pMLanguage;
    }

    public String getPMSubjectID() {
        return pMSubjectID;
    }

    public void setPMSubjectID(String pMSubjectID) {
        this.pMSubjectID = pMSubjectID;
    }

    public String getPMQuestionNo() {
        return pMQuestionNo;
    }

    public void setPMQuestionNo(String pMQuestionNo) {
        this.pMQuestionNo = pMQuestionNo;
    }

    public String getPMQuestionDesc() {
        return pMQuestionDesc;
    }

    public void setPMQuestionDesc(String pMQuestionDesc) {
        this.pMQuestionDesc = pMQuestionDesc;
    }

    public String getPMQuestionMarks() {
        return pMQuestionMarks;
    }

    public void setPMQuestionMarks(String pMQuestionMarks) {
        this.pMQuestionMarks = pMQuestionMarks;
    }

    public String getPMFirstOption() {
        return pMFirstOption;
    }

    public void setPMFirstOption(String pMFirstOption) {
        this.pMFirstOption = pMFirstOption;
    }

    public String getPMSecondOption() {
        return pMSecondOption;
    }

    public void setPMSecondOption(String pMSecondOption) {
        this.pMSecondOption = pMSecondOption;
    }

    public String getPMThirdOption() {
        return pMThirdOption;
    }

    public void setPMThirdOption(String pMThirdOption) {
        this.pMThirdOption = pMThirdOption;
    }

    public String getPMFourthOption() {
        return pMFourthOption;
    }

    public void setPMFourthOption(String pMFourthOption) {
        this.pMFourthOption = pMFourthOption;
    }

    public String getPMAnsware() {
        return pMAnsware;
    }

    public void setPMAnsware(String pMAnsware) {
        this.pMAnsware = pMAnsware;
    }

    public String getPMQuestionFor() {
        return pMQuestionFor;
    }

    public void setPMQuestionFor(String pMQuestionFor) {
        this.pMQuestionFor = pMQuestionFor;
    }

    public String getPMSETNO() {
        return pMSETNO;
    }

    public void setPMSETNO(String pMSETNO) {
        this.pMSETNO = pMSETNO;
    }

    public String getPMImage() {
        return pMImage;
    }

    public void setPMImage(String pMImage) {
        this.pMImage = pMImage;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Object getOnlineOffline() {
        return onlineOffline;
    }

    public void setOnlineOffline(Object onlineOffline) {
        this.onlineOffline = onlineOffline;
    }
    @Override
    public String toString() {
        return pMLanguage;
    }
}
