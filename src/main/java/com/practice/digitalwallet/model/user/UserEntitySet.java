package com.practice.digitalwallet.model.user;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class UserEntitySet {
    static UserEntitySet instance;
    String prefix = "UID";
    AtomicInteger userCount;
    Map<String, User> userIdToUserMap;

    private UserEntitySet(){
        userCount = new AtomicInteger();
        userIdToUserMap = new ConcurrentHashMap<>();
    }

    public static UserEntitySet getInstance(){
        if(instance==null){
            synchronized (instance){
                if(instance==null){
                    instance = new UserEntitySet();
                }
            }
        }
        return instance;
    }

    private String getNewUserId(){
        return prefix+userCount.incrementAndGet();
    }

    public User createNewUser(String name){
        String uid = getNewUserId();
        User user = new User(name,uid);
        System.out.println("Creating new USER with TID: "+uid);
        userIdToUserMap.put(uid,user);
        return user;
    }

    public User getUserForId(String uid){
        User transaction = userIdToUserMap.get(uid);
        if (transaction==null){
            System.out.println("No transaction found with id: "+uid);
        }
        return transaction;
    }

    public void deleteExistingUser(String userId){
        userIdToUserMap.remove(userId);
    }
}
