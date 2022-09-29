package com.appInfoSystem.dao;

import com.appInfoSystem.pojo.appInfo;
import com.appInfoSystem.pojo.appCategory;
import com.appInfoSystem.pojo.appVersion;
import com.appInfoSystem.pojo.dataDictionary;

import java.util.List;
import java.util.Map;

public interface AppMapper {
    appInfo getAppById(int id);
    List<dataDictionary> selectPlatformDict();
    List<appCategory> selectCategoryLevel1();
    List<appCategory> selectNextLevelCategory(int parentId);
//    List<appInfo> paramSearchApp(appInfo app);
    List<appInfo> paramSearchApp(Map<String, Object> params);
    String getFlatformName(appInfo app);
    String getCategoryName(int id);
    String getStatusName(int id);
    String getVersionNo(int id);
    String getFlatformNameById(int id);
    int backendChangeAppStatus(Map map);
    appVersion simpleGetVersion(int id);
}
