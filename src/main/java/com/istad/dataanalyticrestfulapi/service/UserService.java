package com.istad.dataanalyticrestfulapi.service;

import com.github.pagehelper.PageInfo;
import com.istad.dataanalyticrestfulapi.model.User;
import com.istad.dataanalyticrestfulapi.model.UserAccount;
import com.istad.dataanalyticrestfulapi.model.request.UserRequest;

import java.util.List;

public interface UserService {
    PageInfo<User> allUsers(int page, int size, String filterName);
    List<User> findUserByName();
    User findUserByID(int id );

    int createNewUser(UserRequest user);
    int updateUser(UserRequest user, int id) ;
    int removeUser(int id);


    List<UserAccount> getAllUserAccounts();

}
