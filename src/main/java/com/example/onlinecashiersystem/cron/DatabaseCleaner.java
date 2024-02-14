package com.example.onlinecashiersystem.cron;

import com.example.onlinecashiersystem.service.api.ProductPlaneService;
import com.example.onlinecashiersystem.service.api.TransactionService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class DatabaseCleaner {
    private final Logger logger = Logger.getLogger(DatabaseCleaner.class.getName());
    private final TransactionService transactionService;
    private final ProductPlaneService productPlaneService;

    public DatabaseCleaner(TransactionService transactionService, ProductPlaneService productPlaneService) {
        this.transactionService = transactionService;
        this.productPlaneService = productPlaneService;
    }

    @Scheduled(cron = "0 0 * * * ?", zone = "Europe/Paris") // each minute
    public void deleteAllTransactions() {
        transactionService.deleteAllTransactions();
        logger.info("All transactions has been deleted!");
    }

    @Scheduled(cron = "0 0 3 * * ?", zone = "Europe/Paris") // every day at 3:00 AM (CET)
    public void deleteAllProductPlanes() {
        productPlaneService.deleteAllProductPlanes();
        logger.info("All product planes and their products has been deleted!");
    }
}
