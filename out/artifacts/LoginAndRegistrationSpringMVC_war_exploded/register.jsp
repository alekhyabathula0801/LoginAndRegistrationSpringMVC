<%--
  Created by IntelliJ IDEA.
  User: arun kumar
  Date: 28-06-2020
  Time: 22:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="register">
<div class="form">
    <form action="Registration" method="post">
        <h3>Registration Page </h3><br>
        <input type="text" name="userName" required placeholder="User Name"><br>
        <input type="text" name="emailId" required placeholder="Email ID"
               pattern="^[a-zA-Z0-9]+([._+-][0-9a-zA-Z]+)*@[a-zA-Z0-9]+.[a-zA-Z]{2,4}([.][a-zA-Z]{2,3})?$"
               title="sample email pattern -> char@char.com or char@char.com.in"><br>
        <input type="password" name="password" required placeholder="Password" pattern="(?=.*[A-Z])(?=.*[^0-9a-zA-Z])(?=.*[0-9]).{5,}"
               title="Password must contain atleast one capital letter, special character and number with minimum of 5 characters"><br>
        <input type="submit" value="Register" style="margin-right: 40px"><a style="font-size: 30px;" href="login.jsp">Login</a><br>
        <c:if test = "${not empty message}">
            <p>${message}</p>
        </c:if>
    </form>
</div>
</div>
<%
    session.setAttribute("message",null);
%>
</body>
</html>
