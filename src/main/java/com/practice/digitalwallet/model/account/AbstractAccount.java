package com.practice.digitalwallet.model.account;

import com.practice.digitalwallet.model.user.User;

import java.util.function.Predicate;

public abstract class AbstractAccount {
    protected String accountNo;
    protected User user;
    protected Double balance;
    public String getAccountNo(){
        return accountNo;
    }

    public double getBalance(){
        return balance;
    }
    protected Predicate<Double> checkValidBalance = (balance) -> balance>=0.0;

    public abstract void addBalance(double amount);
    public abstract boolean deductBalance(double amount);



}
