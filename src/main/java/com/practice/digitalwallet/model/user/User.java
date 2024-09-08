package com.practice.digitalwallet.model.user;

import com.practice.digitalwallet.Wallet;

public class User {
    String userId;
    String name;
    Wallet wallet;

    public User(String name, String userId){
        this.userId = userId;
        this.name = name;
    }
    public String getId(){
        return userId;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
}
