<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
<%
    response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
    if (session.getAttribute("emailId")==null)
        response.sendRedirect("login.jsp");
%>
Welcome<br>Have a Nice Day
<form action="Logout" method="post">
    <input type="submit" value="Logout">
</form>
</body>
</html>
