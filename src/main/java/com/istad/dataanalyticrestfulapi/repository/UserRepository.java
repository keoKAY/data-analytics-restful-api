package com.istad.dataanalyticrestfulapi.repository;


import com.istad.dataanalyticrestfulapi.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Mapper
@Repository
public interface UserRepository {




    @Select("select * from users_tb")
    @Result(column = "id", property = "userId")

    List<User> allUsers();
    List<User> findUserByUsername(String username);
    @Insert("insert into users_tb (username, gender, address)\n" +
            "values (#{user.username},#{user.gender}, #{user.address})")
    int createNewUser(@Param("user") User user);

    int updateUser(User user);

    @Result(property = "userId", column = "id")
    @Select("select  * from users_tb where id = #{id}")
    User findUserByID(int id );
    int removeUser(int id );


}
