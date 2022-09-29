package com.appInfoSystem.service;

import com.appInfoSystem.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserService {
    User getUserByName(@Param("userName")String userName);
    int modifyUserName(@Param("userName")String userName, @Param("id")int id);
    int modifyUserPassword(@Param("userPassword")String userPassword, @Param("id")int id);
    int deleteAUser(int id);
}
