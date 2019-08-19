package com.example.initapp.model;

public class Comment_Restaurant {
    private String cm_id;
    private String cm_res_id;
    private String cm_res_name;
    private String cm_user_id;
    private String cm_user_name;
    private String cm_title;
    private String cm_content;
    private String cm_time;
    private String CM_RES_image;

    public String getCM_RES_image() {
        return CM_RES_image;
    }

    public void setCM_RES_image(String CM_RES_image) {
        if (CM_RES_image.length() < 3) {
            this.CM_RES_image = "NA";
        } else {
            this.CM_RES_image = CM_RES_image;
        }
    }

//    public Comment_Restaurant(String cm_id, String cm_res_id, String cm_res_name, String cm_user_id, String cm_user_name, String cm_title, String cm_content, String cm_time, String CM_RES_image) {
//        this.cm_id = cm_id;
//        this.cm_res_id = cm_res_id;
//        this.cm_res_name = cm_res_name;
//        this.cm_user_id = cm_user_id;
//        this.cm_user_name = cm_user_name;
//        this.cm_title = cm_title;
//        this.cm_content = cm_content;
//        this.cm_time = cm_time;
//        setCM_RES_image(CM_RES_image);
//    }

    public Comment_Restaurant(String cm_id, String cm_res_id, String cm_user_id, String cm_user_name, String cm_title, String cm_content, String cm_time) {
        this.cm_id = cm_id;
        this.cm_res_id = cm_res_id;
        this.cm_user_id = cm_user_id;
        this.cm_user_name = cm_user_name;
        this.cm_title = cm_title;
        this.cm_content = cm_content;
        this.cm_time = cm_time;
    }

    public Comment_Restaurant() {
    }

    public Comment_Restaurant(String cm_id, String cm_res_id, String cm_user_id, String cm_user_name, String cm_title, String cm_content, String cm_time,String CM_RES_image) {
        this.cm_id = cm_id;
        this.cm_res_id = cm_res_id;
        this.cm_user_id = cm_user_id;
        this.cm_user_name = cm_user_name;
        this.cm_title = cm_title;
        this.cm_content = cm_content;
        this.cm_time = cm_time;
        setCM_RES_image(CM_RES_image);
    }

    public String getCm_id() {
        return cm_id;
    }

    public void setCm_id(String cm_id) {
        this.cm_id = cm_id;
    }

    public String getCm_res_id() {
        return cm_res_id;
    }

    public void setCm_res_id(String cm_res_id) {
        this.cm_res_id = cm_res_id;
    }

    public String getCm_res_name() {
        return cm_res_name;
    }

    public void setCm_res_name(String cm_res_name) {
        this.cm_res_name = cm_res_name;
    }

    public String getCm_user_id() {
        return cm_user_id;
    }

    public void setCm_user_id(String cm_user_id) {
        this.cm_user_id = cm_user_id;
    }

    public String getCm_user_name() {
        return cm_user_name;
    }

    public void setCm_user_name(String cm_user_name) {
        this.cm_user_name = cm_user_name;
    }

    public String getCm_title() {
        return cm_title;
    }

    public void setCm_title(String cm_title) {
        this.cm_title = cm_title;
    }

    public String getCm_content() {
        return cm_content;
    }

    public void setCm_content(String cm_content) {
        this.cm_content = cm_content;
    }

    public String getCm_time() {
        return cm_time;
    }

    public void setCm_time(String cm_time) {
        this.cm_time = cm_time;
    }

    @Override
    public String toString() {
        return "Comment_Restaurant{" +
                "cm_id='" + cm_id + '\'' +
                ", cm_res_id='" + cm_res_id + '\'' +
                ", cm_res_name='" + cm_res_name + '\'' +
                ", cm_user_id='" + cm_user_id + '\'' +
                ", cm_user_name='" + cm_user_name + '\'' +
                ", cm_title='" + cm_title + '\'' +
                ", cm_content='" + cm_content + '\'' +
                ", cm_time='" + cm_time + '\'' +
                '}';
    }
}
