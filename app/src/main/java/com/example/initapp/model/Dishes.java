package com.example.initapp.model;

public class Dishes {
    private String dishes_ID;
    private String dishes_name;
    private String dishes_Image;
    private String dishes_res_id;

    public Dishes(String dishes_ID, String dishes_name, String dishes_Image, String dishes_res_id) {
        this.dishes_ID = dishes_ID;
        this.dishes_name = dishes_name;
        this.dishes_Image = dishes_Image;
        this.dishes_res_id = dishes_res_id;
    }

    public Dishes() {
    }

    public String getDishes_ID() {
        return dishes_ID;
    }

    public void setDishes_ID(String dishes_ID) {
        this.dishes_ID = dishes_ID;
    }

    public String getDishes_name() {
        return dishes_name;
    }

    public void setDishes_name(String dishes_name) {
        this.dishes_name = dishes_name;
    }

    public String getDishes_Image() {
        return dishes_Image;
    }

    public void setDishes_Image(String dishes_Image) {
        this.dishes_Image = dishes_Image;
    }

    public String getDishes_res_id() {
        return dishes_res_id;
    }

    public void setDishes_res_id(String dishes_res_id) {
        this.dishes_res_id = dishes_res_id;
    }

    @Override
    public String toString() {
        return "Dishes{" +
                "dishes_ID='" + dishes_ID + '\'' +
                ", dishes_name='" + dishes_name + '\'' +
                ", dishes_Image='" + dishes_Image + '\'' +
                ", dishes_res_id='" + dishes_res_id + '\'' +
                '}';
    }
}
