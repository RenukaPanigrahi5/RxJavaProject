package com.rx.aggregator.model;

import java.util.Arrays;
import java.util.List;

public class AccountDto {

    private int id;
    private String accountNumber;
    private double balance;
    private List<TransactionDto> transactions;
    public AccountDto() {
    }

    public AccountDto(int id, String accountNumber, double balance) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public AccountDto withTransactions(TransactionDto... txns){
        this.transactions = Arrays.asList(txns);
        return this;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<TransactionDto> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDto> transactions) {
        this.transactions = transactions;
    }
}
