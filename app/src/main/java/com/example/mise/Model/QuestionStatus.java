package com.example.mise.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class QuestionStatus implements Serializable {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("respMessage")
    @Expose
    private String respMessage;
    @SerializedName("questionDetails")
    @Expose
    private List<QuestionDetail> questionDetails = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<QuestionDetail> getQuestionDetails() {
        return questionDetails;
    }

    public void setQuestionDetails(List<QuestionDetail> questionDetails) {
        this.questionDetails = questionDetails;
    }


    public String getRespMessage() {
        return respMessage;
    }

    public void setRespMessage(String respMessage) {
        this.respMessage = respMessage;
    }


}
