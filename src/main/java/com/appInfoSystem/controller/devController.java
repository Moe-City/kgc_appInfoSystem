package com.appInfoSystem.controller;

import com.appInfoSystem.service.DevUserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/dev")
public class devController {
    @Resource
    private DevUserService devUserService;

    private Logger logger = Logger.getLogger(getClass());

    @RequestMapping("/login")
    protected String loginPage(){
        return "devlogin";
    }

    @RequestMapping("/dologin")
    protected String dologin(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session, Map devUserSession){
        //登录验证，实体类，Mapper...
        String devCode = request.getParameter("devCode");
        String devPassword = request.getParameter("devPassword");
        String truePassword = devUserService.verifyDevPwd(devCode);
        logger.info("devCode = "+devCode+" devPassword = "+devPassword+" truePassword = "+truePassword);
        if (Objects.equals(truePassword, devPassword)){
            logger.info("dev login success");
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

    @RequestMapping("/flatform/main")
    protected String mainPage(){
        return "/developer/main";
    }

    @RequestMapping("/flatform/app/list")
    protected String appMaintain(){
        return "/developer/appinfolist";
    }

    @RequestMapping("/flatform/app/appinfoadd")
    protected String addApp(){
        return "/developer/appinfoadd";
    }

    @RequestMapping("/logout")
    protected String logout(HttpSession session){
        session.invalidate();
        return "redirect:login";
    }
}
