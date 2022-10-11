package com.example.crashcourse.user;

public class userModel {

    private String fName;
    private String userName;
    private String password;
    private String email;

    //constructors
    public userModel(String fName, String userName, String password, String email) {
        this.fName = fName;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    //toString


    @Override
    public String toString() {
        return "userModel{" +
                "fName='" + fName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    //getters & setters
    public String getfName() {
        return fName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
