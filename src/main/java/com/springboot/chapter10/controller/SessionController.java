package com.springboot.chapter10.controller;

import com.springboot.chapter10.pojo.User;
import com.springboot.chapter10.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
//sessionAttributes指定数据模型名称或者属性类型，保存到Session中
@SessionAttributes(names = {"user"},types = Long.class)
@RequestMapping("/session")
@Controller
public class SessionController {
    @Autowired
    private UserService userService=null;
    @GetMapping("/test")
    public String test(@SessionAttribute("id") Long id, Model model){
        User user = userService.getUser(id);
        //根据了类型保存到session中
        model.addAttribute("id_new",id);
        //根据名称保存到session中
        model.addAttribute("user",user);
        System.out.println("运行到这里了");
        return "session/test";
    }
}
