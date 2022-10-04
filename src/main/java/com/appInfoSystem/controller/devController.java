package com.appInfoSystem.controller;

import com.alibaba.fastjson.JSON;
import com.appInfoSystem.pojo.*;
import com.appInfoSystem.service.AppService;
import com.appInfoSystem.service.DevUserService;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * 开发者根节点
 */
@Controller
@RequestMapping("/dev")
public class devController {
    @Resource
    private DevUserService devUserService;
    @Resource
    private AppService appService;

    private Logger logger = Logger.getLogger(getClass());

    /**
     * 登录页面
     * @return 登录页面
     */
    @RequestMapping("/login")
    protected String loginPage(){
        return "devlogin";
    }

    /**
     * 登录验证
     * @param model addAttribute
     * @param request getParameter
     * @param response nothing
     * @param session setAttribute
     * @param devUserSession 将Map当做session的存储结构
     * @return 主页或登录页面
     */
    @RequestMapping("/dologin")
    protected String dologin(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session, Map devUserSession){
        String devCode = request.getParameter("devCode");
        String devPassword = request.getParameter("devPassword");
        String truePassword = devUserService.verifyDevPwd(devCode);
        logger.info("devCode = "+devCode+" devPassword = "+devPassword+" truePassword = "+truePassword);
        if (Objects.equals(truePassword, devPassword)){
            logger.info("dev login success");
            devUserSession.put("devCode",devCode);
            String devName = devUserService.getDevName(devCode);
            devUserSession.put("devName",devName);
            session.setAttribute("devUserSession",devUserSession);
            return "redirect:/dev/flatform/main";
        }else{
            logger.info("login FAIL");
            model.addAttribute("error","用户名或密码错误");
            return "devlogin";
        }
    }

    /**
     * 主页按钮
     * @return 主页
     */
    @RequestMapping("/flatform/main")
    protected String mainPage(){
        return "/developer/main";
    }


    @RequestMapping("/flatform/app/list/{pageNum}")
    protected String appMaintain(@PathVariable("pageNum")Integer pageNum, HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response){

        /*
          下拉菜单内选项的获取和返回
         */
        List<dataDictionary> flatFormList = appService.selectPlatformDictFlatForm();
        model.addAttribute("flatFormList", flatFormList);
        List<dataDictionary> statusList = appService.selectPlatformDictStatus();
        model.addAttribute("statusList", statusList);
        List<appCategory> categoryLevel1List = appService.selectCategoryLevel1();
        model.addAttribute("categoryLevel1List", categoryLevel1List);

        /*
          创建并将参数填入paramMap
         */
        Map paramMap = new HashMap();
        String softwareName = request.getParameter("querySoftwareName");
        int flatformId = 0;
        int status = 0;
        int categoryLevel1 = 0;
        int categoryLevel2 = 0;
        int categoryLevel3 = 0;
        Map devUserSession = (HashMap) request.getSession().getAttribute("devUserSession");
        String devCode = (String) devUserSession.get("devCode");
        logger.info(devCode);
        int devId = devUserService.getDevId(devCode);
        logger.info(devId);
        paramMap.put("devId",devId);
        //paramMap.put("devId",1);

        try{
            paramMap.put("softwareName",softwareName);
            if (request.getParameter("queryFlatformId") != "" && request.getParameter("queryFlatformId") != null){
                flatformId = Integer.parseInt(request.getParameter("queryFlatformId"));
                paramMap.put("flatformId",flatformId);
            }
            if (request.getParameter("queryStatus") != "" && request.getParameter("queryStatus") != null){
                status = Integer.parseInt(request.getParameter("queryStatus"));
                paramMap.put("status", status);
            }
            if (request.getParameter("queryCategoryLevel1") != "" && request.getParameter("queryCategoryLevel1") != null){
                categoryLevel1 = Integer.parseInt(request.getParameter("queryCategoryLevel1"));
                paramMap.put("categoryLevel1", categoryLevel1);
            }
            if (request.getParameter("queryCategoryLevel2") != "" && request.getParameter("queryCategoryLevel2") != null){
                categoryLevel2 = Integer.parseInt(request.getParameter("queryCategoryLevel2"));
                paramMap.put("categoryLevel2", categoryLevel2);
            }
            if (request.getParameter("queryCategoryLevel3") != "" && request.getParameter("queryCategoryLevel3") != null) {
                categoryLevel3 = Integer.parseInt(request.getParameter("queryCategoryLevel3"));
                paramMap.put("categoryLevel3", categoryLevel3);
            }
            /*
              将二级三级分类的下拉菜单选项获取为List并返回前端
             */
            if (categoryLevel1 != 0){
                List<appCategory> categoryLevel2List = appService.selectNextLevelCategory(categoryLevel1);
                model.addAttribute("categoryLevel2List",categoryLevel2List);
            }
            if (categoryLevel2 != 0){
                List<appCategory> categoryLevel3List = appService.selectNextLevelCategory(categoryLevel2);
                model.addAttribute("categoryLevel3List",categoryLevel3List);
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info(e);
        }
        logger.info(paramMap);

        /*
          查询以paramMap内Object为参数的appInfoList
          并对List中每一个appInfo补充信息
         */
        int pageSize = 7;
        PageInfo<appInfo> page = appService.getAppPage(paramMap,pageNum, pageSize);
        //List<appInfo> appInfoList = appService.paramSearchApp(paramMap);
        for (appInfo ai:
                page.getList()) {
            ai.setFlatformName(appService.getFlatformName(ai));
            ai.setStatusName(appService.getStatusName(ai.getId()));
            ai.setVersionNo(appService.getVersionNo(ai.getId()));
            ai.setCategoryLevel1Name(appService.getCategoryName(ai.getCategoryLevel1()));
            ai.setCategoryLevel2Name(appService.getCategoryName(ai.getCategoryLevel2()));
            ai.setCategoryLevel3Name(appService.getCategoryName(ai.getCategoryLevel3()));
        }
        model.addAttribute("pages",page);

        model.addAttribute("querySoftwareName",softwareName);
        model.addAttribute("queryFlatformId",flatformId);
        model.addAttribute("queryStatus",status);
        model.addAttribute("queryCategoryLevel1",categoryLevel1);
        model.addAttribute("queryCategoryLevel2",categoryLevel2);
        model.addAttribute("queryCategoryLevel3",categoryLevel3);
        return "/developer/appinfolist";
    }

    /**
     * 获取下一级别的分类信息
     * List转换为json
     * 发送json回前端
     * 用在AJAX中
     * @param request getParameter
     * @param response setContentType 发送json
     */
    @RequestMapping("/flatform/app/nlc")
    protected void getNextLevel(HttpServletRequest request, HttpServletResponse response){
        int parentId = Integer.parseInt(request.getParameter("parentId"));
        List<appCategory> categoryNextLevelList = appService.selectNextLevelCategory(parentId);
        String json = JSON.toJSONString(categoryNextLevelList);
        logger.info(json);
        try {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.println(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 同上
     * @param request getParameter
     * @param response setContentType 发送json
     */
    @RequestMapping("/flatform/app/list/nlc")
    protected void getNextLevel1(HttpServletRequest request, HttpServletResponse response){
        int parentId = Integer.parseInt(request.getParameter("parentId"));
        List<appCategory> categoryNextLevelList = appService.selectNextLevelCategory(parentId);
        String json = JSON.toJSONString(categoryNextLevelList);
        logger.info(json);
        try {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.println(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加app
     * @return 添加app页面
     */
    @RequestMapping("/flatform/app/appinfoadd")
    protected String addApp(Model model){
        List<dataDictionary> flatFormList = appService.selectPlatformDictFlatForm();
        model.addAttribute("flatFormList", flatFormList);
        List<appCategory> categoryLevel1List = appService.selectCategoryLevel1();
        model.addAttribute("categoryLevel1List", categoryLevel1List);
        return "/developer/appinfoadd";
    }

    /**
     * 保存app
     * request 从表单中获取数据
     * model addAttribute
     */
    @RequestMapping("/flatform/app/appinfoaddsave")
    protected String saveApp(HttpServletRequest request, Model model){
        try{
            request.setCharacterEncoding("utf-8");
            Map<String, String> params = new HashMap<String, String>();
            String path = "/webApp/kegongchang/appInfoSystem/web/statics/uploadfiles";
            File file = new File(path);
            /*如果路径不存在且不为目录则创建目录*/
            if (!file.exists() && !file.isDirectory())
                file.mkdirs();
            /*工厂，获取传来的文件，判断是否为multipart/form-data*/
            DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
            ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
            servletFileUpload.setFileSizeMax(50*1024);
            boolean isFile = ServletFileUpload.isMultipartContent(request);
            if (isFile){
                /*multipart/form-data中组成部分放入list*/
                List<FileItem> items = servletFileUpload.parseRequest(request);
                for (FileItem fileItem:
                     items) {
                    if (!fileItem.isFormField()){
                        String name = fileItem.getName();
                        if (name != null && name != ""){
                            String suffixName = FilenameUtils.getExtension(name);
                            if ("jpg".equalsIgnoreCase(suffixName) || "jpeg".equalsIgnoreCase(suffixName)
                                    || "png".equalsIgnoreCase(suffixName)){
                            }else {
                                throw new Exception("Incorrect file type!");
                            }
                        }
                        /*输入输出流*/
                        InputStream inputStream = fileItem.getInputStream();
                        FileOutputStream fileOutputStream = new FileOutputStream(path+"/"+name);
                        byte[] b = new byte[1024];
                        /*写数据*/
                        int length;
                        while ((length = inputStream.read(b)) > 0) {
                            fileOutputStream.write(b, 0, length);
                        }
                        params.put("logoPicPath",file.getPath()
                                .replaceFirst("\\\\webApp\\\\kegongchang\\\\appInfoSystem\\\\web", "")
                                .replaceAll("\\\\", "/")+"/"+name);
                        params.put("logoLocPath",file.getAbsolutePath()+"\\"+name);
                        /*关闭流*/
                        fileOutputStream.flush();
                        inputStream.close();
                        fileOutputStream.close();
                    }else {
                        /*除文件外其他参数存入map*/
                        String str = new String(fileItem.getString().getBytes("iso-8859-1"),"utf-8");
                        params.put(fileItem.getFieldName(), str);
                    }
                }
                params.put("status", String.valueOf(1));
                Map devUserSession = (HashMap) request.getSession().getAttribute("devUserSession");
                String devCode = (String) devUserSession.get("devCode");
                int devId = devUserService.getDevId(devCode);
                params.put("devId", String.valueOf(devId));
                //params.put("devId", String.valueOf(1));
                appService.addApp(params);
            }else {
            }
        }catch (Exception e){
            logger.info(e);
            e.printStackTrace();
            List<dataDictionary> flatFormList = appService.selectPlatformDictFlatForm();
            model.addAttribute("flatFormList", flatFormList);
            List<appCategory> categoryLevel1List = appService.selectCategoryLevel1();
            model.addAttribute("categoryLevel1List", categoryLevel1List);
            if (e.getClass().getName().contains("FileSizeLimitExceededException"))
                model.addAttribute("fileUploadError", "文件过大，提交失败!请不要提交超过50KB的文件！");
            else if (e.getMessage().contains("Incorrect file type!")){
                model.addAttribute("fileUploadError", "请上传PNG或JPG图片文件！");
            }
            else{
                System.out.println(e.getClass().getName());
                model.addAttribute("fileUploadError","未知错误！");
            }
            return "/developer/appinfoadd";
        }
        return "redirect:/dev/flatform/app/list/1";
    }

    /**
     * 检查APK名称是否重复
     * @param request getParameter("APKName")
     * @param response 返回是否重复的标记数据
     */
    @RequestMapping("/flatform/app/checkAppName")
    protected void checkAppName(HttpServletRequest request, HttpServletResponse response){
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            String APKName = request.getParameter("APKName");
            int sameAPKNameNumber = appService.checkAppName(APKName);
            if (sameAPKNameNumber != 0){
                response.setContentType("application/json;charset=utf-8");
                PrintWriter printWriter = response.getWriter();
                printWriter.println(1);
            }
        } catch (IOException e) {
                e.printStackTrace();
        }
    }

    /**
     * 修改APP页面
     * @param request getParameter
     * @param model addAttribute
     * @return appinfomodify.jsp
     */
    @RequestMapping("/flatform/app/modifyApp")
    protected String modifyApp(HttpServletRequest request, Model model){
        try {
            request.setCharacterEncoding("utf-8");
            int id = Integer.parseInt(request.getParameter("formId"));
            appInfo ai = appService.getAppById(id);
            ai.setStatusName(appService.getStatusName(id));
            model.addAttribute("appInfo", ai);
            model.addAttribute("flatformId",ai.getFlatformId());
            List<dataDictionary> flatFormList = appService.selectPlatformDictFlatForm();
            model.addAttribute("flatFormList", flatFormList);
            model.addAttribute("categoryLevel1",ai.getCategoryLevel1());
            List<appCategory> categoryLevel1List = appService.selectCategoryLevel1();
            model.addAttribute("categoryLevel1List", categoryLevel1List);
            model.addAttribute("categoryLevel2",ai.getCategoryLevel2());
            model.addAttribute("categoryLevel2List", appService.selectNextLevelCategory(ai.getCategoryLevel1()));
            model.addAttribute("categoryLevel3",ai.getCategoryLevel3());
            model.addAttribute("categoryLevel3List", appService.selectNextLevelCategory(ai.getCategoryLevel2()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "developer/appinfomodify";
    }

    /**
     * 保存APP修改
     * @param request 获取表单信息
     * @param model 错误信息
     * @return 错误页面
     */
    @RequestMapping("/flatform/app/appinfomodifysave")
    protected String modifySave(HttpServletRequest request, Model model){
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try{
            Map<String, String> params = new HashMap<String, String>();
            String path = "/webApp/kegongchang/appInfoSystem/web/statics/uploadfiles";
            File file = new File(path);
            /*如果路径不存在且不为目录则创建目录*/
            if (!file.exists() && !file.isDirectory())
                file.mkdirs();
            /*工厂，获取传来的文件，判断是否为multipart/form-data*/
            DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
            ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
            servletFileUpload.setFileSizeMax(50*1024);
            boolean isFile = ServletFileUpload.isMultipartContent(request);
            if (isFile){
                /*multipart/form-data中组成部分放入list*/
                List<FileItem> items = servletFileUpload.parseRequest(request);
                for (FileItem fileItem:
                        items) {
                    if (!fileItem.isFormField()){
                        String name = fileItem.getName();
                        if (name != null && name != ""){
                            String suffixName = FilenameUtils.getExtension(name);
                            if ("jpg".equalsIgnoreCase(suffixName) || "jpeg".equalsIgnoreCase(suffixName)
                                    || "png".equalsIgnoreCase(suffixName)){
                            }else {
                                throw new Exception("Incorrect file type! ");
                            }
                        }else continue;
                        /*输入输出流*/
                        InputStream inputStream = fileItem.getInputStream();
                        FileOutputStream fileOutputStream = new FileOutputStream(path+"/"+name);
                        byte[] b = new byte[1024];
                        /*写数据*/
                        int length;
                        while ((length = inputStream.read(b)) > 0) {
                            fileOutputStream.write(b, 0, length);
                        }
                        params.put("logoPicPath",file.getPath()
                                .replaceFirst("\\\\webApp\\\\kegongchang\\\\appInfoSystem\\\\web", "")
                                .replaceAll("\\\\", "/")+"/"+name);
                        params.put("logoLocPath",file.getAbsolutePath()+"\\"+name);
                        /*关闭流*/
                        fileOutputStream.flush();
                        inputStream.close();
                        fileOutputStream.close();
                    }else {
                        /*除文件外其他参数存入map*/
                        String str = new String(fileItem.getString().getBytes("iso-8859-1"),"utf-8");
                        if (!fileItem.getFieldName().equals("logoLocPath") && !fileItem.getFieldName().equals("logoPicPath"))
                            params.put(fileItem.getFieldName(), str);
                    }
                }
                appService.modifyApp(params);
            }else {
            }
        }catch (Exception e){
            logger.info(e);
            e.printStackTrace();
            if (e.getClass().getName().contains("FileSizeLimitExceededException"))
                model.addAttribute("fileUploadError", "文件过大，提交失败!请不要提交超过50KB的文件！");
            else if (e.getMessage().contains("Incorrect file type!")){
                model.addAttribute("fileUploadError", "请上传PNG或JPG图片文件！");
            }
            else{
                System.out.println(e.getClass().getName());
                model.addAttribute("fileUploadError","未知错误！");
            }
            return "/developer/goback";
        }
        return "redirect:list/1";
    }

    /**
     * 新增版本
     * @param request getParameter
     * @param model addAttribute
     * @return jsp
     */
    @RequestMapping("/flatform/app/addversion")
    protected String addVersion(HttpServletRequest request, Model model){
        int appId = Integer.parseInt(request.getParameter("formId"));
        List<appVersion> appVersionList = appService.getAppVersionList(appId);
        for (appVersion av:
             appVersionList) {
            av.setAppName(appService.getAppName(appId));
            av.setPublishStatusName(appService.getPublishStatusName(av.getPublishStatus()));
        }
        model.addAttribute("appId",appId);
        model.addAttribute("appVersionList",appVersionList);
        return "/developer/appversionadd";
    }

    /**
     * 保存新增版本
     * @param request 获取表单信息
     * @param model 返回错误信息
     * @return 错误页面或返回列表
     */
    @RequestMapping("/flatform/app/addversionsave")
    protected String addVersionSave(HttpServletRequest request, Model model){
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try{
            Map<String, String> params = new HashMap<>();
            Map<String, String> aiParams = new HashMap<>();
            String path = "/webApp/kegongchang/appInfoSystem/web/statics/uploadfiles";
            File file = new File(path);
            /*如果路径不存在且不为目录则创建目录*/
            if (!file.exists() && !file.isDirectory())
                file.mkdirs();
            /*工厂，获取传来的文件，判断是否为multipart/form-data*/
            DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
            ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
            servletFileUpload.setFileSizeMax(500*1024*1024);/*500MB*/
            boolean isFile = ServletFileUpload.isMultipartContent(request);
            if (isFile){
                /*multipart/form-data中组成部分放入list*/
                List<FileItem> items = servletFileUpload.parseRequest(request);
                for (FileItem fileItem:
                        items) {
                    if (!fileItem.isFormField()){
                        String name = fileItem.getName();
                        if (name != null && name != ""){
                            String suffixName = FilenameUtils.getExtension(name);
                            if ("apk".equalsIgnoreCase(suffixName)){
                            }else {
                                throw new Exception("Incorrect file type! ");
                            }
                        }
                        /*输入输出流*/
                        InputStream inputStream = fileItem.getInputStream();
                        FileOutputStream fileOutputStream = new FileOutputStream(path+"/"+name);
                        byte[] b = new byte[1024];
                        /*写数据*/
                        int length;
                        while ((length = inputStream.read(b)) > 0) {
                            fileOutputStream.write(b, 0, length);
                        }
                        params.put("downloadLink",file.getPath()
                                .replaceFirst("\\\\webApp\\\\kegongchang\\\\appInfoSystem\\\\web", "")
                                .replaceAll("\\\\", "/")+"/"+name);
                        params.put("apkLocPath",file.getAbsolutePath()+"\\"+name);
                        params.put("apkFileName",name);
                        /*关闭流*/
                        fileOutputStream.flush();
                        inputStream.close();
                        fileOutputStream.close();
                    }else {
                        /*除文件外其他参数存入map*/
                        String str = new String(fileItem.getString().getBytes("iso-8859-1"),"utf-8");
                        params.put(fileItem.getFieldName(), str);
                        if (fileItem.getFieldName().equals("appId")){
                            aiParams.put("id", str);
                        }
                    }
                }
                params.put("creationDate", new java.sql.Timestamp(new java.util.Date().getTime()).toString());
                params.put("modifyDate", new java.sql.Timestamp(new java.util.Date().getTime()).toString());
                appService.addAppVersion(params);
                int versionId = appService.getLatestVersion(Integer.parseInt(aiParams.get("id")));
                aiParams.put("versionId", String.valueOf(versionId));
                appService.updateAppVersionId(aiParams);
            }else {
            }
        }catch (Exception e){
            logger.info(e);
            e.printStackTrace();
            if (e.getClass().getName().contains("FileSizeLimitExceededException"))
                model.addAttribute("fileUploadError", "文件过大，提交失败!请不要提交超过500MB的文件！");
            else if (e.getMessage().contains("Incorrect file type!")){
                model.addAttribute("fileUploadError", "请上传APK文件！");
            }
            else{
                System.out.println(e.getClass().getName());
                model.addAttribute("fileUploadError","未知错误！");
            }
            return "/developer/goback";
        }
        return "redirect:list/1";
    }

    /**
     * 修改最新版本
     * @param request 获取appId
     * @param model 返回appVersion appId appVersionList
     * @return 修改页面
     */
    @RequestMapping("/flatform/app/modifyVersion")
    protected String modifyVersion(HttpServletRequest request, Model model){
        /*获取appId，获取List并补充信息，返回*/
        int appId = Integer.parseInt(request.getParameter("formId"));
        List<appVersion> appVersionList = appService.getAppVersionList(appId);
        for (appVersion av:
                appVersionList) {
            av.setAppName(appService.getAppName(appId));
            av.setPublishStatusName(appService.getPublishStatusName(av.getPublishStatus()));
        }
        appVersion av = appService.simpleGetVersion(appService.getLatestVersion(appId));
        model.addAttribute("appVersion",av);
        model.addAttribute("appId",appId);
        model.addAttribute("appVersionList",appVersionList);
        return "/developer/appversionmodify";
    }

    /**
     * 保存appVersion的修改
     * @param request 获取multipart/form-data
     * @param model addAttribute
     * @return 错误页面或第一页
     */
    @RequestMapping("/flatform/app/appversionmodifysave")
    protected String modifyVersionSave(HttpServletRequest request, Model model){
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try{
            Map<String, String> params = new HashMap<>();
            String path = "/webApp/kegongchang/appInfoSystem/web/statics/uploadfiles";
            File file = new File(path);
            /*如果路径不存在且不为目录则创建目录*/
            if (!file.exists() && !file.isDirectory())
                file.mkdirs();
            /*工厂，获取传来的文件，判断是否为multipart/form-data*/
            DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
            ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
            servletFileUpload.setFileSizeMax(500*1024*1024);/*500MB*/
            boolean isFile = ServletFileUpload.isMultipartContent(request);
            if (isFile){
                /*multipart/form-data中组成部分放入list*/
                List<FileItem> items = servletFileUpload.parseRequest(request);
                for (FileItem fileItem:
                        items) {
                    if (!fileItem.isFormField()){
                        String name = fileItem.getName();
                        System.out.println(name);
                        if (name != null && name != ""){
                            String suffixName = FilenameUtils.getExtension(name);
                            if ("apk".equalsIgnoreCase(suffixName)){
                            }else {
                                throw new Exception("Incorrect file type! ");
                            }
                        }else continue;
                        /*输入输出流*/
                        InputStream inputStream = fileItem.getInputStream();
                        FileOutputStream fileOutputStream = new FileOutputStream(path+"/"+name);
                        byte[] b = new byte[1024];
                        /*写数据*/
                        int length;
                        while ((length = inputStream.read(b)) > 0) {
                            fileOutputStream.write(b, 0, length);
                        }
                        params.put("downloadLink",file.getPath()
                                .replaceFirst("\\\\webApp\\\\kegongchang\\\\appInfoSystem\\\\web", "")
                                .replaceAll("\\\\", "/")+"/"+name);
                        params.put("apkLocPath",file.getAbsolutePath()+"\\"+name);
                        params.put("apkFileName",name);
                        /*关闭流*/
                        fileOutputStream.flush();
                        inputStream.close();
                        fileOutputStream.close();
                    }else {
                        /*除文件外其他参数存入map*/
                        String str = new String(fileItem.getString().getBytes("iso-8859-1"),"utf-8");
                        if (fileItem.getFieldName().equals("id") || fileItem.getFieldName().equals("versionSize") || fileItem.getFieldName().equals("versionInfo"))
                            params.put(fileItem.getFieldName(), str);
                    }
                }
                params.put("modifyDate", new java.sql.Timestamp(new java.util.Date().getTime()).toString());
                appService.updateAppVersion(params);
                System.out.println(params);
            }else {
            }
        }catch (Exception e){
            logger.info(e);
            e.printStackTrace();
            if (e.getClass().getName().contains("FileSizeLimitExceededException"))
                model.addAttribute("fileUploadError", "文件过大，提交失败!请不要提交超过500MB的文件！");
            else if (e.getMessage().contains("Incorrect file type!")){
                model.addAttribute("fileUploadError", "请上传APK文件！");
            }
            else{
                System.out.println(e.getClass().getName());
                model.addAttribute("fileUploadError","未知错误！");
            }
            return "/developer/goback";
        }
        return "redirect:list/1";
    }

    /**
     * 查看app信息
     * @param request 获得appId
     * @param model 返回appInfo和appVersionList
     * @return app信息页面
     */
    @RequestMapping("/flatform/app/viewAppInfo")
    protected String viewAppInfo(HttpServletRequest request, Model model){
        /*获取appId*/
        int appId = Integer.parseInt(request.getParameter("formId"));
        appInfo ai = appService.getAppById(appId);
        /*返回数据*/
        ai.setCategoryLevel1Name(appService.getCategoryName(ai.getCategoryLevel1()));
        ai.setCategoryLevel2Name(appService.getCategoryName(ai.getCategoryLevel2()));
        ai.setCategoryLevel3Name(appService.getCategoryName(ai.getCategoryLevel3()));
        ai.setFlatformName(appService.getFlatformNameById(ai.getFlatformId()));
        ai.setStatusName(appService.getStatusName(appId));
        model.addAttribute("appInfo",ai);
        List<appVersion> appVersionList = appService.getAppVersionList(appId);
        /*补充信息并返回*/
        for (appVersion av:
                appVersionList) {
            av.setAppName(appService.getAppName(appId));
            av.setPublishStatusName(appService.getPublishStatusName(av.getPublishStatus()));
        }
        model.addAttribute("appVersionList", appVersionList);
        return "/developer/appinfoview";
    }

    /**
     * 删除appInfo和appVersion
     * @param request 获得appId
     * @return 第一页
     */
    @RequestMapping("/flatform/app/delete")
    protected String deleteApp(HttpServletRequest request){
        int appId = Integer.parseInt(request.getParameter("formId"));
        appService.deleteAppInfo(appId);
        appService.deleteAppVersion(appId);
        return "redirect:list/1";
    }

    /**
     * 上下架app ajax
     * @param request 获得appId和当前status
     * @param response 返回statusName
     */
    @RequestMapping("/flatform/app/list/changeStatus")
    protected void changeStatus(HttpServletRequest request, HttpServletResponse response){
        try {
            /*获取ajax传来的数据*/
            int appId = Integer.parseInt(request.getParameter("formId"));
            int originalStatus = Integer.parseInt(request.getParameter("status"));
            int status = originalStatus;
            if (originalStatus == 2 || originalStatus == 5)
                status = 4;
            else if (originalStatus == 4)
                status = 5;
            Map map = new HashMap<>();
            map.put("id",appId);
            map.put("status",status);
            /*改上下架状态*/
            appService.devChangeAppPublishStatus(map);
            /*更改后状态*/
            String statusName = appService.getStatusName(appId);
            logger.info("appId: "+appId+"->"+statusName);
            map.put("statusName",statusName);
            /*按钮的名字*/
            if (status == 4)
                map.put("buttonStatusName", "下架");
            else if (status == 5)
                map.put("buttonStatusName", "上架");
            String jsonMap = JSON.toJSONString(map);
            /*返回前端*/
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter printWriter = response.getWriter();
            printWriter.println(jsonMap);
        }catch (Exception e){
            logger.info(e);
            e.printStackTrace();
        }
    }

    /**
     * 退出登录
     * @param session 清空session
     * @return 登录页面
     */
    @RequestMapping("/logout")
    protected String logout(HttpSession session){
        session.invalidate();
        return "redirect:login";
    }
}
