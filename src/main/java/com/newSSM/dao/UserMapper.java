package com.newSSM.dao;

import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int modifyUserName(@Param("userName")String userName, @Param("id")int id);
    int modifyUserPassword(@Param("userPassword")String userPassword, @Param("id")int id);
    int deleteAUser(@Param("id") Integer id);
}
