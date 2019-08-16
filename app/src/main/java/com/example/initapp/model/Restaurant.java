package com.example.initapp.model;

public class Restaurant {
    private String resID;
    private String resName;
    private String resDetail;
    private String resImage;
    private String resImage2;
    private String resImage3;
    private String resLike;
    private String resMark;
    private String resType;
    private String phone;
    private String address;
    private String price;
    private String time;

    public Restaurant(String resID, String resName, String resDetail, String resImage, String resImage2, String resImage3, String resLike, String resMark, String resType, String phone, String address, String price, String time) {
        this.resID = resID;
        this.resName = resName;
        this.resDetail = resDetail;
        this.resImage = resImage;
        this.resImage2 = resImage2;
        this.resImage3 = resImage3;
        this.resLike = resLike;
        this.resMark = resMark;
        this.resType = resType;
        this.phone = phone;
        this.address = address;
        this.price = price;
        this.time = time;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Restaurant(String resID, String resName, String resDetail, String resImage, String resImage2, String resImage3, String resLike, String resMark, String resType) {
        this.resID = resID;
        this.resName = resName;
        this.resDetail = resDetail;
        this.resImage = resImage;
        setResImage2(resImage2);
        setResImage3(resImage3);
        this.resLike = resLike;
        this.resMark = resMark;
        this.resType = resType;
    }

    public Restaurant() {
    }

    public String getResID() {
        return resID;
    }

    public void setResID(String resID) {
        this.resID = resID;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public String getResDetail() {
        return resDetail;
    }

    public void setResDetail(String resDetail) {
        this.resDetail = resDetail;
    }

    public String getResImage() {
        return resImage;
    }

    public void setResImage(String resImage) {
        this.resImage = resImage;
    }

    public String getResImage2() {
        return resImage2;
    }

    public void setResImage2(String resImage2) {
        if(resImage2.trim().length()<3){
            this.resImage2 = "NA";
        }else {
            this.resImage2 = resImage2;
        }

    }

    public String getResImage3() {
        return resImage3;
    }

    public void setResImage3(String resImage3) {
        if(resImage3.trim().length()<3){
            this.resImage3 = "NA";
        }else{
            this.resImage3 = resImage3;
        }
    }

    public String getResLike() {
        return resLike;
    }

    public void setResLike(String resLike) {
        this.resLike = resLike;
    }

    public String getResMark() {
        return resMark;
    }

    public void setResMark(String resMark) {
        this.resMark = resMark;
    }

    public String getResType() {
        return resType;
    }

    public void setResType(String resType) {
        this.resType = resType;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "resID='" + resID + '\'' +
                ", resName='" + resName + '\'' +
                ", resDetail='" + resDetail + '\'' +
                ", resImage='" + resImage + '\'' +
                ", resImage2='" + resImage2 + '\'' +
                ", resImage3='" + resImage3 + '\'' +
                ", resLike='" + resLike + '\'' +
                ", resMark='" + resMark + '\'' +
                ", resType='" + resType + '\'' +
                '}';
    }
}
