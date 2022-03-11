package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;


@RestController
//@RequestMapping("/account")
@PreAuthorize(("isAuthenticated()"))
public class AccountController {

    private AccountDao accountDao;
    private UserDao userDao;
    private TransferDao transferDao;

    public AccountController(AccountDao accountDao, UserDao userDao, TransferDao transferDao) {
        this.accountDao = accountDao;
        this.userDao = userDao;
        this.transferDao = transferDao;
    }
        @RequestMapping( value = "/account/balance", method = RequestMethod.GET)
                public BigDecimal getBalance(Principal principal){
            int userId = getCurrentUserId(principal);
            return accountDao.getBalance(userId);
        }
        @RequestMapping(value = "/transfer", method = RequestMethod.GET)
        public List<Transfer> getTransfer(Principal principal){
        return transferDao.getTransferHistory();
        }

    private int getCurrentUserId(Principal principal){
        return userDao.findByUsername(principal.getName()).getId();
    }

}