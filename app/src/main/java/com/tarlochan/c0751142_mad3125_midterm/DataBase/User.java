package com.tarlochan.c0751142_mad3125_midterm.DataBase;

public class User
{
    private String email;
    private String password;
    private int id;
    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public User(int id,String email, String password) {
        this.id=id;
        this.email = email;
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User{"+"user id = "+id+ '\''+
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
