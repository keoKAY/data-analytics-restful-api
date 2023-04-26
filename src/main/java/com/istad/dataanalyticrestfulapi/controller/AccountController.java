package com.istad.dataanalyticrestfulapi.controller;


import com.istad.dataanalyticrestfulapi.mapper.AutoAccountMapper;
import com.istad.dataanalyticrestfulapi.model.Account;
import com.istad.dataanalyticrestfulapi.model.response.AccountResponse;
import com.istad.dataanalyticrestfulapi.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    // inject account service
    final private AccountService accountService;
    final private AutoAccountMapper autoAccountMapper;

    AccountController(AccountService accountService, AutoAccountMapper autoAccountMapper) {
        this.accountService = accountService;
        this.autoAccountMapper = autoAccountMapper;
    }


    @GetMapping("/allaccounts")
    public ResponseEntity<?> getAllAccounts() {

        try {
            List<Account> allAccount = accountService.getAllAccounts();

            System.out.println("All Account  now : "+ allAccount);

            List<AccountResponse> accountResponses = autoAccountMapper.mapToAccountResponse(allAccount);
            HashMap<String, Object> response = new HashMap<>();
            response.put("payload", accountResponses);
            response.put("message", "Successfully retrieve all accounts info!");
            response.put("status", HttpStatus.OK);

            return ResponseEntity.ok().body(response);

        } catch (Exception ex) {
            System.out.println("Something wrong : " + ex.getMessage());
            return ResponseEntity.ok().body("Failed to retreived the acccounts ");
        }

    }


}
