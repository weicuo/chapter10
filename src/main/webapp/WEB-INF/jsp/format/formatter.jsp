<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/10/8
  Time: 16:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>格式化</title>
</head>
<body>
    <form action="./commit" method="post">
        <table>
            <tr>
                <td>日期(yyyy-MM-dd)</td>
                <td>
                    <input type="text" name="date" value="2018-10-08">
                </td>
            </tr>
            <tr>
                <td>金额(#,###.00)</td>
                <td>
                    <input type="text" name="number" value="1,234,567.89">
                </td>
            </tr>
            <tr>
                <td colspan="2" align="right">
                    <input type="submit" value="提交">
                </td>
            </tr>
        </table>
    </form>
</body>
</html>