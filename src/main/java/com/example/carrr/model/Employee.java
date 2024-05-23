package com.example.carrr.model;

public class Employee {
    private int id;
    private String username;
    private String userPassword;
    private Usertype usertype;

    public Employee() {
    }

    public Employee(int id, String username, String userPassword, Usertype usertype) {
        this.id = id;
        this.username = username;
        this.userPassword = userPassword;
        this.usertype = usertype;
    }

    // Getters and Setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Usertype getUsertype() {
        return usertype;
    }

    public void setUsertype(Usertype usertype) {
        this.usertype = usertype;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "username='" + username + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", usertype=" + usertype +
                '}';
    }
}