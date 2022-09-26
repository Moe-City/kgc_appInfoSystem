package com.newSSM.dao;

import com.newSSM.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    User getUserByName(@Param("userName")String userName);
    int modifyUserName(@Param("userName")String userName, @Param("id")int id);
    int modifyUserPassword(@Param("userPassword")String userPassword, @Param("id")int id);
    int deleteAUser(@Param("id") Integer id);
}
