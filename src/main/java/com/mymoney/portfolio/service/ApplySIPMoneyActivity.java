package com.mymoney.portfolio.service;

import com.mymoney.portfolio.command.TransactionCommand;
import com.mymoney.portfolio.enums.AssetClassType;
import com.mymoney.portfolio.models.AssetClass;
import com.mymoney.portfolio.models.Portfolio;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

public class ApplySIPMoneyActivity implements ApplyMoneyActivity {
    @Override
    public void execute(Portfolio portfolio, TransactionCommand activityTransactionCommand) {
        Map<AssetClassType, List<AssetClass>> assetClassTypeMap = portfolio.getAssetClassList().stream()
                .collect(groupingBy(AssetClass::getAssetClassType));

        activityTransactionCommand.getAssetClassAmountShareMap().forEach((assetClassType, transactionAmount) -> {
            AssetClass assetClass = assetClassTypeMap.get(assetClassType).get(0);
            assetClass.setSipAmount(transactionAmount);
        });
    }
}
