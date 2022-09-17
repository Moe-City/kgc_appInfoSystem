package com.newSSM.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController extends AbstractController {
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
        System.out.println("==========================\n"+username+"\n==========================");
        ModelAndView modelAndView = new ModelAndView("success");
        modelAndView.addObject("username", username);
        return modelAndView;
    }
}
