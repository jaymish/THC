package com.org.THC.controller;

import com.org.THC.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController implements ErrorController {

    @Autowired
    private UserDetailsService userDetailsService;

    @RequestMapping(path="/")
    public String thcIndex(ModelMap modelMap){
        modelMap.put("message","");
        return "index";
    }

    @GetMapping(path = "/login")
    public String loginPage(){
        return "login";
    }

    @RequestMapping(path = "/error")
    public String thcError(ModelMap modelMap) {
        modelMap.put("message", "Something went wrong. Please try again.");
        return "index";
    }

    @RequestMapping(path = "/registration")
    public String adminregistration(){
        return "registration";
    }

    @PostMapping(path = "/register")
    public String addUser(@ModelAttribute("name") String name,@ModelAttribute("password") String password,ModelMap modelMap){
        System.out.println(name+password);
        userDetailsService.saveUser(name,password);
        modelMap.put("message", "");
        return "index";
    }
}