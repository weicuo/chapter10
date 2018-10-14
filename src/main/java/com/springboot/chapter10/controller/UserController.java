package com.springboot.chapter10.controller;

import com.springboot.chapter10.pojo.User;
import com.springboot.chapter10.service.UserService;
import com.springboot.chapter10.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService=null;
    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.setValidator(new UserValidator());
        binder.registerCustomEditor(Date.class,new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"),false));
    }
    @GetMapping("/validator")
    @ResponseBody
    public Map<String,Object> validator(@Valid User user, Errors errors,Date date){
        Map<String,Object> map=new HashMap<>();
        map.put("user",user);
        map.put("date",date);
        if(errors.hasErrors()){
            List<ObjectError> errorList = errors.getAllErrors();
            for(ObjectError error :errorList){
                if(error instanceof FieldError){
                    FieldError fe = (FieldError) error;
                    map.put(fe.getField(),fe.getDefaultMessage());
                }else {
                    map.put(error.getObjectName(),error.getDefaultMessage());
                }
            }
        }
        return map;
    }
    @GetMapping("/add")
    public String add(){
        return "/user/add";
    }
    @PostMapping("/insert")
    @ResponseBody
    public User insert(@RequestBody User user){
        userService.insertUser(user);
        return user;
    }
    //{...}代表占位符，还可以配置参数名称
    @GetMapping("/{id}")
    //相应为json数据集
    @ResponseBody
    //@pathVariable通过名称获取参数
    public User get(@PathVariable Long id){
        return userService.getUser(id);
    }
    @GetMapping("/converter")
    @ResponseBody
    public User getUserByConverter(User user){
        return user;
    }
    @GetMapping("/list")
    @ResponseBody
    public List<User> list(List<User> userList){
        return userList;
    }
    /*//显示用户
    @GetMapping("/show")
    public String showUser(Long id, Model model){
        User user = userService.getUser(id);
        model.addAttribute("user",user);
        return "data/user";
    }
    //使用字符串之sing跳转
    @GetMapping("/redirect1")
    public String redirect1(String userName,String note){
        User user = new User();
        user.setUserName(userName);
        user.setNote(note);
        //插入数据后，回填user的id
        userService.insertUser(user);
        return "redirect:/user/show?id=" + user.getId();
    }
    //使用模型与视图指定跳转
    @GetMapping("/redirect2")
    public ModelAndView redirect2(String userName,String note){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        user.setUserName(userName);
        user.setNote(note);
        userService.insertUser(user);
        modelAndView.setViewName("redirect:/user/show?id="+user.getId());
        return modelAndView;
    }*/
    //显示用户
    //参数user直接从数据模型中RedirectAttributes对象中取出
    @RequestMapping("/showUser")
    public String showUser(User user,Model model){
        System.out.println(user.getId());
        return "data/user";
    }
    //使用字符串指定跳转
    @RequestMapping("/redirect1")
    public String redirect1(String userName, String note, RedirectAttributes ra){
        User user = new User();
        user.setUserName(userName);
        user.setNote(note);
        userService.insertUser(user);
        //保存需要传递给重定向的对象
        ra.addFlashAttribute("user",user);
        return "redirect:/user/showUser";
    }
    //使用模型与视图指定跳转
    @RequestMapping("/redirect2")
    public ModelAndView redirect2(String userName,String note,RedirectAttributes ra){
        User user = new User();
        user.setUserName(userName);
        user.setNote(note);
        userService.insertUser(user);
        ModelAndView mv = new ModelAndView();
        ra.addFlashAttribute("user",user);
        mv.setViewName("redirect:/user/showUser");
        return mv;
    }
    @GetMapping("/header/page")
    public String headerPage(){
        return "header";
    }
    @PostMapping("/header/user")
    @ResponseBody
    public User headerUser(@RequestHeader("id") Long id){
        User user = userService.getUser(id);
        return user;
    }
}
