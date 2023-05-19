package com.istad.dataanalyticrestfulapi.repository;


import com.istad.dataanalyticrestfulapi.model.Account;
import com.istad.dataanalyticrestfulapi.model.User;
import com.istad.dataanalyticrestfulapi.model.UserAccount;
import com.istad.dataanalyticrestfulapi.model.request.UserRequest;
import com.istad.dataanalyticrestfulapi.repository.provider.UserProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


// 1. loop queries
// 2. providers
// 3. pagination
// 4. file upload
// 4.1 upload file
// 4.2 multiple upload
// 4.3 exception for file upload
@Mapper
@Repository
public interface UserRepository {

//    @Select("select * from users_tb")


    @Result(column = "id", property = "userId")
    @SelectProvider(type = UserProvider.class, method = "getAllUsers")
    List<User> allUsers(String filterName);


    @Select("select * from users_tb where username like #{username}")
    @Result(property = "password", column = "secret_key")
    @Result(property = "userId", column = "id")
    List<User> findUserByUsername(String username);


    @Select("insert into users_tb (username, gender, address)\n" +
            "values (#{user.username},#{user.gender}, #{user.address}) returning id")
    int createNewUser(@Param("user") UserRequest user);


    @Update("update users_tb set username=#{user.username},\n" +
            "                    gender=#{user.gender},\n" +
            "                    address =#{user.address}\n" +
            "where  id = #{id};")
    int updateUser(@Param("user") UserRequest user, int id);

    @Result(property = "userId", column = "id")
    @Select("select  * from users_tb where id = #{id}")
    User findUserByID(int id);


    @Delete("delete  from users_tb where id = #{id}")
    int removeUser(int id);


    @Results({
            @Result(column = "id", property = "userId"),
            @Result(column = "id", property = "accounts", many = @Many(select = "findAccountsByUserId"))
    })
    @Select("select * from users_tb")
    List<UserAccount> getAllUserAccounts();


    @Results({
            @Result(property = "accountName", column = "account_name"),
            @Result(property = "accountNumber", column = "account_no"),
            @Result(property = "transferLimit", column = "transfer_limit"),
            @Result(property = "password", column = "passcode"),
            @Result(property = "accountType", column = "account_type",
                    one = @One(select = "com.istad.dataanalyticrestfulapi.repository.AccountRepository.getAccountTypeByID"))
    })
    @Select("select * from user_account_tb " +
            "    inner join account_tb " +
            "        a on a.id = user_account_tb.account_id\n" +
            "    where user_id = #{id};")
    List<Account> findAccountsByUserId(int id);


}
