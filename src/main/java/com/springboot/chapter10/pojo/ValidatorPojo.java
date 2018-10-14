package com.springboot.chapter10.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

public class ValidatorPojo {
    //非空判断
    @NotNull(message = "id不能为空")
    private Long id;
    @Future(message = "需要一个将来日期")//只能是将来的日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")//日期格式化转换
    @NotNull//不能为空
    private Date date;
    @NotNull//不能为空
    @DecimalMin(value = "0.1")//最小值0.1元
    @DecimalMax(value = "10000.00")//最大值10000元
    private Double doubleValue = null;
    @Max(value = 88,message = "最大值为88")
    @Min(value = 1,message = "最小值为1")
    @NotNull//不能为空
    private Long range;
    @Email(message = "邮箱格式错误")
    private String email;
    @Size(min = 20,max = 30,message = "字符串长度要求在20到30之间")
    private String size;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getDoubleValue() {
        return doubleValue;
    }

    public void setDoubleValue(Double doubleValue) {
        this.doubleValue = doubleValue;
    }

    public Long getRange() {
        return range;
    }

    public void setRange(Long range) {
        this.range = range;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
