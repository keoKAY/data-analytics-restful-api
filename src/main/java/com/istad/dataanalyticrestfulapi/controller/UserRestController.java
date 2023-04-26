package com.istad.dataanalyticrestfulapi.controller;


import com.istad.dataanalyticrestfulapi.model.User;
import com.istad.dataanalyticrestfulapi.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRestController {

    // inject UserService
    private final UserService userService;

    UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/allusers")
    List<User> getAllUser() {
        return userService.allUsers();
    }

    @GetMapping("/{id}")
    public User findUserByID(@PathVariable int id) {
        return userService.findUserByID(id);
    }

    @PostMapping("/new-user")
    public String createUser(@RequestBody User user) {

        try {
            int affectRow = userService.createNewUser(user);
            System.out.println("Affect Row : "+ affectRow);
            if(affectRow>0)
                return "Create user successfully !";
            else
                return "Cannot create a new user ! ";
        } catch (Exception exception) {
            return exception.getMessage();

        }



    }


}
