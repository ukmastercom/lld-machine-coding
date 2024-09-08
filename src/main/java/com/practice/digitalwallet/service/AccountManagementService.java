package com.practice.digitalwallet.service;

import com.practice.digitalwallet.model.account.AbstractAccount;
import com.practice.digitalwallet.model.user.User;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public interface AccountManagementService {

    AbstractAccount generateNewAccount(User user);
    List<AbstractAccount> getAccountForUser(User user);
    AbstractAccount getAccountWithId(String id);
    List<AbstractAccount> getAccountsForUser(User user);
    boolean deductAmountFromAccount(AbstractAccount account, double amount);
    boolean addAmountToAccount(AbstractAccount account, double amount);
    boolean deductAmountFromAccountForUser(User user, double amount);

    boolean addAmountToAccountForUser(User user, double amount);
}
