package com.java.rx.customer.model;

public class TransactionDto {
    private String descriptiom;
    private double amount;

    public TransactionDto(String descriptiom, double amount) {
        this.descriptiom = descriptiom;
        this.amount = amount;
    }
    public TransactionDto(){
    }

    public String getDescriptiom() {
        return descriptiom;
    }

    public void setDescriptiom(String descriptiom) {
        this.descriptiom = descriptiom;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
