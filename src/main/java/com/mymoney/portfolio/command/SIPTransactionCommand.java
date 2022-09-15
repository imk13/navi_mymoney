package com.mymoney.portfolio.command;

import java.time.LocalDate;

public class SIPTransactionCommand extends TransactionCommand {
    public SIPTransactionCommand(Double equityShare, Double debtShare, Double goldShare, LocalDate transactionDate) {
        super(equityShare, debtShare, goldShare, transactionDate);
    }
}
