package com.appInfoSystem.service;

import com.appInfoSystem.pojo.*;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface AppService {
    List<dataDictionary> selectPlatformDict();
    List<appCategory> selectCategoryLevel1();
    List<appCategory> selectNextLevelCategory(int parentId);
    List<appInfo> paramSearchApp(Map map);
    String getFlatformName(appInfo app);
    String getCategoryName(int id);
    String getStatusName(int id);
    String getVersionNo(int id);
    PageBean<appInfo> appInfoPageBean(int pagegNow, int pageSize, Map<String, Object> map);
    PageInfo<appInfo> getAppPage(Map<String, Object> map, int pageNum, int pageSize);
    String getFlatformNameById(int id);
    appInfo getAppById(int id);
    int backendChangeAppStatus(Map map);
    appVersion simpleGetVersion(int id);
}
