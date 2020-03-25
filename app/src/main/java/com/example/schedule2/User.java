package com.example.schedule2;
//하나의 사용자를 저장하기 위한 클래스
public class User {

    String userID;
    String userPassword;
    String userGender;
    String userMajor;

    public String getUserID() { return userID; }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserGender() { return userGender; }

    public void setUserGender(String userGender) { this.userGender = userGender; }

    public String getUserMajor() {
        return userMajor;
    }

    public void setUserMajor(String userMajor) {
        this.userMajor = userMajor;
    }

    public User(String userID, String userPassword, String userGender, String userMajor) {
        this.userID = userID;
        this.userPassword = userPassword;
        this.userGender = userGender;
        this.userMajor = userMajor;
    }
}

