package com.mymoney.portfolio.service;

import com.mymoney.portfolio.command.TransactionCommand;
import com.mymoney.portfolio.models.Portfolio;

public interface ApplyMoneyActivity {
    public void execute(Portfolio portfolio, TransactionCommand activityTransactionCommand);
}
