package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;


@RestController
//@PreAuthorize(("isAuthenticated()"))
public class AccountController {

    private AccountDao accountDao;
    private UserDao userDao;
    private TransferDao transferDao;


    public AccountController(AccountDao accountDao, UserDao userDao, TransferDao transferDao) {
        this.accountDao = accountDao;
        this.userDao = userDao;
        this.transferDao = transferDao;
    }

    //  @PreAuthorize("permitAll")
    @RequestMapping(path = "/account/balance/{id}", method = RequestMethod.GET)
    public Account getBalance(@PathVariable("id") int userId) {
        return accountDao.getBalance(userId);
    }

    @RequestMapping(path = "/account/balance/{id}", method = RequestMethod.POST)
    public void updateAccFrom(@PathVariable("balance") BigDecimal newBalance, @PathVariable("id") int accountFrom) {
        accountDao.updateAccFrom(newBalance, accountFrom);
    }

    @RequestMapping(path = "/account/balance/{id}", method = RequestMethod.POST)
    public void updateAccTo(@PathVariable("balance") BigDecimal newBalance, @PathVariable("id") int accountTo) {
        accountDao.updateAccTo(newBalance, accountTo);
    }
}