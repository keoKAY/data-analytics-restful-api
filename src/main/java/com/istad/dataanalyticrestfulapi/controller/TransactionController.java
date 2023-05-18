package com.istad.dataanalyticrestfulapi.controller;

import com.istad.dataanalyticrestfulapi.model.Transaction;
import com.istad.dataanalyticrestfulapi.repository.provider.TransactionProvider;
import com.istad.dataanalyticrestfulapi.service.TransactionService;
import com.istad.dataanalyticrestfulapi.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {

    // inject service
    private final TransactionService transactionService;
    @Autowired
    TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }


    // getAllTransaction  -> Pagination
    @GetMapping("/all-transactions")
    public Response<List<Transaction>> getAllTransaction(){
        List<Transaction>  transactions = transactionService.getAllTransactions();

        return Response.<List<Transaction>>ok().setPayload(transactions)
                .setMessage("Successfully retrieved all the transaction..!");
    }



}
