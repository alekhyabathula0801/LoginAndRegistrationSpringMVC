<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<div class="form">
    <form action="Login" method="post">
        <h3>Login Page</h3><br>
        <input type="text" name="emailId" required placeholder="Email ID"
               pattern="^[a-zA-Z0-9]+([._+-][0-9a-zA-Z]+)*@[a-zA-Z0-9]+[.][a-zA-Z]{2,4}([.][a-zA-Z]{2,3})?$"
               title="sample email pattern - char@char.com or char@char.com.in"> <br>
        <input type="password" name="password" required placeholder="Password"
               pattern="(?=.*[A-Z])(?=.*[^0-9a-zA-Z])(?=.*[0-9]).{5,}"
               title="Password must contain atleast one capital letter, special character and number with minimum of 5
               characters"><br>
        <input type="submit" value="Login" style="margin-right: 40px"><br>
    </form>
</div>
</body>
</html>
