package com.istad.dataanalyticrestfulapi.mapper;


import com.istad.dataanalyticrestfulapi.model.Account;
import com.istad.dataanalyticrestfulapi.model.response.AccountResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AutoAccountMapper {

    // account -> account-response
    List<AccountResponse > mapToAccountResponse(List<Account> accounts);
    // account-response -> account
    List<Account>mapToAccount(List<AccountResponse> responses);

}
