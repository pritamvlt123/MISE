package com.example.mise.Model;

import java.util.List;

public class QuestionsModel {

    private String question;
    private List<String> answers;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public QuestionsModel(String question, List<String>answers ) {
        this.question = question;
        this.answers = answers;
    }

}
