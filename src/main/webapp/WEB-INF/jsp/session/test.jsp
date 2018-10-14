<%@ page import="com.springboot.chapter10.pojo.User" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/10/11
  Time: 13:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.springboot.chapter10.pojo.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="content-type" content="text/html"; charset="UTF-8">
    <title>测试@sessionAttributes</title>
</head>
<body>
    <%
        //从session中取出数据
        Long id = (Long)session.getAttribute("id_new");
        User user=(User)session.getAttribute("user");
        //展示数据
        out.print("<br>user_name="+user.getUserName());
        out.println("<br>id_name+"+id);
    %>
</body>
</html>
