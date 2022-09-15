package com.mymoney.portfolio.service;

import com.mymoney.portfolio.command.TransactionCommand;
import com.mymoney.portfolio.enums.AssetClassType;
import com.mymoney.portfolio.enums.TransactionType;
import com.mymoney.portfolio.models.AssetClass;
import com.mymoney.portfolio.models.Portfolio;
import com.mymoney.portfolio.models.Transaction;
import com.mymoney.portfolio.utils.DecimalFormatter;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

public class ApplyMarketChangeActivity implements ApplyMoneyActivity {
    @Override
    public void execute(Portfolio portfolio, TransactionCommand activityTransactionCommand) {
        Map<AssetClassType, List<AssetClass>> assetClassTypeMap = portfolio.getAssetClassList().stream()
                .collect(groupingBy(AssetClass::getAssetClassType));

        activityTransactionCommand.getAssetClassAmountShareMap().forEach((assetClassType, transactionAmount) -> {
            if(transactionAmount < -100 || transactionAmount > 100) {
                throw new IllegalArgumentException("For " + TransactionType.CHANGE +  " amount (%) should be in -100 to 100");
            }

            AssetClass assetClass = assetClassTypeMap.get(assetClassType).get(0);
            // First add SIP transaction and then apply market change
            if(assetClass.getTransactions().size() > 1){
                assetClass.addTransaction(new Transaction(TransactionType.SIP, DecimalFormatter.getDoubleValue(assetClass.getBalance() + assetClass.getSipAmount()), activityTransactionCommand.getDate()));
            }

            Double newMarketChangeAmount = DecimalFormatter.getDoubleValue(assetClass.getBalance() * DecimalFormatter.getDoubleValue(transactionAmount / 100, 4));
            Transaction newMarketTransaction = new Transaction(TransactionType.CHANGE, assetClass.getBalance() + newMarketChangeAmount, activityTransactionCommand.getDate());

            assetClass.addTransaction(newMarketTransaction);
        });
    }
}
