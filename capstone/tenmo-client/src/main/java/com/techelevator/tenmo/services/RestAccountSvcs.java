package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.util.BasicLogger;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import java.math.BigDecimal;


public class RestAccountSvcs implements AccountSvcs {


    private static final String API_BASE_URL = "http://localhost:8080";
    private RestTemplate restTemplate = new RestTemplate();


    public Account getAccount(int accountId){
        Account account = null;
        try {
            account = restTemplate.getForObject(API_BASE_URL + "/account/" + accountId, Account.class);
        } catch (RestClientResponseException rcre) {
            BasicLogger.log(rcre.getRawStatusCode() + " : " + rcre.getStatusText() );
        } catch (ResourceAccessException rae) {
            BasicLogger.log(rae.getMessage() );
    }   return account;
    }


    public BigDecimal getBalance(AuthenticatedUser user){
        HttpEntity<AuthenticatedUser> entity = createCredentialsEntity(user);

        BigDecimal balance = null;

        try {
            ResponseEntity<Account> response = restTemplate.exchange(API_BASE_URL + "/account/balance/"
                    + user.getUser().getId(), HttpMethod.GET, entity, Account.class);
            balance = response.getBody().getBalance();
        } catch (RestClientResponseException rcre) {
            BasicLogger.log(rcre.getRawStatusCode() + " : " + rcre.getStatusText() );
        } catch (ResourceAccessException rae) {
            BasicLogger.log(rae.getMessage() );
        }   return balance;
    }

    private HttpEntity<AuthenticatedUser> createCredentialsEntity(AuthenticatedUser user) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(user, headers);
    }

    public boolean updateAccount(Account newAccount){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Account> entity = new HttpEntity<>(newAccount, headers);
        boolean result = false;

        try {
            restTemplate.put(API_BASE_URL + "/account/balance/{id}" + newAccount.getAccountId(), entity);
            result = true;
        } catch (RestClientResponseException rcre) {
            BasicLogger.log(rcre.getRawStatusCode() + " : " + rcre.getStatusText() );
        } catch (ResourceAccessException rae) {
            BasicLogger.log(rae.getMessage());
        } return result;
    }


    public Account createAccount(Account newAccount) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Account> entity = new HttpEntity<>(newAccount, headers);

        Account returnedAccount = null;
        try {
            returnedAccount = restTemplate.postForObject(API_BASE_URL + "/account/", entity, Account.class);
        } catch (RestClientResponseException e) {
            BasicLogger.log(e.getRawStatusCode() + " : " + e.getStatusText());
        } catch (ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return returnedAccount;
    }



}
