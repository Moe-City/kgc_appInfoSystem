package com.appInfoSystem.dao;

public interface BackendMapper {
    String verifyBackendPwd(String userCode);
    String getBackendType(String userCode);
}
