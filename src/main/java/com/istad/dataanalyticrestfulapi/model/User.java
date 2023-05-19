package com.istad.dataanalyticrestfulapi.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class User {
    private int userId;
    private String username;
    private String gender;
    private String address;
    // new additional field...
    private String email;
    private String password;



}
