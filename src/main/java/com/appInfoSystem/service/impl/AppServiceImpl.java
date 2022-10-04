package com.appInfoSystem.service.impl;

import com.appInfoSystem.dao.AppMapper;
import com.appInfoSystem.pojo.*;
import com.appInfoSystem.service.AppService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("appService")
public class AppServiceImpl implements AppService {
    @Resource
    private AppMapper appMapper;

    @Override
    public List<dataDictionary> selectPlatformDictFlatForm() {
        return appMapper.selectPlatformDictFlatForm();
    }

    @Override
    public List<dataDictionary> selectPlatformDictStatus() {
        return appMapper.selectPlatformDictStatus();
    }

    @Override
    public List<appCategory> selectCategoryLevel1() {
        return appMapper.selectCategoryLevel1();
    }

    @Override
    public List<appCategory> selectNextLevelCategory(int parentId) {
        return appMapper.selectNextLevelCategory(parentId);
    }

    @Override
    public List<appInfo> paramSearchApp(Map map) {
        return appMapper.paramSearchApp(map);
    }

    @Override
    public String getFlatformName(appInfo app) {
        return appMapper.getFlatformName(app);
    }

    @Override
    public String getCategoryName(int id) {
        return appMapper.getCategoryName(id);
    }

    @Override
    public String getStatusName(int id) {
        return appMapper.getStatusName(id);
    }

    @Override
    public String getVersionNo(int id) {
        return appMapper.getVersionNo(id);
    }

    @Override
    public String getFlatformNameById(int id) {
        return appMapper.getFlatformNameById(id);
    }

    @Override
    public appInfo getAppById(int id) {
        return appMapper.getAppById(id);
    }

    @Override
    public int backendChangeAppStatus(Map map) {
        return appMapper.backendChangeAppStatus(map);
    }

    @Override
    public appVersion simpleGetVersion(int id) {
        return appMapper.simpleGetVersion(id);
    }

    @Override
    public int checkAppName(String APKName) {
        return appMapper.checkAppName(APKName);
    }

    @Override
    public int addApp(Map map) {
        return appMapper.addApp(map);
    }

    @Override
    public int modifyApp(Map map) {
        return appMapper.modifyApp(map);
    }

    @Override
    public List<appVersion> getAppVersionList(int appId) {
        return appMapper.getAppVersionList(appId);
    }

    @Override
    public String getPublishStatusName(int valueId) {
        return appMapper.getPublishStatusName(valueId);
    }

    @Override
    public String getAppName(int id) {
        return appMapper.getAppName(id);
    }

    @Override
    public int addAppVersion(Map map) {
        return appMapper.addAppVersion(map);
    }

    @Override
    public int getLatestVersion(int appId) {
        return appMapper.getLatestVersion(appId);
    }

    @Override
    public int updateAppVersionId(Map map) {
        return appMapper.updateAppVersionId(map);
    }

    @Override
    public int updateAppVersion(Map map) {
        return appMapper.updateAppVersion(map);
    }

    @Override
    public int deleteAppInfo(int id) {
        return appMapper.deleteAppInfo(id);
    }

    @Override
    public int deleteAppVersion(int appId) {
        return appMapper.deleteAppVersion(appId);
    }

    @Override
    public int devChangeAppPublishStatus(Map map) {
        return appMapper.devChangeAppPublishStatus(map);
    }

    @Override
    public PageInfo<appInfo> getAppPage(Map map, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<appInfo> list = appMapper.paramSearchApp(map);
        PageInfo<appInfo> page = new PageInfo<>(list,5);
        return page;
    }
}
