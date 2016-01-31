package com.hequalab.rai.api.dtos.user;

public class UserUpdatePassword {

    private String password;

    @SuppressWarnings("unused")
    private UserUpdatePassword() {

    }
    
    public UserUpdatePassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String unitCode) {
        this.password = unitCode;
    }

}