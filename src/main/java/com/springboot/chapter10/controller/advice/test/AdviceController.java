package com.springboot.chapter10.controller.advice.test;

import org.apache.tools.ant.util.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequestMapping("/advice")
public class AdviceController {
    @GetMapping("/test")
    public String test(Date date, ModelMap modelMap){
        System.out.println(modelMap.get("project_name"));
        System.out.println(DateUtils.format(date,"yyyy-MM-dd"));
        throw  new RuntimeException("异常了 跳转到控制器通知的异常信息里");
    }
}
