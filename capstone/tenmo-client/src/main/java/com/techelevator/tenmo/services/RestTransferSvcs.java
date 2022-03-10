package com.techelevator.tenmo.services;


import org.springframework.web.client.RestTemplate;

//@Component
public class RestTransferSvcs implements TransferSvcs {

    private static final String API_URL = "localhost::8080";                        //TODO
    private RestTemplate restTemplate = new RestTemplate();


//    @Override
//    public Transfer sendTransfer(){                                                //TODO    add param id
//        Transfer transfer = restTemplate.getForObject(API_URL, Transfer.class);
//        return transfer;
//    }
//
//
//    @Override
//    public Transfer acceptTransfer(){                                            //TODO    add param transfer
//        Transfer transfer = restTemplate.getForObject(API_URL, Transfer.class);
//        return transfer;
//    }


}
