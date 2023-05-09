package com.istad.dataanalyticrestfulapi.service.serviceImpl;


import com.istad.dataanalyticrestfulapi.model.Transaction;
import com.istad.dataanalyticrestfulapi.repository.TransactionRepository;
import com.istad.dataanalyticrestfulapi.repository.provider.TransactionProvider;
import com.istad.dataanalyticrestfulapi.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    @Autowired
    TransactionServiceImpl(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }
    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.getAllTransactions();
    }

    @Override
    public int createTransaction(Transaction transaction) {
        return 0;
    }

    @Override
    public int deleteTransaction(int transactionId) {
        return 0;
    }

    @Override
    public List<Transaction> getTransactionByUserId(int userId) {
        return null;
    }
}
