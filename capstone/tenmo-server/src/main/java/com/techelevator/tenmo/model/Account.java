package com.techelevator.tenmo.model;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class Account {

    @Positive(message = "Account Id must be a positive value")
    private int accountId;
    @DecimalMin( value = "0.01", message = "Balance must be greater than 0.00")
    private BigDecimal balance;
    @Positive(message = "Transfer Id must be a positive value")
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
