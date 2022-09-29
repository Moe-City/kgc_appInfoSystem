package com.appInfoSystem.service;

import org.springframework.stereotype.Service;

public interface BackendService {
    String verifyBackendPwd(String userCode);
    String getBackendType(String userCode);
}
