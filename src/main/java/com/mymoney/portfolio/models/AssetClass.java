package com.mymoney.portfolio.models;

import com.mymoney.portfolio.enums.AssetClassType;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public abstract class AssetClass {
    double share;
    double sipAmount;
    AssetClassType assetClassType;
    List<Transaction> transactions;

    public AssetClass(AssetClassType assetClassType) {
        this.share = 0;
        this.sipAmount = 0;
        this.assetClassType = assetClassType;
        this.transactions = new ArrayList<>();
    }
    public double getShare() {
        return share;
    }

    public double getSipAmount() {
        return sipAmount;
    }

    public double getBalance() {
        int len = transactions.size();
        if(len > 0){
            return transactions.get(len - 1).amount;
        }
        return 0;
    }

    public double getLastBalanceOfMonth(Month month) {
        int len = transactions.size();
        if(len > 0){
            return transactions.stream().filter(transaction -> {
                return transaction.transactionDate.getMonth() == month;
            }).reduce((a, b) -> b).get().amount; // find last matching
        }
        return 0;
    }

    public AssetClassType getAssetClassType() {
        return assetClassType;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void setShare(double share) {
        this.share = share;
    }

    public void setSipAmount(double sipAmount) {
        this.sipAmount = sipAmount;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
