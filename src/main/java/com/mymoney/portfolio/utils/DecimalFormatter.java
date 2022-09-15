package com.mymoney.portfolio.utils;

import java.text.DecimalFormat;

public class DecimalFormatter {

    public static Double getDoubleValue(Double value){
        return getDoubleValue(value, 3);
    }
    public static Double getDoubleValue(Double value, int n) {
        String postDecimalZeros = new String(new char[n-1]).replace("\0", "0");
        DecimalFormat df = new DecimalFormat("0.0" + postDecimalZeros);
        String formatted = df.format(value);
        return Double.valueOf(formatted) ;
    }
}
