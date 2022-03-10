package com.techelevator.tenmo.services;

import org.springframework.web.client.RestTemplate;


public class RestAccountSvcs implements AccountSvcs {


    private static final String API_URL = "localhost::8080";                        //TODO
    private RestTemplate restTemplate = new RestTemplate();


  /*  @Override
    public Account getAccount(){                                                //TODO    add param id
        Account account = restTemplate.getForObject(API_URL, Account.class);
        return account;
    }


    @Override
    public Account updateAccount(){                                                //TODO    add param id
        Account account = restTemplate.getForObject(API_URL, Account.class);
        return account;
    }*/


}
