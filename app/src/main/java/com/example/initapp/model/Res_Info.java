package com.example.initapp.model;

public class Res_Info {
    public String res_info_id;
    public String res_info_phone;
    public String res_Address;
    public String res_Time;
    public String res_Price;
    public String res_id;

    public Res_Info(String res_info_id, String res_info_phone, String res_Address, String res_Time, String res_Price, String res_id) {
        this.res_info_id = res_info_id;
        this.res_info_phone = res_info_phone;
        this.res_Address = res_Address;
        this.res_Time = res_Time;
        this.res_Price = res_Price;
        this.res_id = res_id;
    }

    public String getRes_info_id() {
        return res_info_id;
    }

    public void setRes_info_id(String res_info_id) {
        this.res_info_id = res_info_id;
    }

    public String getRes_info_phone() {
        return res_info_phone;
    }

    public void setRes_info_phone(String res_info_phone) {
        this.res_info_phone = res_info_phone;
    }

    public String getRes_Address() {
        return res_Address;
    }

    public void setRes_Address(String res_Address) {
        this.res_Address = res_Address;
    }

    public String getRes_Time() {
        return res_Time;
    }

    public void setRes_Time(String res_Time) {
        this.res_Time = res_Time;
    }

    public String getRes_Price() {
        return res_Price;
    }

    public void setRes_Price(String res_Price) {
        this.res_Price = res_Price;
    }

    public String getRes_id() {
        return res_id;
    }

    public void setRes_id(String res_id) {
        this.res_id = res_id;
    }

    @Override
    public String toString() {
        return "Res_Info{" +
                "res_info_id='" + res_info_id + '\'' +
                ", res_info_phone='" + res_info_phone + '\'' +
                ", res_Address='" + res_Address + '\'' +
                ", res_Time='" + res_Time + '\'' +
                ", res_Price='" + res_Price + '\'' +
                ", res_id='" + res_id + '\'' +
                '}';
    }
}
