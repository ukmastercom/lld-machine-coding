package com.practice.digitalwallet.service;

import com.practice.digitalwallet.model.user.User;
import com.practice.digitalwallet.model.user.UserEntitySet;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class UserManagementService {

    static UserManagementService userManagementService;
    private UserEntitySet userEntitySet;

    private UserManagementService(){
        userEntitySet = UserEntitySet.getInstance();
    }

    public static UserManagementService getInstance(){
        if(userManagementService == null){
            synchronized (userManagementService){
                if(userManagementService==null){
                    userManagementService = new UserManagementService();
                }
            }
        }
        return userManagementService;
    }

    public User getUserForId(String id){
        return userEntitySet.getUserForId(id);
    }


}
