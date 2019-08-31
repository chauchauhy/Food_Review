package com.example.initapp.model;

public class Mlkit_post {
    String post_ID;
    String imagePath;
    String imageType_MAIN;
    String imageType_SUB1;
    String imageType_SUB2;
    String imageType_SUB3;
    String imageType_SUB4;

    public Mlkit_post(String post_ID, String imagePath, String imageType_MAIN, String imageType_SUB1, String imageType_SUB2, String imageType_SUB3, String imageType_SUB4) {
        this.post_ID = post_ID;
        this.imagePath = imagePath;
        this.imageType_MAIN = imageType_MAIN;
        this.imageType_SUB1 = imageType_SUB1;
        this.imageType_SUB2 = imageType_SUB2;
        this.imageType_SUB3 = imageType_SUB3;
        this.imageType_SUB4 = imageType_SUB4;
    }

    public Mlkit_post() {
    }

    @Override
    public String toString() {
        return "Mlkit_post{" +
                "post_ID='" + post_ID + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", imageType_MAIN='" + imageType_MAIN + '\'' +
                ", imageType_SUB1='" + imageType_SUB1 + '\'' +
                ", imageType_SUB2='" + imageType_SUB2 + '\'' +
                ", imageType_SUB3='" + imageType_SUB3 + '\'' +
                ", imageType_SUB4='" + imageType_SUB4 + '\'' +
                '}';
    }

    public String getPost_ID() {
        return post_ID;
    }

    public void setPost_ID(String post_ID) {
        this.post_ID = post_ID;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImageType_MAIN() {
        return imageType_MAIN;
    }

    public void setImageType_MAIN(String imageType_MAIN) {
        this.imageType_MAIN = imageType_MAIN;
    }

    public String getImageType_SUB1() {
        return imageType_SUB1;
    }

    public void setImageType_SUB1(String imageType_SUB1) {
        this.imageType_SUB1 = imageType_SUB1;
    }

    public String getImageType_SUB2() {
        return imageType_SUB2;
    }

    public void setImageType_SUB2(String imageType_SUB2) {
        this.imageType_SUB2 = imageType_SUB2;
    }

    public String getImageType_SUB3() {
        return imageType_SUB3;
    }

    public void setImageType_SUB3(String imageType_SUB3) {
        this.imageType_SUB3 = imageType_SUB3;
    }

    public String getImageType_SUB4() {
        return imageType_SUB4;
    }

    public void setImageType_SUB4(String imageType_SUB4) {
        this.imageType_SUB4 = imageType_SUB4;
    }
}
