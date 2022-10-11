package com.example.crashcourse.entity;

public class User {

    //OOP -> encapsulation
    private String fName;
    private String userName;
    private String password;
    private String email;

    //creating constructors
    public User(String fName, String userName, String password, String email) {
        this.fName = fName;
        this.userName = userName;
        this.password = password;
        this.email = email;
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
        //check if empty string

        //otherwise
        this.fName = fName;
    }

    public void setUserName(String userName) {
        //check if empty string

        //otherwise
        this.userName = userName;
    }

    public void setPassword(String password) {
        //check if empty string

        //otherwise
        this.password = password;
    }

    public void setEmail(String email) {
        //check if empty string

        //otherwise
        this.email = email;
    }

    //print obj to string
    @Override
    public String toString() {
        return "userModel{" +
                "fName='" + fName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
