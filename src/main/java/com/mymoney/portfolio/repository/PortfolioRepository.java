package com.mymoney.portfolio.repository;

import com.mymoney.portfolio.models.Portfolio;

import java.util.HashMap;
import java.util.Map;

public class PortfolioRepository {
    // userId -> Portfolio
    public static Map<String, Portfolio> userPortfolioMap = new HashMap();
}
