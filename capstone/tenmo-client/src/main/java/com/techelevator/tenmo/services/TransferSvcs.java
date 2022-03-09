package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Transfer;

public interface TransferSvcs {

    Transfer sendTransfer();

    Transfer acceptTransfer();
}
