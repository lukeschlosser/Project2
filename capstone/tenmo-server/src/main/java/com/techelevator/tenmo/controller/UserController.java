package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//TODO @PreAuthorize("isAuthenticated()")
public class UserController {

    private AccountDao accountDao;
    private UserDao userDao;
    private TransferDao transferDao;


    public UserController(AccountDao accountDao, UserDao userDao, TransferDao transferDao) {
        this.accountDao = accountDao;
        this.userDao = userDao;
        this.transferDao = transferDao;
    }

//TODO @PreAuthorize("permitAll")
    @RequestMapping(path = "/tenmo_user", method = RequestMethod.GET)
    public List <User> listUsernameAndId(){
            return userDao.findAllUsers();
        }


}