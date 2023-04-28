package com.istad.dataanalyticrestfulapi.service;

import com.istad.dataanalyticrestfulapi.model.User;
import com.istad.dataanalyticrestfulapi.model.UserAccount;
import com.istad.dataanalyticrestfulapi.model.request.UserRequest;

import java.util.List;

public interface UserService {
    List<User> allUsers();
    List<User> findUserByName();
    User findUserByID(int id );

    int createNewUser(UserRequest user);
    int updateUser(UserRequest user, int id) ;
    int removeUser(int id);


    List<UserAccount> getAllUserAccounts();

}
