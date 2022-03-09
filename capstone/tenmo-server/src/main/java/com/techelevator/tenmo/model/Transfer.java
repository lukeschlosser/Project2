package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transfer {
    private int senderId;
    private int recipientId;
    private BigDecimal transferAmt;

    public Transfer(int senderId, int recipientId, BigDecimal transferAmt) {
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.transferAmt = transferAmt;
    }

    public int getSenderId() {
        return senderId;
    }


    public int getRecipientId() {
        return recipientId;
    }


    public BigDecimal getTransferAmt() {
        return transferAmt;
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
