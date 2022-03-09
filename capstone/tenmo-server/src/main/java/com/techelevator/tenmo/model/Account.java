package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Account {
    private int accountId;
    private BigDecimal balance;

    public Account(int accountId, BigDecimal balance) {
        this.accountId = accountId;
        this.balance = balance;
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
}
