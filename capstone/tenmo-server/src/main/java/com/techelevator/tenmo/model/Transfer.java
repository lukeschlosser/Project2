package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transfer {
    private int transferId;
    private int senderId;
    private int recipientId;
    private BigDecimal transferAmt;
    private int TransferType;
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
