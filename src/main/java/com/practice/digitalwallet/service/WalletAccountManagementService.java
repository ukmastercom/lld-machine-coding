package com.practice.digitalwallet.service;

import com.practice.digitalwallet.model.account.AbstractAccount;
import com.practice.digitalwallet.model.account.WalletAccount;
import com.practice.digitalwallet.model.account.WalletAccountEntitySet;
import com.practice.digitalwallet.model.user.User;

import java.util.List;

public class WalletAccountManagementService implements AccountManagementService {

    static WalletAccountManagementService instance;

    WalletAccountEntitySet walletAccountEntitySet;

    private WalletAccountManagementService(){
    }

    public static WalletAccountManagementService getInstance(){
        if(instance==null){
            synchronized (instance){
                if(instance==null){
                    instance = new WalletAccountManagementService();
                }
            }
        }
        return instance;
    }

    @Override
    public AbstractAccount generateNewAccount(User user) {
        return walletAccountEntitySet.createNewAccount(user);
    }

    @Override
    public List<AbstractAccount> getAccountForUser(User user) {
        return walletAccountEntitySet.getAccountForUser(user);
    }

    @Override
    public AbstractAccount getAccountWithId(String id) {
        return walletAccountEntitySet.getAccountForId(id);
    }

    @Override
    public List<AbstractAccount> getAccountsForUser(User user) {
        return walletAccountEntitySet.getAccountForUser(user);
    }

    @Override
    public boolean deductAmountFromAccount(AbstractAccount account, double amount) {
        WalletAccount walletAccount = (WalletAccount) account;
        boolean status = walletAccount.deductBalance(amount);
        if(!status){
            System.out.println("Account: "+account.getAccountNo()+" has insufficient Balance");
            return false;
        }
        System.out.println("deducted "+amount+" from account: "+account.getAccountNo());
        return true;
    }

    @Override
    public boolean addAmountToAccount(AbstractAccount account, double amount) {
        return false;
    }

    @Override
    public boolean deductAmountFromAccountForUser(User user, double amount){
        List<AbstractAccount> walletAccounts = walletAccountEntitySet.getAccountForUser(user);
        if(walletAccounts.isEmpty()){
            System.out.println("No active account for user: "+user.getId());
            return false;
        }
        WalletAccount walletAccount = (WalletAccount) walletAccounts.get(0);
        return walletAccount.deductBalance(amount);
    }

    @Override
    public boolean addAmountToAccountForUser(User user, double amount) {
        List<AbstractAccount> walletAccounts = walletAccountEntitySet.getAccountForUser(user);
        if(walletAccounts.isEmpty()){
            System.out.println("No active account for user: "+user.getId());
            return false;
        }
        WalletAccount walletAccount = (WalletAccount) walletAccounts.get(0);
        walletAccount.addBalance(amount);
        return true;
    }


}
