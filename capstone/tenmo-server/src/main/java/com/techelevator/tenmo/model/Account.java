package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Account {
    private int accountId;
    private BigDecimal balance;
    private int userId;

    public Account() {}

    public Account(int accountId, BigDecimal balance) {
        this.accountId = accountId;
        this.balance = balance;

    }

    public int getUserId() {
        return userId;
    }

    public int getAccountId() {
        return accountId;
    }


    public BigDecimal getBalance() {
        return balance;
    }


    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }


    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
