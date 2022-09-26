package com.newSSM.service.impl;

import com.newSSM.dao.UserMapper;
import com.newSSM.pojo.User;
import com.newSSM.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User getUserByName(String userName) {
        return userMapper.getUserByName(userName);
    }

    @Override
    public int modifyUserName(@Param("userName")String userName, @Param("id")int id) {
        return userMapper.modifyUserName(userName, id);
    }

    @Override
    public int modifyUserPassword(@Param("userPassword")String userPassword, @Param("id")int id) {
        return userMapper.modifyUserPassword(userPassword, id);
    }

    @Override
    public int deleteAUser(int id) {
        return userMapper.deleteAUser(id);
    }
}
