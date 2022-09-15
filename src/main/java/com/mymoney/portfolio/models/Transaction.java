package com.mymoney.portfolio.models;

import com.mymoney.portfolio.enums.TransactionType;

import java.time.LocalDate;

public class Transaction {
    TransactionType transactionType;
    double amount;
    LocalDate transactionDate;

    public Transaction(TransactionType transactionType, double value, LocalDate transactionDate) {
        this.transactionType = transactionType;
        this.amount = value;
        this.transactionDate = transactionDate;
    }

    public Transaction(TransactionType transactionType, double value) {
        this.transactionType = transactionType;
        this.amount = value;
        this.transactionDate = LocalDate.now();
    }

    public double getAmount() {
        return amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }
}
