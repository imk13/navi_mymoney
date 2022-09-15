package com.mymoney.portfolio.command;

import com.mymoney.portfolio.enums.AssetClassType;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public abstract class TransactionCommand {
    Map<AssetClassType, Double> assetClassAmountShareMap = new HashMap<>();
    LocalDate date;

    public TransactionCommand(){
        this.date = LocalDate.now();
    }

    public TransactionCommand(LocalDate transactionDate){
        this.date = transactionDate;
    }

    public TransactionCommand(Double equityShare, Double debtShare, Double goldShare, LocalDate transactionDate){
        assetClassAmountShareMap.put(AssetClassType.EQUITY, equityShare);
        assetClassAmountShareMap.put(AssetClassType.DEBT, debtShare);
        assetClassAmountShareMap.put(AssetClassType.GOLD, goldShare);
        this.date = transactionDate;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Map<AssetClassType, Double> getAssetClassAmountShareMap() {
        return assetClassAmountShareMap;
    }
}
