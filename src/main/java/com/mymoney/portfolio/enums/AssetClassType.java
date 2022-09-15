package com.mymoney.portfolio.enums;

public enum AssetClassType {
    EQUITY(0), DEBT(1), GOLD(2);
    public final Integer value;

    private AssetClassType(Integer value) {
        this.value = value;
    }
}
