package com.techelevator.tenmo.model;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public class Transfer {

    @Positive(message = "Transfer Id cannot be empty")
    private int transferId;
    @Positive(message = "No one is listed as the transfer sender")
    private int senderId;
    @PositiveOrZero(message = "No one is listed as the recipient sender")
    private int recipientId;
    @DecimalMin(value= "0.01", message = "Transfer Amount  must be greater than $0.01")
    private BigDecimal transferAmt;
    @Positive(message = "Transfer Type  must be a positive value")
    private int TransferType;
    @Positive(message = "Transfer Status Id must be a positive value")
    private int TransferStatusId;

    public Transfer() {}

    public Transfer(int transferId, int senderId, int recipientId, BigDecimal transferAmt) {
        this.transferId = transferId;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.transferAmt = transferAmt;
    }

    public int getTransferId() {return transferId;}

    public int getSenderId() {
        return senderId;
    }


    public int getRecipientId() {
        return recipientId;
    }


    public BigDecimal getTransferAmt() {
        return transferAmt;
    }

    public int getTransferType() {
        return TransferType;
    }

    public int getTransferStatusId() {
        return TransferStatusId;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }

    public void setTransferType(int transferType) {
        TransferType = transferType;
    }

    public void setTransferStatusId(int transferStatusId) {
        TransferStatusId = transferStatusId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }


    public void setRecipientId(int recipientId) {
        this.recipientId = recipientId;
    }


    public void setTransferAmt(BigDecimal transferAmt) {
        this.transferAmt = transferAmt;
    }

}
