package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcAccountDao implements AccountDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Account getBalance(int userId) {
        Account balance = null;
        String sql = "SELECT * FROM account WHERE user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);

        if(results.next()){
            balance = mapRowToAccount(results);
        }
        return balance;
    }




    @Override
    public Account getAccount(int userId) {
        Account account = null;
        String sql = "SELECT account_id, user_id, balance FROM account WHERE user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);

        if(results.next()){
            account = mapRowToAccount(results);
        }
        return account;
    }


    @Override
    public int getAccountIdByUserId(int userId) {
        int accountId = 0;
        String sql = "SELECT account_id FROM account WHERE user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);

        if(results.next()){
            accountId = mapRowToAccount(results).getAccountId();
        }
        return accountId;
    }


    @Override
    public List<Account> getAll() {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT account_id, user_id, balance FROM account;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next()) {
            accounts.add(mapRowToAccount(results));
        }
        return accounts;
    }


    @Override
    public List<Account> getAllAccountsByUserId(int userId) {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT account_id, user_id, balance FROM account WHERE user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next()) {
            accounts.add(mapRowToAccount(results));
        }
        return accounts;
    }

    @Override
    public void updateAccFrom(BigDecimal newBalance, int accountFrom){
        String sql = "UPDATE account AS a SET a.balance = ? JOIN transfer AS t ON a.account_id = t.account_from WHERE t.account_from = ?;";
        jdbcTemplate.update(sql, newBalance, accountFrom);
    }


    @Override
    public void updateAccTo(BigDecimal newBalance, int accountTo){
        String sql = "UPDATE account AS a SET a.balance = ? JOIN transfer AS t ON a.account_id = t.account_to WHERE t.account_to = ?;";
        jdbcTemplate.update(sql, newBalance, accountTo);
    }




    private Account mapRowToAccount(SqlRowSet rs) {
        Account account = new Account();
        account.setAccountId(rs.getInt("account_id"));
        account.setUserId(rs.getInt("user_id"));
        account.setBalance(rs.getBigDecimal("balance"));
        return account;
    }



}
