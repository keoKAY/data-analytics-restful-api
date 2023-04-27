package com.istad.dataanalyticrestfulapi.repository;


import com.istad.dataanalyticrestfulapi.model.Account;
import com.istad.dataanalyticrestfulapi.model.AccountType;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AccountRepository {

    @Results({
            @Result(property ="password" , column = "passcode"),
            @Result(property = "accountName", column = "account_name"),
            @Result(property = "accountNumber", column = "account_no"),
            @Result(property = "transferLimit", column = "transfer_limit"),
            @Result(column = "account_type", property = "accountType", one = @One(select = "getAccountTypeByID"))
    })
    @Select("select * from account_tb")
    List<Account> getAllAccounts();

    @Result(property = "accountName", column = "name")
    @Select("select * from accounttype_tb where id=#{account_type}")
    AccountType getAccountTypeByID(int account_type);


    int createAccount(Account account  );
    int updateAccount(Account account, int id);
    Account findAccountByID(int id);
}
