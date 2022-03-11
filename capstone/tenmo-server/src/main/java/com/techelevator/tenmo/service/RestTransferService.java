package com.techelevator.tenmo.service;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTransferService implements TransferService{

    private static final String API_URL = "http://localhost:8080/transfer";
    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public Transfer getTransfer() throws RestClientResponseException {
        Transfer transfer = restTemplate.getForObject(API_URL, Transfer.class);
        return transfer;
    }
}
