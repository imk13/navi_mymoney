package com.mymoney.portfolio.command;

import java.time.LocalDate;

public class MarketChangeTransactionCommand extends TransactionCommand {
    public MarketChangeTransactionCommand(Double amountShare, Double debtShare, Double goldShare, LocalDate transactionDate) {
        super(amountShare, debtShare, goldShare, transactionDate);
    }
}
