package com.springboot.chapter10.controller;

import com.springboot.chapter10.pojo.User;
import com.springboot.chapter10.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/data")
public class DataModelController {
    //注入用户服务接口
    @Autowired
    private UserService userService=null;
    @GetMapping("/model")
    public String useModel(Model model,Long id){
        User user = userService.getUser(id);
        model.addAttribute("user",user);
        return "data/user";
    }
    @GetMapping("/modelMap")
    public ModelAndView useModelMap(ModelMap modelMap,Long id){
        User user = userService.getUser(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("data/user");
        modelMap.addAttribute("user",user);
        return mv;
    }
    @GetMapping("/useModelAndView")
    public ModelAndView useModelAndView(Long id,ModelAndView mv){
        User user = userService.getUser(id);
        mv.addObject("user",user);
        mv.setViewName("data/user");
        return mv;
    }
}
