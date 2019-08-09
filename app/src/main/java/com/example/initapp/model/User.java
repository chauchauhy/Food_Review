package com.example.initapp.model;

public class User {
    private String userID;
    private String userName;
    private String userAccount;
    private String userPassword;
    private String userLoginMethod;
    private String userEmail;
    private String userSignupTime;
    private String userIcon;
    private String userLevel;

    public User() {
    }

    @Override
    public String toString() {
        return "user{" +
                "userID='" + userID + '\'' +
                ", userName='" + userName + '\'' +
                ", userAccount='" + userAccount + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userLoginMethod='" + userLoginMethod + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userSignupTime='" + userSignupTime + '\'' +
                ", userIcon='" + userIcon + '\'' +
                ", userLevel='" + userLevel + '\'' +
                '}';
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserLoginMethod() {
        return userLoginMethod;
    }

    public void setUserLoginMethod(String userLoginMethod) {

        this.userLoginMethod = userLoginMethod;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserSignupTime() {
        return userSignupTime;
    }

    public void setUserSignupTime(String userSignupTime) {
        this.userSignupTime = userSignupTime;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public User(String userID, String userName, String userAccount, String userPassword, String userLoginMethod, String userEmail, String userSignupTime, String userIcon, String userLevel) {
        this.userID = userID;
        this.userName = userName;
        this.userAccount = userAccount;
        this.userPassword = userPassword;
        this.userLoginMethod = userLoginMethod;
        this.userEmail = userEmail;
        this.userSignupTime = userSignupTime;
        this.userIcon = userIcon;
        this.userLevel = userLevel;
    }
}
