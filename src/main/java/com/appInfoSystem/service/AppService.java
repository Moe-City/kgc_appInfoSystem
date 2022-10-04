package com.appInfoSystem.service;

import com.appInfoSystem.pojo.*;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface AppService {
    List<dataDictionary> selectPlatformDictFlatForm();
    List<dataDictionary> selectPlatformDictStatus();
    List<appCategory> selectCategoryLevel1();
    List<appCategory> selectNextLevelCategory(int parentId);
    List<appInfo> paramSearchApp(Map map);
    String getFlatformName(appInfo app);
    String getCategoryName(int id);
    String getStatusName(int id);
    String getVersionNo(int id);
    PageInfo<appInfo> getAppPage(Map<String, Object> map, int pageNum, int pageSize);
    String getFlatformNameById(int id);
    appInfo getAppById(int id);
    int backendChangeAppStatus(Map map);
    appVersion simpleGetVersion(int id);
    int checkAppName(String APKName);
    int addApp (Map map);
    int modifyApp (Map map);
    List<appVersion> getAppVersionList (int appId);
    String getPublishStatusName (int valueId);
    String getAppName (int id);
    int addAppVersion(Map map);
    int getLatestVersion(int appId);
    int updateAppVersionId(Map map);
    int updateAppVersion(Map map);
    int deleteAppInfo (int id);
    int deleteAppVersion(int appId);
    int devChangeAppPublishStatus (Map map);
}
