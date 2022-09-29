package com.appInfoSystem.pojo;

import com.appInfoSystem.service.AppService;

import javax.annotation.Resource;
import java.util.Date;

public class appInfo {
    private int id;
    private String softwareName;
    private String APKName;
    private String supportROM;
    private String interfaceLanguage;
    private double softwareSize;
    private Date updateDate;
    private int devid;
    private String appInfo;
    private int status;
    private Date onSaleDate;
    private Date offSaleDate;
    private int flatformId;
    private int categoryLevel3;
    private int downloads;
    private int createdBy;
    private Date creationDate;
    private int modifyBy;
    private Date modifyDate;
    private int categoryLevel1;
    private int categoryLevel2;
    private String logoPicPath;
    private String logoLocPath;
    private int versionId;
    private String flatformName;
    private String categoryLevel1Name;
    private String categoryLevel2Name;
    private String categoryLevel3Name;
    private String statusName;
    private String versionNo;

    @Resource
    private AppService appService;

    public String getCategoryLevel1Name() {
        return categoryLevel1Name;
    }

    public void setCategoryLevel1Name(String categoryLevel1Name) {
        this.categoryLevel1Name = categoryLevel1Name;
    }

    public String getCategoryLevel2Name() {
        return categoryLevel2Name;
    }

    public void setCategoryLevel2Name(String categoryLevel2Name) {
        this.categoryLevel2Name = categoryLevel2Name;
    }

    public String getCategoryLevel3Name() {
        return categoryLevel3Name;
    }

    public void setCategoryLevel3Name(String categoryLevel3Name) {
        this.categoryLevel3Name = categoryLevel3Name;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }

    public String getFlatformName(){return flatformName;}

    public void setFlatformName(String flatformName){
        this.flatformName = flatformName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSoftwareName() {
        return softwareName;
    }

    public void setSoftwareName(String softwareName) {
        this.softwareName = softwareName;
    }

    public String getAPKName() {
        return APKName;
    }

    public void setAPKName(String APKName) {
        this.APKName = APKName;
    }

    public String getSupportROM() {
        return supportROM;
    }

    public void setSupportROM(String supportROM) {
        this.supportROM = supportROM;
    }

    public String getInterfaceLanguage() {
        return interfaceLanguage;
    }

    public void setInterfaceLanguage(String interfaceLanguage) {
        this.interfaceLanguage = interfaceLanguage;
    }

    public double getSoftwareSize() {
        return softwareSize;
    }

    public void setSoftwareSize(double softwareSize) {
        this.softwareSize = softwareSize;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public int getDevid() {
        return devid;
    }

    public void setDevid(int devid) {
        this.devid = devid;
    }

    public String getAppInfo() {
        return appInfo;
    }

    public void setAppInfo(String appInfo) {
        this.appInfo = appInfo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getOnSaleDate() {
        return onSaleDate;
    }

    public void setOnSaleDate(Date onSaleDate) {
        this.onSaleDate = onSaleDate;
    }

    public Date getOffSaleDate() {
        return offSaleDate;
    }

    public void setOffSaleDate(Date offSaleDate) {
        this.offSaleDate = offSaleDate;
    }

    public int getFlatformId() {
        return flatformId;
    }

    public void setFlatformId(int flatformId) {
        this.flatformId = flatformId;
    }

    public int getCategoryLevel3() {
        return categoryLevel3;
    }

    public void setCategoryLevel3(int categoryLevel3) {
        this.categoryLevel3 = categoryLevel3;
    }

    public int getDownloads() {
        return downloads;
    }

    public void setDownloads(int downloads) {
        this.downloads = downloads;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(int modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public int getCategoryLevel1() {
        return categoryLevel1;
    }

    public void setCategoryLevel1(int categoryLevel1) {
        this.categoryLevel1 = categoryLevel1;
    }

    public int getCategoryLevel2() {
        return categoryLevel2;
    }

    public void setCategoryLevel2(int categoryLevel2) {
        this.categoryLevel2 = categoryLevel2;
    }

    public String getLogoPicPath() {
        return logoPicPath;
    }

    public void setLogoPicPath(String logoPicPath) {
        this.logoPicPath = logoPicPath;
    }

    public String getLogoLocPath() {
        return logoLocPath;
    }

    public void setLogoLocPath(String logoLocPath) {
        this.logoLocPath = logoLocPath;
    }

    public int getVersionId() {
        return versionId;
    }

    public void setVersionId(int versionId) {
        this.versionId = versionId;
    }

    @Override
    public String toString() {
        return "appInfo{" +
                "id=" + id +
                ", softwareName='" + softwareName + '\'' +
                ", APKName='" + APKName + '\'' +
                ", supportROM='" + supportROM + '\'' +
                ", interfaceLanguage='" + interfaceLanguage + '\'' +
                ", softwareSize=" + softwareSize +
                ", updateDate=" + updateDate +
                ", devid=" + devid +
                ", appInfo='" + appInfo + '\'' +
                ", status=" + status +
                ", onSaleDate=" + onSaleDate +
                ", offSaleDate=" + offSaleDate +
                ", flatformId=" + flatformId +
                ", categoryLevel3=" + categoryLevel3 +
                ", downloads=" + downloads +
                ", createdBy=" + createdBy +
                ", creationDate=" + creationDate +
                ", modifyBy=" + modifyBy +
                ", modifyDate=" + modifyDate +
                ", categoryLevel1=" + categoryLevel1 +
                ", categoryLevel2=" + categoryLevel2 +
                ", logoPicPath='" + logoPicPath + '\'' +
                ", logoLocPath='" + logoLocPath + '\'' +
                ", versionId=" + versionId +
                ", flatformName='" + flatformName + '\'' +
                ", categoryLevel1Name='" + categoryLevel1Name + '\'' +
                ", categoryLevel2Name='" + categoryLevel2Name + '\'' +
                ", categoryLevel3Name='" + categoryLevel3Name + '\'' +
                ", statusName='" + statusName + '\'' +
                ", versionNo='" + versionNo + '\'' +
                ", appService=" + appService +
                '}'+"\n";
    }
}
