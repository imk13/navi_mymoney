package com.mymoney.portfolio.service;

import com.mymoney.portfolio.command.TransactionCommand;
import com.mymoney.portfolio.enums.AssetClassType;
import com.mymoney.portfolio.models.AssetClass;
import com.mymoney.portfolio.models.Portfolio;
import com.mymoney.portfolio.utils.DecimalFormatter;

import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.stream.Collectors.groupingBy;

public class ApplyShowBalanceMoneyActivity implements ApplyMoneyActivity {
    static Logger logger = Logger.getLogger(ApplyShowBalanceMoneyActivity.class.getName());
    @Override
    public void execute(Portfolio portfolio, TransactionCommand activityTransactionCommand) {
        Map<AssetClassType, List<AssetClass>> assetClassTypeMap = portfolio.getAssetClassList().stream()
                .collect(groupingBy(AssetClass::getAssetClassType));
        Map<Integer, String> assetClassBalance = new HashMap<>();
        Month transactionMonth = activityTransactionCommand.getDate().getMonth();
        assetClassTypeMap.forEach((assetClassType, assetClasses) -> {
            assetClassBalance.put(assetClassType.value, "" + DecimalFormatter.getDoubleValue(assetClasses.get(0).getLastBalanceOfMonth(transactionMonth)));
        });
        logger.log(Level.INFO, String.join(" ", assetClassBalance.values()));
    }
}
