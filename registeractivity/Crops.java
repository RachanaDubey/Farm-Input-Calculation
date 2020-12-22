package com.rait.registeractivity;

public class Crops {
String quantity;
private String crop;

    public Crops(String quantity, String crop) {
        this.quantity = quantity;
        this.crop = crop;

    }


    public String getTitle() {
        return crop;
    }

    public String getShortdesc() {
        return quantity;
    }


}