package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import java.math.BigDecimal;


public interface AccountSvcs {


    public Account getAccount(int accountId);


    public BigDecimal getBalance(int accountId);


    public boolean updateAccount(Account newAccount);


    public Account createAccount(Account newAccount);


}
