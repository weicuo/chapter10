package com.springboot.chapter10.controller;

import com.springboot.chapter10.pojo.User;
import com.springboot.chapter10.pojo.ValidatorPojo;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/my")
public class MyController {
    @ResponseBody
    @RequestMapping("/no/annotation")
    public Map<String,Object> noAnnotation(Integer intVal,Long longVal,String strVal){
        Map<String,Object> map=new HashMap<>();
        map.put("intVal",intVal);
        map.put("longVal",longVal);
        map.put("strVal",strVal);
        return map;
    }
    @GetMapping("/annotation")
    @ResponseBody
    public Map<String,Object> requestParam(
            @RequestParam("int_val") Integer intVal,
            @RequestParam("long_val") Long longVal,
            @RequestParam("str_val") String strVal){
        Map<String,Object> map=new HashMap<>();
        map.put("intVal",intVal);
        map.put("longVal",longVal);
        map.put("strVal",strVal);
        return map;
    }
    @GetMapping("/requestArray")
    @ResponseBody
    public Map<String,Object> requestArray(int [] intArr,Long [] longArr,String[] strArr){
        Map<String,Object> map=new HashMap<>();
        map.put("intArr",intArr);
        map.put("longArr",longArr);
        map.put("strArr",strArr);
        return map;
    }
    //映射jsp页面
    @GetMapping("/format/form")
    public String showFormat(){
        return "/format/formatter";
    }
    //获取提交参数
    @PostMapping("format/commit")
    @ResponseBody
    public Map<String,Object> format(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
            @NumberFormat(pattern = "#,###.##") Double number){
        Map<String,Object> dataMap=new HashMap<>();
        dataMap.put("date",date);
        dataMap.put("number",number);
        return dataMap;
    }
    @GetMapping("/valid/page")
    public String validPage(){
        return "/validator/pojo";
    }
    @RequestMapping(value = "/valid/validate")
    @ResponseBody
    public Map<String,Object> validate(
            @Valid @RequestBody ValidatorPojo vp, Errors errors){
        Map<String,Object> errMap = new HashMap<>();
        List<ObjectError> errorList = errors.getAllErrors();
        for(ObjectError oe : errorList){
            String key = null;
            String msg = null;
            if(oe instanceof FieldError){
                FieldError fieldError = (FieldError) oe;
                key=fieldError.getField();//获取错误验证字段名
            }else {
                key = oe.getObjectName();//获取验证对象的名字
            }
            msg = oe.getDefaultMessage();
            errMap.put(key,msg);
        }
        return errMap;
    }
}
