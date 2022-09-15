package com.mymoney.portfolio.command;

import com.mymoney.portfolio.enums.TransactionType;

import java.time.LocalDate;

public class CommandFactory {
    public static TransactionCommand getCommand(TransactionType transactionType, LocalDate transactionDate, Double equityShare, Double debtShare, Double goldShare){
        switch(transactionType) {
            case ALLOCATE: return new AllocateTransactionCommand(equityShare, debtShare, goldShare, transactionDate);
            case SIP: return new SIPTransactionCommand(equityShare, debtShare, goldShare, transactionDate);
            case CHANGE: return new MarketChangeTransactionCommand(equityShare, debtShare, goldShare, transactionDate);
            case BALANCE: return new BalanceTransactionCommand(transactionDate);
            case REBALANCE: return new RebalanceTransactionCommand();
            default: throw new IllegalArgumentException("This " + transactionType.toString() + " is not supported");
        }
    }
}