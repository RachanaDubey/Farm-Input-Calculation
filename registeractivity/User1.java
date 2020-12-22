package com.rait.registeractivity;

public class User1 {
    private int id;
    private String name;
    private String email;

    public User1(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;

    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return name;
    }

    public String getShortdesc() {
        return email;
    }


}