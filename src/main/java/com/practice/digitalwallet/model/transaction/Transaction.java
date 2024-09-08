package com.practice.digitalwallet.model.transaction;

import com.practice.digitalwallet.model.user.User;

public class Transaction {
    String transactionId;
    User sourceUser;
    User targetUser;
    double amount;

    TransactionState transactionState;

    public Transaction(User sourceUser, User targetUser, double amount, String transactionId){
        this.amount = amount;
        this.sourceUser = sourceUser;
        this.transactionId = transactionId;
        this.targetUser = targetUser;
        transactionState = TransactionState.NOT_INITIATED;

    }

    public void setTransactionState(TransactionState transactionState){
        this.transactionState = transactionState;
    }
    public String getTransactionId(){
        return transactionId;
    }





}
