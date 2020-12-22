package com.rait.registeractivity;

public class User {
    private int fid;
    private String name;
    private String email;
    private String feedback_cat;
    private String feedback;

    public User(int fid, String name, String email, String feedback_cat, String feedback) {
        this.fid = fid;
        this.name = name;
        this.email = email;
        this.feedback_cat = feedback_cat;
        this.feedback = feedback;

    }

    public int getId() {
        return fid;
    }

    public String getTitle() {
        return name;
    }

    public String getShortdesc() {
        return email;
    }

    public String getRating() {
        return feedback_cat;
    }

    public String getPrice() {
        return feedback;
    }

}