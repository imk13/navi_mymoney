package com.mymoney.portfolio.command;

import java.time.LocalDate;

public class AllocateTransactionCommand extends TransactionCommand {
    public AllocateTransactionCommand(Double equityShare, Double debtShare, Double goldShare, LocalDate transactionDate) {
        super(equityShare, debtShare, goldShare, transactionDate);
    }
}
