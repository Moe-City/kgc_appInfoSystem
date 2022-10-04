package com.appInfoSystem.service.impl;

import com.appInfoSystem.dao.DevUserMapper;
import com.appInfoSystem.service.DevUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("devUserService")
public class DevUserServiceImpl implements DevUserService {
    @Resource
    private DevUserMapper devUserMapper;

    @Override
    public String verifyDevPwd(String devCode) {
        return devUserMapper.verifyDevPwd(devCode);
    }

    @Override
    public String getDevName(String devCode) {
        return devUserMapper.getDevName(devCode);
    }

    @Override
    public int getDevId(String devCode) {
        return devUserMapper.getDevId(devCode);
    }
}
