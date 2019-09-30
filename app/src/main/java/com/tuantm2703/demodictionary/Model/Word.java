package com.tuantm2703.demodictionary.Model;

import java.io.Serializable;

public class Word implements Serializable {

    private Integer ID;
    private String english;
    private String vietnamese;
    private String explanation;

    public Word(){

    }

    public Word(Integer ID, String english, String vietnamese, String explanation) {
        this.ID = ID;
        this.english = english;
        this.vietnamese = vietnamese;
        this.explanation = explanation;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getVietnamese() {
        return vietnamese;
    }

    public void setVietnamese(String vietnamese) {
        this.vietnamese = vietnamese;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}
