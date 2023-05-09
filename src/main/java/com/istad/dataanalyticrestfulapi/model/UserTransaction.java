package com.istad.dataanalyticrestfulapi.model;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserTransaction {
    private int accountId;
    private String accountNumber;
//    private String gender;
    private User user;
    // user.gender


}
