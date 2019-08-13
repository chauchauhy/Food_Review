package com.example.initapp.model;

public class Like {
    String likeID;
    String Res_ID;
    String userId;

    public Like(String likeID, String Res_ID, String userId) {
        this.likeID = likeID;
        this.Res_ID = Res_ID;
        this.userId = userId;

    }

    public String getLikeID() {
        return likeID;
    }

    public void setLikeID(String likeID) {
        this.likeID = likeID;
    }

    public String getRes_ID() {
        return Res_ID;
    }

    public void setRes_ID(String Res_ID) {
        this.Res_ID = Res_ID;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Like{" +
                "likeID='" + likeID + '\'' +
                ", Res_ID='" + Res_ID + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
