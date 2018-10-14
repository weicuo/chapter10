package com.springboot.chapter10.converter;

import com.springboot.chapter10.pojo.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

//springmvc通过http的参数类型（String）和控制器的参数类型（User）进行转换
@Component
public class StringToUserConverter implements Converter<String, User> {
    //转换方法
    @Override
    public User convert(String userStr) {
        User user = new User();
        String[] strArr = userStr.split("-");
        long id = Long.parseLong(strArr[0]);
        String userName = strArr[1];
        String note = strArr[2];
        user.setId(id);
        user.setUserName(userName);
        user.setNote(note);
        return user;
    }
}
