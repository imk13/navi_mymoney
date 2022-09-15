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

public class ApplyAllocationMoneyActivity implements ApplyMoneyActivity {
    @Override
    public void execute(Portfolio portfolio, TransactionCommand activityTransactionCommand) {
        Map<AssetClassType, List<AssetClass>> assetClassTypeMap = portfolio.getAssetClassList().stream()
                .collect(groupingBy(AssetClass::getAssetClassType));

        // since this will be first transaction (allocation), so this will provide portfolio balance
        double portfolioBalance = activityTransactionCommand.getAssetClassAmountShareMap().values().stream().mapToDouble(Double::doubleValue).sum();

        activityTransactionCommand.getAssetClassAmountShareMap().forEach((assetClassType, transactionAmount) -> {
            AssetClass assetClass = assetClassTypeMap.get(assetClassType).get(0);
            assetClass.setShare(DecimalFormatter.getDoubleValue(transactionAmount / portfolioBalance, 2));
            assetClass.addTransaction(new Transaction(TransactionType.ALLOCATE, transactionAmount, activityTransactionCommand.getDate()));
        });
    }
}
