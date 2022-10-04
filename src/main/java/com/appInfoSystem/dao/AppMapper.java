package com.appInfoSystem.dao;

import com.appInfoSystem.pojo.appInfo;
import com.appInfoSystem.pojo.appCategory;
import com.appInfoSystem.pojo.appVersion;
import com.appInfoSystem.pojo.dataDictionary;

import java.util.List;
import java.util.Map;

public interface AppMapper {
    appInfo getAppById(int id);//根据id获得appInfo
    String getFlatformName(appInfo app);//根据appInfo获取平台名
    String getFlatformNameById(int id);//根据平台id获取平台名
    List<dataDictionary> selectPlatformDictFlatForm();//获取平台List
    List<dataDictionary> selectPlatformDictStatus();//获取状态List
    List<appCategory> selectCategoryLevel1();//获取一级分类List
    List<appCategory> selectNextLevelCategory(int parentId);//根据上级分类parentId获取下级分类List
    List<appInfo> paramSearchApp(Map<String, Object> params);//参数搜索App
    String getCategoryName(int id);//根据分类id获取分类名
    String getStatusName(int id);//根据appInfo.id获取状态名
    String getVersionNo(int id);//根据appInfo.id获取版本号
    appVersion simpleGetVersion(int id);//根据appVersion.id获取appVersion
    int addApp (Map map);//增加appInfo
    int modifyApp (Map map);//修改appInfo
    int addAppVersion(Map map);//新增appVersion
    int getLatestVersion(int appId);//根据appInfo.id按创建日期排序获取最新的appVersion.id
    int updateAppVersionId(Map map);//修改appInfo.versionId
    int updateAppVersion(Map map);//修改appVersion
    int deleteAppInfo (int id);//根据appInfo.id删除appInfo
    int deleteAppVersion(int appId);//根据appVersion.appId删除appVersion
    int devChangeAppPublishStatus (Map map);//appInfo上下架
    int backendChangeAppStatus(Map map);//审核app
    int checkAppName(String APKName);//查找同APK名称个数
    List<appVersion> getAppVersionList (int appId);//根据appInfo.id获取历史版本List
    String getPublishStatusName (int valueId);//根据发布状态id获取发布状态名
    String getAppName (int id);//根据appInfo.id获取appInfo.softwareName
}
