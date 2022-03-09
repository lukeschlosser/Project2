package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import org.springframework.web.client.RestTemplate;

public interface AccountSvcs {

    Account getAccount();

    Account updateAccount();
}
