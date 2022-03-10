package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

//@Component
public class JdbcTransferDao implements TransferDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcTransferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Transfer> getTransferHistory() {
        return null;
    }

    @Override
    public Transfer getTransferHistoryByTransferId() {
        return null;
    }

    @Override
    public BigDecimal sendTransfer(BigDecimal transferAmt, int senderId, int recipientId) {
        return null;
    }

    @Override
    public BigDecimal receiveTransfer(BigDecimal transferAmt, int senderId, int recipientId) {
        return null;
    }

    @Override
    public boolean transferStatus(int transferId) {
        return false;
    }

    private Transfer mapRowToTransfer(SqlRowSet rs){
        Transfer transfer = new Transfer();
        transfer.setTransferId(rs.getInt("transfer_id"));
        transfer.setTransferType(rs.getInt("transfer_type_id"));
        transfer.setTransferStatusId(rs.getInt("transfer_status_id"));
        transfer.setSenderId(rs.getInt("account_from"));
        transfer.setRecipientId(rs.getInt("account_to"));
        transfer.setTransferAmt(rs.getBigDecimal("amount"));
        return transfer;
    }

}
//TODO make sure balance is > transferAmt
//Recepient NOT NULL
//Can't transfer to yourself