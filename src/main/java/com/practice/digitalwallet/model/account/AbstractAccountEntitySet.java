package com.practice.digitalwallet.model.account;

import com.practice.digitalwallet.model.user.User;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class AbstractAccountEntitySet {
    Map<String, AbstractAccount> idToEntityMap;
    Map<User, List<AbstractAccount>> userToAccountMap;
    String prefix;
    AtomicInteger accountCount;

    public AbstractAccount getAccountForId(String id){
        return idToEntityMap.get(id);
    }
    protected String getNewId(){
        return prefix+accountCount.incrementAndGet();
    }

    public abstract AbstractAccount createNewAccount(User user);
    public List<AbstractAccount> getAccountForUser(User user){
        return userToAccountMap.get(user);
    }



}
