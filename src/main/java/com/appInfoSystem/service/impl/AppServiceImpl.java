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
    public List<dataDictionary> selectPlatformDict() {
        return appMapper.selectPlatformDict();
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
    public PageBean<appInfo> appInfoPageBean(int pageNow, int pageSize, Map<String, Object> map){
        PageBean<appInfo> pageBean = new PageBean<>();
        pageBean.setCurrentPageNo(pageNow);
        pageBean.setPageSize(pageSize);
        int count = appMapper.paramSearchApp(map).size();
        map.put("start",(pageNow-1)*pageSize);
        map.put("size",pageSize);
        pageBean.setTotalCount(count);
        int pages = count/pageSize;
        if (count%pageSize!=0)
            pages+=1;
        pageBean.setTotalPageCount(pages);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(map);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        List <appInfo> list = appMapper.paramSearchApp(map);
        pageBean.setList(list);
        return pageBean;
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
    public PageInfo<appInfo> getAppPage(Map map, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<appInfo> list = appMapper.paramSearchApp(map);
        PageInfo<appInfo> page = new PageInfo<>(list,5);
        return page;
    }
}
