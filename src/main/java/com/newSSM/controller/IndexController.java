package com.newSSM.controller;

import com.newSSM.pojo.User;
import com.newSSM.service.UserService;
import com.newSSM.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController extends AbstractController {
    @Resource
    private UserService userService;
    private Logger logger = Logger.getLogger(getClass());
    @Override
    @RequestMapping("/index")
    protected ModelAndView handleRequestInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        logger.info("helloSpring");
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("message", "Hello Spring MVC");
        return modelAndView;
    }
    @RequestMapping("/success")
    public ModelAndView success(HttpServletRequest request, HttpServletResponse response){
        logger.info("success");
        String username = request.getParameter("username");
        logger.info(username);
        System.out.println("==========================\n"+username+"\n==========================");
        ModelAndView modelAndView = new ModelAndView("success");
        modelAndView.addObject("username", username);
        return modelAndView;
    }
    @RequestMapping("/test")
    public ModelAndView test(HttpServletRequest request, HttpServletResponse response){
        logger.info("test");
        String username = request.getParameter("username");
        logger.info(username);
        User user = userService.getUserByName(username);
        ModelAndView modelAndView = new ModelAndView("success");
        modelAndView.addObject("user", user);
        return modelAndView;
    }
}
