package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@RestController
public class SystemController {
    /*登陆页面跳转*/
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    /*注册页面跳转*/
    @GetMapping("/registry")
    public String registry(){
        return "registry";
    }

}
