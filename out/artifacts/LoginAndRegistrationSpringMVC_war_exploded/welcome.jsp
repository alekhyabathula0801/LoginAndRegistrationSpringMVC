<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<%
    response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
    if (request.getAttribute("userName")==null) {
        request.getSession().setAttribute("message","Please Login");
        response.sendRedirect("login.jsp");
    }
%>
<div class="welcome">
    <div class="form">
        <h3>Welcome </h3> <br>
        <p> Have a nice day ${userName} </p>
        <form action="Logout" method="post">
            <input type="submit" value="Logout">
        </form>
    </div>
</div>
</body>
</html>
