package com.techelevator.tenmo.services;


import com.techelevator.tenmo.model.Transfer;
import com.techelevator.util.BasicLogger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;


public class RestTransferSvcs implements TransferSvcs {

    private static final String API_BASE_URL = "http://localhost:8080";
    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public Transfer getTransfer(int transferId){
        Transfer transfer = null;
        try {
            transfer = restTemplate.getForObject(API_BASE_URL + "/transfer/" + transferId, Transfer.class);
        } catch (RestClientResponseException rcre) {
            BasicLogger.log(rcre.getRawStatusCode() + " : " + rcre.getStatusText() );
        } catch (ResourceAccessException rae) {
            BasicLogger.log(rae.getMessage() );
        }   return transfer;
    }

    @Override
    public List<Transfer> getTransferHistory(int transferId){
        List<Transfer> transfers = new ArrayList<>();

        try {
            transfers.add(restTemplate.getForObject(API_BASE_URL + "/account/balance" + transferId , Transfer.class));
        } catch (RestClientResponseException rcre) {
            BasicLogger.log(rcre.getRawStatusCode() + " : " + rcre.getStatusText() );
        } catch (ResourceAccessException rae) {
            BasicLogger.log(rae.getMessage() );
        }   return transfers;
    }


    @Override
    public boolean updateTransfer(Transfer newTransfer){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Transfer> entity = new HttpEntity<>(newTransfer, headers);
        boolean result = false;

        try {
            restTemplate.put(API_BASE_URL + "/transfer/" + newTransfer.getTransferId(), entity);
            result = true;
        } catch (RestClientResponseException rcre) {
            BasicLogger.log(rcre.getRawStatusCode() + " : " + rcre.getStatusText() );
        } catch (ResourceAccessException rae) {
            BasicLogger.log(rae.getMessage());
        } return result;
    }


    @Override
    public Transfer createTransfer(Transfer newTransfer) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Transfer> entity = new HttpEntity<>(newTransfer, headers);

        Transfer returnedTransfer = null;
        try {
            returnedTransfer = restTemplate.postForObject(API_BASE_URL + "/transfer/", entity, Transfer.class);
        } catch (RestClientResponseException e) {
            BasicLogger.log(e.getRawStatusCode() + " : " + e.getStatusText());
        } catch (ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return returnedTransfer;
    }


}
