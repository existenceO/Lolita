package com.example.lolita.models;

public class UserModel {
    private String username;
    private Long phone;
    private String password;
    private String email;
    private String type = "c1001";

    public void setUsername(String username){
       this.username = username;
    }
    public String getUsername(){
        return this.username;
    }
    public void setPhone(Long phone){
        this.phone = phone;
    }
    public Long getPhone(){
        return this.phone;
    }
    public void setPassword(String pwd){
        this.password = pwd;
    }
    public String getPassword(){
        return this.password;
    }
    public String getEmail(){
       return this.email;
    }
    public void setEmain(String email){
        this.email = email;
    }
    public String getType(){
        return this.type;
    }
    public void setType(String type){
        this.type = type;
    }

}
