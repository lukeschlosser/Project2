package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransferDao implements TransferDao{

    private JdbcTemplate jdbcTemplate;
                                                                                          //TODO transfer_type_id
    private static final int REQUEST = 1;
    private static final int SEND = 2;
                                                                                          //TODO transfer_status_id
    private static final int PENDING = 1;
    private static final int APPROVED = 2;
    private static final int REJECTED = 3;


    public JdbcTransferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Transfer getTransfer(int transferId) {
        Transfer transfer = null;
        String sql = "SELECT transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount FROM transfer WHERE transfer_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next()) {
            transfer = mapRowToTransfer(results);
        }
        return transfer;
    }


    @Override
    public List<Transfer> getTransferHistory(int accountId) {
        List<Transfer> transfers = new ArrayList<>();
        String sql = "SELECT transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount FROM transfer WHERE account_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next()) {
            transfers.add(mapRowToTransfer(results));
        }
        return transfers;
    }


    @Override
    public List<Transfer> getTransferHistoryByTransferId(int transferId) {
        List<Transfer> transfers = new ArrayList<>();
        String sql = "SELECT transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount FROM transfer WHERE transfer_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, transferId);

        while (results.next()) {
            transfers.add(mapRowToTransfer(results));
        }
        return transfers;
    }


    @Override
    public boolean getTransferStatus(int transferId) {
        boolean status = false;
        String sql = "SELECT transfer_status_id FROM transfer WHERE transfer_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, transferId);

        if (results.equals(true)) {
            status = true;
        }
        return status;
    }


    @Override
    public Transfer logTransfer(Transfer transfer) {
        Transfer transfer1 = new Transfer();
        String sql = "INSERT INTO transfer(transfer_type_id, transfer_status_id, account_from, account_to, amount) VALUES(?, ?, ?, ?, ?) RETURNING transfer_id;";
        int newTransferId = jdbcTemplate.update(sql, transfer.getTransferType(), transfer.getTransferStatusId(), transfer.getTransferAmt(),
                transfer.getRecipientId(), transfer.getSenderId());
        transfer1 = getTransfer(newTransferId);
        return transfer1;

    }


//    @Override
//    public boolean create(String username, String password) {                                           //TODO convert to logTransfer
//        String sql = "INSERT INTO tenmo_user (username, password_hash) VALUES (?, ?) RETURNING user_id";
//        String password_hash = new BCryptPasswordEncoder().encode(password);
//        Integer newUserId;
//        try {
//            newUserId = jdbcTemplate.queryForObject(sql, Integer.class, username, password_hash);
//        } catch (DataAccessException e) {
//            return false;
//        }
//
//        sql = "INSERT INTO account (user_id, balance) values(?, ?)";
//        try {
//            jdbcTemplate.update(sql, newUserId, STARTING_BALANCE);
//        } catch (DataAccessException e) {
//            return false;
//        }
//
//        return true;
//    }


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
//TODO make sure balance is > transferAmt   && Can't transfer to yourself