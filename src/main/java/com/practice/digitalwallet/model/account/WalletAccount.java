package com.practice.digitalwallet.model.account;

import com.practice.digitalwallet.model.user.User;

public class WalletAccount extends AbstractAccount {

    public WalletAccount(User user,String accountNo){
        this.accountNo = accountNo;
        this.balance = 0.0;
        this.user = user;
    }

    @Override
    public synchronized void addBalance(double amount) {
        balance+=amount;
    }

    @Override
    public synchronized boolean deductBalance(double amount) {
        double newBalance = balance-amount;
        if(checkValidBalance.test(newBalance)){
            balance = newBalance;
            System.out.println("Deducting "+amount+" from "+accountNo);
            return true;
        }
        System.out.println("ERROR::Balance cannot be below 0.0");
        return false;
    }
}
