package com.istad.dataanalyticrestfulapi.controller;


import com.istad.dataanalyticrestfulapi.model.User;
import com.istad.dataanalyticrestfulapi.model.UserAccount;
import com.istad.dataanalyticrestfulapi.model.request.UserRequest;
import com.istad.dataanalyticrestfulapi.service.UserService;
import com.istad.dataanalyticrestfulapi.utils.Response;
import jakarta.validation.Valid;
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
    public Response<List<User>> getAllUser() {
        try {
            List<User> response = userService.allUsers();
            return Response.<List<User>>ok().setPayload(response).setMessage("Successfully retrieved all users ! ");

        } catch (Exception ex) {
            return Response.<List<User>>exception().setMessage("Failed to retrieved the users ! Exception occurred ! ");
        }

    }

    @GetMapping("/{id}")
    public Response<User> findUserByID(@PathVariable int id) {
        try {
            User response = userService.findUserByID(id);
            if (response != null) {
                return Response.<User>ok().setPayload(response).setSuccess(true).setMessage("Successfully retrieved user with id = " + id);
            } else {
                return Response.<User>notFound().setMessage("User with id =" + id + " doesn't exist ").setSuccess(false);
            }
        } catch (Exception ex) {
            return Response.<User>exception().setMessage("Failed to retrieved user with id =" + id);
        }

    }

    @PostMapping("/new-user")
    public Response<User> createUser(@Valid @RequestBody UserRequest request) {

        try {
            int userID = userService.createNewUser(request);
            if (userID > 0) {

                User response = new User().setUsername(request.getUsername())
                        .setAddress(request.getAddress())
                        .setGender(request.getGender()).setUserId(userID);

                return Response.<User>createSuccess().setPayload(response).setMessage("Create User Successfully").setSuccess(true);
            } else {
                return Response.<User>badRequest().setMessage("Bad Request ! Failed to create user");
            }


        } catch (Exception ex) {

            return Response.<User>exception().setMessage("Exception occurs! Failed to create a new user ").setSuccess(false);
        }


    }

    @GetMapping("/user-accounts")
    public Response<List<UserAccount>> getAllUserAccounts() {
        try {
            List<UserAccount> data = userService.getAllUserAccounts();
            return Response.<List<UserAccount>>ok().setPayload(data).setMessage("Successfully retrieved all user accounts !");

        } catch (Exception ex) {
            return Response.<List<UserAccount>>exception().setMessage("Exception occurs ! Failed to retrieved all users accounts!")
                    .setSuccess(false);
        }
    }
    // method for update the user

    @PutMapping("/{id}")
    public Response<User> updateUserByID(@PathVariable int id, @RequestBody UserRequest request) {

        try {
            int affectedRow = userService.updateUser(request, id);
            if (affectedRow > 0) {
                User response = new User().setUserId(id).setUsername(request.getUsername())
                        .setAddress(request.getAddress()).setGender(request.getGender());

                return Response.<User>updateSuccess().setMessage("Successfully update the user ")
                        .setPayload(response).setSuccess(true);
            } else {
                return Response.<User>notFound().setMessage("Cannot update , user with id = " + id + " doesn't exist ! ").setSuccess(false);
            }
        } catch (Exception ex) {
            return Response.<User>exception().setMessage("Failed to update user , Exception Occurred!");
        }

    }

    // delete user account
    @DeleteMapping("/{id}")
    public Response<?> deleteUser(@PathVariable int id) {
        try {
            int affectedRow = userService.removeUser(id);
            if (affectedRow > 0) {
                // delete success
                return Response.<Object>deleteSuccess().setMessage("Successfully remove the user ! ").setSuccess(true);
            } else {
                // id do not exist !
                return Response.<Object>notFound().setMessage("User with id =" + id + " doesn't exist in our system !");
            }

        } catch (Exception ex) {
            return Response.<Object>exception().setMessage("Exception occurred! Failed to delete the user !").setSuccess(false);
        }
    }


}
