package com.org.THC.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController implements ErrorController {

    @RequestMapping(path="/")
    public String thcIndex(ModelMap modelMap){
        modelMap.put("message","");
        return "index";
    }


    @RequestMapping(path = "/error")
    public String thcError(ModelMap modelMap) {
        modelMap.put("message", "Something went wrong. Please try again.");
        return "index";
    }
}
