package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.math.BigDecimal;
import java.util.List;

public interface TransferDao {

    public Transfer getTransfer(int transferId);


    boolean getTransferStatus(int transferId);


    List<Transfer> getTransferHistory();


    List<Transfer> getTransferHistoryByTransferId(int transferId);


    Transfer logTransfer(Transfer transfer);


}
