package com.practice.digitalwallet.model.account;

import com.practice.digitalwallet.model.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class WalletAccountEntitySet extends AbstractAccountEntitySet{

    private static WalletAccountEntitySet instance;

    private WalletAccountEntitySet(){
        this.prefix = "WID";
        this.accountCount = new AtomicInteger();
        this.idToEntityMap = new ConcurrentHashMap<>();
        this.userToAccountMap = new ConcurrentHashMap<>();
    }

    public static WalletAccountEntitySet getInstance(){
        if(instance==null){
            synchronized (instance){
                if(instance==null){
                    instance = new WalletAccountEntitySet();
                }
            }
        }
        return instance;
    }

    @Override
    public WalletAccount createNewAccount(User user) {
        String accountId = getNewId();
        WalletAccount account = new WalletAccount(user,accountId);
        idToEntityMap.put(accountId,account);
        List<AbstractAccount> accountList = userToAccountMap.getOrDefault(user,new ArrayList<>());
        accountList.add(account);
        System.out.println("Created New account with id: "+accountId+" for user : "+user.getId());
        return account;
    }
}
