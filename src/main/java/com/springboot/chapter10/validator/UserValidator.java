package com.springboot.chapter10.validator;

import com.springboot.chapter10.pojo.User;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {
    //该验证器只支持User类验证
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(User.class);
    }
    //验证逻辑
    @Override
    public void validate(Object o, Errors errors) {
        if(o == null){
            //直接在参数出报错，这样就不能进入控制器的方法
            errors.rejectValue("",null,"用户不能为空");
            return;
        }
        //强制转换
        User user = (User) o;
        //用户名非空串
        if(StringUtils.isEmpty(user.getUserName())){
            //增加错误，可以进入控制器方法
            errors.rejectValue("userName",null,"用户名不能为空");
        }
    }
}
