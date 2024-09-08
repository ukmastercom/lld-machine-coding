package com.practice.digitalwallet.model.transaction;

import com.practice.digitalwallet.model.user.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class TransactionEntitySet {
    static TransactionEntitySet instance;
    String prefix = "TID";
    AtomicInteger transactionCount;
    Map<String, Transaction> transactionIdToTransactionMap;

    private TransactionEntitySet(){
        transactionCount = new AtomicInteger();
        transactionIdToTransactionMap = new ConcurrentHashMap<>();
    }

    public static TransactionEntitySet getInstance(){
        if(instance==null){
            synchronized (instance){
                if(instance==null){
                    instance = new TransactionEntitySet();
                }
            }
        }
        return instance;
    }

    private String getTransactionId(){
        return prefix+transactionCount.incrementAndGet();
    }

    public Transaction createNewTransaction(User user1, User user2, double amount){
        String tid = getTransactionId();
        Transaction transaction = new Transaction(user1,user2,amount,tid);
        System.out.println("Creating new Transaction with TID: "+tid);
        transactionIdToTransactionMap.put(tid,transaction);
        return transaction;
    }

    public Transaction getTransactionForId(String tid){
        Transaction transaction = transactionIdToTransactionMap.get(tid);
        if (transaction==null){
            System.out.println("No transaction found with id: "+tid);
        }
        return transaction;
    }

}
