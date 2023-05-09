package com.istad.dataanalyticrestfulapi.service;

import com.istad.dataanalyticrestfulapi.model.Transaction;

import java.util.List;

public interface TransactionService {
    // features of  the application / website
    List<Transaction> getAllTransactions();
    int createTransaction(Transaction transaction);
    int deleteTransaction(int transactionId);
    List<Transaction> getTransactionByUserId(int userId);


}
