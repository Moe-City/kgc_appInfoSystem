package com.appInfoSystem.controller;

import com.alibaba.fastjson.JSON;
import com.appInfoSystem.pojo.*;
import com.appInfoSystem.service.AppService;
import com.appInfoSystem.service.BackendService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 管理员根节点
 */
@Controller
@RequestMapping("/manage")
public class manageController {
    @Resource
    private BackendService backendService;
    @Resource
    private AppService appService;
    private Logger logger = Logger.getLogger(getClass());

    /**
     * 管理员登录页面
     * @return 登录页面
     */
    @RequestMapping("/login")
    protected String loginPage(){
        logger.info("backendLogin");
        return "backendlogin";
    }

    /**
     * 管理员登录验证
     * @param model addAttribute
     * @param request getParameter
     * @param response nothing
     * @param session 存入用户信息作为已登录判据
     * @param userSession Map，作为session的存储结构使用
     * @return 主页/登录页面
     */
    @RequestMapping("/dologin")
    protected String dologin(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session, Map userSession){
        String userCode = request.getParameter("userCode");
        String userPassword = request.getParameter("userPassword");
        String truePassword = backendService.verifyBackendPwd(userCode);
        logger.info("userCode="+userCode+", userPassword="+userPassword+" logging...");
        logger.info("True password = "+truePassword);
        if (Objects.equals(truePassword, userPassword)){
            logger.info("login SUCCESS");
            String userTypeName = backendService.getBackendType(userCode);
            userSession.put("userName",userCode);
            userSession.put("userTypeName",userTypeName);
            session.setAttribute("userSession", userSession);
            return "redirect:backend/main";
        }else{
            logger.info("login FAIL");
            model.addAttribute("error","用户名或密码错误");
            return "backendlogin";
        }
    }

    /**
     * 首页按钮
     * @return 首页
     */
    @RequestMapping("/backend/main")
    protected String mainPage(){
        return "backend/main";
    }

    /**
     * 获取下一级别的分类信息
     * List转换为json
     * 发送json回前端
     * 用在AJAX中
     * @param request getParameter
     * @param response setContentType 发送json
     */
    @RequestMapping("/backend/app/list/nextLevelCategory")
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
     * APP列表分页
     * @param pageNum 当前页参数
     * @param model setAttribute
     * @param request getParameter
     * @param response nothing
     * @return applist.jsp
     */
    @RequestMapping("/backend/app/list/{pageNum}")
    protected String appListPage(@PathVariable("pageNum") Integer pageNum, Model model, HttpServletRequest request, HttpServletResponse response){
        /*
          获取当前页，传送至下一页面使用
          已因保存审核结果时产生了BUG弃用
         */
        /*if (pageNum == 0 && request.getParameter("pageNum") != "" && request.getParameter("pageNum") != null)
            pageNum = Integer.valueOf(request.getParameter("pageNum"));*/
        /*
          下拉菜单内选项的获取和返回
         */
        List<dataDictionary> flatFormList = appService.selectPlatformDictFlatForm();
        model.addAttribute("flatFormList", flatFormList);
        List<appCategory> categoryLevel1List = appService.selectCategoryLevel1();
        model.addAttribute("categoryLevel1List",categoryLevel1List);
        /*
          创建map
          获取参数并放入map中
          以map内内容作为参数查询应用
          使用PageInfo分页插件查询并将结果放入page
          page.list即为结果List，page其他参数为分页相关参数
         */
        Map<String, Object> map = new HashMap<String, Object>();
        String softwareName = request.getParameter("querySoftwareName");
        int flatformId = 0;
        int categoryLevel1 = 0;
        int categoryLevel2 = 0;
        int categoryLevel3 = 0;
        try{
            map.put("softwareName",softwareName);
            if (request.getParameter("queryFlatformId") != "" && request.getParameter("queryFlatformId") != null){
                flatformId = Integer.parseInt(request.getParameter("queryFlatformId"));
                map.put("flatformId",flatformId);
            }
            if (request.getParameter("queryCategoryLevel1") != "" && request.getParameter("queryCategoryLevel1") != null){
                categoryLevel1 = Integer.parseInt(request.getParameter("queryCategoryLevel1"));
                map.put("categoryLevel1", categoryLevel1);
            }
            if (request.getParameter("queryCategoryLevel2") != "" && request.getParameter("queryCategoryLevel2") != null){
                categoryLevel2 = Integer.parseInt(request.getParameter("queryCategoryLevel2"));
                map.put("categoryLevel2", categoryLevel2);
            }
            if (request.getParameter("queryCategoryLevel3") != "" && request.getParameter("queryCategoryLevel3") != null) {
                categoryLevel3 = Integer.parseInt(request.getParameter("queryCategoryLevel3"));
                map.put("categoryLevel3", categoryLevel3);
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
        logger.info(map);
        int pageSize = 7;
        map.put("status",1);
        PageInfo<appInfo> page = appService.getAppPage(map,pageNum, pageSize);
        /*
          对page.list中的每一个appInfo补充信息
         */
        for (appInfo ai:
                page.getList()) {
            ai.setFlatformName(appService.getFlatformName(ai));
            ai.setStatusName(appService.getStatusName(ai.getId()));
            ai.setVersionNo(appService.getVersionNo(ai.getId()));
            ai.setCategoryLevel1Name(appService.getCategoryName(ai.getCategoryLevel1()));
            ai.setCategoryLevel2Name(appService.getCategoryName(ai.getCategoryLevel2()));
            ai.setCategoryLevel3Name(appService.getCategoryName(ai.getCategoryLevel3()));
        }
        /*
          将数据（参数）返回前端，实现表单中自动填入上次查询参数的功能
         */
        model.addAttribute("querySoftwareName",softwareName);
        model.addAttribute("queryFlatformId",flatformId);
        model.addAttribute("queryCategoryLevel1",categoryLevel1);
        model.addAttribute("queryCategoryLevel2",categoryLevel2);
        model.addAttribute("queryCategoryLevel3",categoryLevel3);
        /*
          返回page，实现分页和查询结果功能
         */
        model.addAttribute("pages",page);
        return "backend/applist";
    }

    /**
     * 审核APP页面
     * @param request getParameter setCharacterEncoding
     * @param response nothing
     * @param model addAttribute
     * @return appcheck.jsp
     */
    @RequestMapping("/backend/app/list/check")
    protected String checkApp(HttpServletRequest request, HttpServletResponse response, Model model){
        /*
          编码，必须放在单独的try catch组合（原因未知），否则status会乱码
          如果想要保留搜索结果的参数以便保存审核结果后返回搜索结果，
          此处要写上个方法中的参数获取和保存，但会导致后续方法中出现BUG，
          详见保存审核结果方法中注释。
         */
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        /*
          获取各种参数和返回各种信息
         */
        int appInfoId = Integer.parseInt(request.getParameter("formAppinfoid"));
        int versionId = Integer.parseInt(request.getParameter("formVersionid"));
        int status = Integer.parseInt(request.getParameter("formStatus"));
        String statusname = request.getParameter("formStatusname");
        appInfo ai = appService.getAppById(appInfoId);
        ai.setFlatformName(appService.getFlatformName(ai));
        ai.setCategoryLevel1Name(appService.getCategoryName(ai.getCategoryLevel1()));
        ai.setCategoryLevel2Name(appService.getCategoryName(ai.getCategoryLevel2()));
        ai.setCategoryLevel3Name(appService.getCategoryName(ai.getCategoryLevel3()));
        ai.setStatusName(statusname);
        if (versionId != 0){
            appVersion av = appService.simpleGetVersion(versionId);
            model.addAttribute("appVersion",av);
        }
        model.addAttribute("appInfo",ai);
        return "/backend/appcheck";
    }

    /**
     * 保存审核结果
     * @param request getParameter
     * @param response nothing
     * @param model nothing
     * @return 所有应用列表的第一页
     */
    @RequestMapping("/backend/app/list/checksave")
    protected String appStatus(HttpServletRequest request, HttpServletResponse response, Model model){
        /*
          如果想保留所有参数，以达到保存后返回此前搜索结果的某一页，则需要写参数的获取和返回
          -----但是-----：信息流转到此步时id的parseInt会抛出空异常，原因未知。
          遂放弃保留参数，直接重定向至所有应用列表的第一页。
         */
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println(id);
        int status = Integer.parseInt(request.getParameter("status"));
        Map map1 = new HashMap();
        map1.put("id", id);
        map1.put("status", status);
        appService.backendChangeAppStatus(map1);
        return "redirect:1";
    }

    /**
     * 退出登录
     * @param session 清空session
     * @return 重定向，返回登录界面
     */
    @RequestMapping("/logout")
    protected String logout(HttpSession session){
        session.invalidate();
        return "redirect:login";
    }
}
