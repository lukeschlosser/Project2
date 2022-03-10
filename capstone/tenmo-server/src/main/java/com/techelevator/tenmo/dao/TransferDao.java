package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.math.BigDecimal;
import java.util.List;

public interface TransferDao {
    List<Transfer> getTransferHistory();
    Transfer getTransferHistoryByTransferId();
    BigDecimal sendTransfer(BigDecimal transferAmt, int senderId, int recipientId);
    BigDecimal receiveTransfer(BigDecimal transferAmt, int senderId, int recipientId);
    boolean transferStatus(int transferId);



}
