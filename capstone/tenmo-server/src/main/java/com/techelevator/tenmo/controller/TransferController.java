package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
public class TransferController {

    private UserDao userDao;
    private TransferDao transferDao;

    public TransferController(UserDao userDao, TransferDao transferDao) {
        this.userDao = userDao;
        this.transferDao = transferDao;
    }

    @PreAuthorize("permitAll")
    @RequestMapping(value = "/transfer", method = RequestMethod.GET)
    public List<Transfer> getTransfer(Principal principal){

        return transferDao.getTransferHistory();
    }

    @PreAuthorize("permitAll")
    @RequestMapping(value = "/transfer/{id}", method = RequestMethod.GET)
    public List<Transfer> getTransferHistoryByStatus(int transferId){
        return transferDao.getTransferHistoryByTransferId(transferId);
    }

    @PreAuthorize("permitAll")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/transfer/send", method = RequestMethod.POST)
    public Transfer create(@Valid @RequestBody Transfer transfer){
        return transferDao.logTransfer(transfer);
    }


    private int getCurrentUserId(Principal principal){

        return userDao.findByUsername(principal.getName()).getId();
    }

}
