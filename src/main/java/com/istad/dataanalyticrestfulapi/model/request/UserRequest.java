package com.istad.dataanalyticrestfulapi.model.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class UserRequest {
    private String username;
    private String gender;
    private String address;
}
