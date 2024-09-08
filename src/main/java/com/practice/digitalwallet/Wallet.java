package com.practice.digitalwallet;

import com.practice.digitalwallet.model.account.AbstractAccount;

public class Wallet {
    AbstractAccount account;
    String walletId;
    public Wallet(AbstractAccount account, String walletId){
        this.account = account;
        this.walletId = walletId;
    }
    public AbstractAccount getAccount(){
        return account;
    }
    public String getWalletId(){
        return walletId;
    }
    //putting in hooks need to determine if needed or not
    public double getBalance(){
        return account.getBalance();
    }
}
