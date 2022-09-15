package com.mymoney.portfolio.service;

import com.mymoney.portfolio.command.BalanceTransactionCommand;
import com.mymoney.portfolio.command.TransactionCommand;
import com.mymoney.portfolio.enums.AssetClassType;
import com.mymoney.portfolio.enums.TransactionType;
import com.mymoney.portfolio.models.AssetClass;
import com.mymoney.portfolio.models.Portfolio;
import com.mymoney.portfolio.models.Transaction;
import com.mymoney.portfolio.utils.DecimalFormatter;

import java.time.Month;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class ApplyReBalanceMoneyActivity implements ApplyMoneyActivity {
    static Logger logger = Logger.getLogger(ApplyReBalanceMoneyActivity.class.getName());
    @Override
    public void execute(Portfolio portfolio, TransactionCommand activityTransactionCommand) {
        Double portfolioBalance = portfolio.getBalance();
        for(AssetClass assetClass : portfolio.getAssetClassList()) {
            int len = assetClass.getTransactions().size();
            Transaction lastTransaction = assetClass.getTransactions().get(len-1);
            activityTransactionCommand.setDate(lastTransaction.getTransactionDate());
            if(len >= 6 && (lastTransaction.getTransactionDate().getMonth() == Month.JUNE || lastTransaction.getTransactionDate().getMonth() == Month.DECEMBER)) {
                Transaction reBalancedTransaction = new Transaction(TransactionType.REBALANCE, DecimalFormatter.getDoubleValue(portfolioBalance * assetClass.getShare()), activityTransactionCommand.getDate());
                assetClass.addTransaction(reBalancedTransaction);
            }else{
                System.out.println("CANNOT_REBALANCE");
                return;
            }
        }
        //logger.log(Level.INFO, "Balance after Re-Balancing of portfolio");
        new ApplyShowBalanceMoneyActivity().execute(portfolio, activityTransactionCommand);
    }
}
