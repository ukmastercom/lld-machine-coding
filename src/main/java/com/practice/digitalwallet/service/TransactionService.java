package com.practice.digitalwallet.service;

import com.practice.digitalwallet.model.account.AbstractAccount;
import com.practice.digitalwallet.model.transaction.Transaction;
import com.practice.digitalwallet.model.transaction.TransactionState;
import com.practice.digitalwallet.model.user.User;
import com.practice.digitalwallet.model.transaction.TransactionEntitySet;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TransactionService {
    static TransactionService instance;
    UserManagementService userManagementService;
    TransactionEntitySet transactionEntitySet;
    AccountManagementService accountManagementService;

    private TransactionService(){
        userManagementService = UserManagementService.getInstance();
        transactionEntitySet = TransactionEntitySet.getInstance();
        accountManagementService = WalletAccountManagementService.getInstance();
    }

    public static TransactionService getInstance(){
        if(instance==null){
            synchronized (instance){
                if(instance==null){
                    instance = new TransactionService();
                }
            }
        }
        return instance;
    }

    private void failTransaction(Transaction transaction){
        transaction.setTransactionState(TransactionState.FAILED);
        System.out.println("transaction with id: "+transaction.getTransactionId()+" failed");
    }

    public boolean transferFunds(String userId1, String userId2, double amount){
        User user1 = userManagementService.getUserForId(userId1);
        User user2 = userManagementService.getUserForId(userId2);
        Transaction transaction = null;
        //this is a transaction need to think of rollback
        try{
            transaction = transactionEntitySet.createNewTransaction(user1,user2,amount);
            boolean status = accountManagementService.deductAmountFromAccountForUser(user1,amount);
            transaction.setTransactionState(TransactionState.AMOUNT_DEDUCTED);
            if(!status){
                throw new RuntimeException("Transaction failed");
            }
            status = accountManagementService.addAmountToAccountForUser(user2,amount);
            if(!status){
                throw new RuntimeException("Transaction failed");
            }
            transaction.setTransactionState(TransactionState.AMOUNT_CREDITED);
            transaction.setTransactionState(TransactionState.SUCCESS);
            return true;
        }
        catch (Exception e){
            System.out.println("Error occured during transaction: "+e);
            if(transaction!=null){
                failTransaction(transaction);
                //            rollbackTransaction(transaction);
            }
            return false;
        }
    }
}
