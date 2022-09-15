package com.mymoney.portfolio.service;

import com.mymoney.portfolio.command.TransactionCommand;
import com.mymoney.portfolio.enums.TransactionType;
import com.mymoney.portfolio.models.Portfolio;
import com.mymoney.portfolio.repository.PortfolioRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PortfolioService {
    static Logger logger = Logger.getLogger(PortfolioService.class.getName());
    Map<TransactionType, ApplyMoneyActivity> applyMoneyActivityMap = new HashMap<>();
    public PortfolioService(){
            applyMoneyActivityMap.put(TransactionType.ALLOCATE, new ApplyAllocationMoneyActivity());
            applyMoneyActivityMap.put(TransactionType.SIP, new ApplySIPMoneyActivity());
            applyMoneyActivityMap.put(TransactionType.CHANGE, new ApplyMarketChangeActivity());
            applyMoneyActivityMap.put(TransactionType.REBALANCE, new ApplyReBalanceMoneyActivity());
            applyMoneyActivityMap.put(TransactionType.BALANCE, new ApplyShowBalanceMoneyActivity());
    }

    public void createPortfolio(String userId){
        Portfolio userPortfolio = new Portfolio(userId);
        PortfolioRepository.userPortfolioMap.putIfAbsent(userId, userPortfolio);
    }
    public Portfolio getUserPortfolio(String userId){
        try{
            return PortfolioRepository.userPortfolioMap.get(userId);
        }catch (Exception ex){
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            throw ex;
        }
    }

    public void executeTransactions(TransactionType transactionType, String userId, TransactionCommand transactionCommand) {
        try{
            applyMoneyActivityMap.get(transactionType).execute(getUserPortfolio(userId), transactionCommand);
        }catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    public double getPortfolioBalance(String userId) {
        return getUserPortfolio(userId).getBalance();
    }
}
