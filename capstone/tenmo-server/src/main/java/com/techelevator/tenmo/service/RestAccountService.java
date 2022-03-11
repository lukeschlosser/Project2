package com.techelevator.tenmo.service;

import com.techelevator.tenmo.model.Account;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

@Component
public class RestAccountService implements AccountService{

    private static final String API_URL = "http://localhost:8080/account";
    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public Account getAccount() throws RestClientResponseException {
        Account account = restTemplate.getForObject(API_URL, Account.class);
        return account;
    }
}
