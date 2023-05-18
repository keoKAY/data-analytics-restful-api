package com.istad.dataanalyticrestfulapi.controller;


import com.istad.dataanalyticrestfulapi.utils.Response;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/authentication")
public class AuthenticationController {

    @PostMapping("/register")
    public Response<?> register(){
        return null;
    }

    @GetMapping("/login")
    public Response<?> login(){
        return null;
    }

    @PatchMapping("/resetPassword")
    public  Response<?> resetPassword(){
        return null;
    }

    // verify email , ....

}
