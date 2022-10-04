package com.appInfoSystem.dao;

public interface DevUserMapper {
    String verifyDevPwd(String devCode);
    String getDevName(String devCode);
    int getDevId (String devCode);
}
