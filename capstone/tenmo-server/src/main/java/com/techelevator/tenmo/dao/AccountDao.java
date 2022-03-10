package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountDao {

    List<Account> getAll();

    List<Account> getAllAccountsByUserId();

    Account getAccount(int userId);

    int getAccountIdByUserId(int userId);

    BigDecimal getBalance(int accountId);

}
