package com.mymoney.portfolio.models;

import com.mymoney.portfolio.enums.AssetClassType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Portfolio {
    String id;
    String userId;
    List<AssetClass> assetClassList;

    public Portfolio(String portfolioUserId){
        id = UUID.randomUUID().toString();
        userId = portfolioUserId;
        assetClassList = new ArrayList<>();
        assetClassList.add(new EquityAssetClass(AssetClassType.EQUITY));
        assetClassList.add(new DebtAssetClass(AssetClassType.DEBT));
        assetClassList.add(new GoldAssetClass(AssetClassType.GOLD));
    }

    public double getBalance() {
        return assetClassList.stream().map(AssetClass::getBalance).mapToDouble(Double::doubleValue).sum();
    }

    public List<AssetClass> getAssetClassList() {
        return assetClassList;
    }
}
