package com.mymoney.portfolio;

import com.mymoney.portfolio.command.TransactionCommand;
import com.mymoney.portfolio.command.CommandFactory;
import com.mymoney.portfolio.enums.AssetClassType;
import com.mymoney.portfolio.enums.TransactionType;
import com.mymoney.portfolio.models.Transaction;
import com.mymoney.portfolio.service.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    static Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) throws IOException {
        String filePath = args.length > 0 ? args[0] : "sample_input/input2.txt";

        //Sample code to read from file passed as command line argument
        String userId1 = UserService.createUser("Mike");
        PortfolioService portfolioService = new PortfolioService();

        portfolioService.createPortfolio(userId1);
        FileInputStream fis = null;
        try {
            // the file to be opened for reading
            fis = new FileInputStream(filePath);
            Scanner sc = new Scanner(fis); // file to be scanned
            // returns true if there is another line to read
            while (sc.hasNextLine()) {
               //Add your code here to process input commands
                List<String> transactionInfo = Arrays.asList(sc.nextLine().trim().split(" "));
                TransactionCommand activityTransactionCommand = buildActivityCommand(transactionInfo);
                TransactionType transactionType = TransactionType.valueOf(transactionInfo.get(0));
                portfolioService.executeTransactions(transactionType, userId1, activityTransactionCommand);
            }
            sc.close(); // closes the scanner
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw e;
        } finally {
            fis.close();
        }
    }

    static TransactionCommand buildActivityCommand(List<String> transactionInfo){

        try{
            TransactionType transactionType = TransactionType.valueOf(transactionInfo.get(0));
            Double equityAmount = 0.0;
            Double debtAmount = 0.0;
            Double goldAmount = 0.0;
            LocalDate transactionDate = LocalDate.now();
            Map<AssetClassType, Transaction> transactionMap = new HashMap<>();

            if(Arrays.asList(TransactionType.ALLOCATE, TransactionType.SIP, TransactionType.CHANGE).contains(transactionType)) {
                equityAmount = Double.valueOf(transactionInfo.get(1).replace("%", ""));
                debtAmount = Double.valueOf(transactionInfo.get(2).replace("%", ""));
                goldAmount = Double.valueOf(transactionInfo.get(3).replace("%", ""));
            }

            if(transactionType == TransactionType.CHANGE) {
                Month marketChangeMonth = Month.valueOf(transactionInfo.get(4));
                transactionDate = transactionDate.withMonth(marketChangeMonth.getValue());
            }

            if(transactionType == TransactionType.BALANCE) {
                Month marketChangeMonth = Month.valueOf(transactionInfo.get(1));
                transactionDate = transactionDate.withMonth(marketChangeMonth.getValue());
            }

            return CommandFactory.getCommand(transactionType, transactionDate, equityAmount, debtAmount, goldAmount);
        }catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            throw ex;
        }
    }
}



