package com.appInfoSystem.service.impl;

import com.appInfoSystem.dao.BackendMapper;
import com.appInfoSystem.service.BackendService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("backendService")
public class BackendServiceImpl implements BackendService {
    @Resource
    private BackendMapper backendMapper;

    @Override
    public String verifyBackendPwd(String userCode) {
        return backendMapper.verifyBackendPwd(userCode);
    }

    @Override
    public String getBackendType(String userCode) {
        return backendMapper.getBackendType(userCode);
    }
}
