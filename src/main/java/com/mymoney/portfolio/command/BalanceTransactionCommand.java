package com.mymoney.portfolio.command;

import java.time.LocalDate;

public class BalanceTransactionCommand extends TransactionCommand {
    public BalanceTransactionCommand(LocalDate transactionDate) {
        super(transactionDate);
    }
}
