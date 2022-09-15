package com.mymoney.portfolio.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class DecimalFormatter {

    public static Double getDoubleValue(Double value){
        return getDoubleValue(value, 1);
    }

    static DecimalFormat getDecimalFormatter(int n) {
        if(n <= 1)
            return new DecimalFormat("0");
        String postDecimalZeros = new String(new char[n-1]).replace("\0", "0");
        DecimalFormat df = new DecimalFormat("0.0" + postDecimalZeros);
        return df;
    }
    public static Double getDoubleValue(Double value, int n) {
        DecimalFormat df = getDecimalFormatter(n);
        df.setRoundingMode(RoundingMode.FLOOR);
        String formatted = df.format(value);
        return Double.valueOf(formatted) ;
    }
}
