<%-- 
    Document   : index
    Created on : Dec 13, 2015, 4:46:39 PM
    Author     : Hossain
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <title>Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width">
    </head>
    <body style="text-align: center;">
        <h1>Login</h1>
        
        <!--For empty fields-->
        <div style="color: red;">${param.error}</div>
        
        <div align="center" style="margin-top: 50px;">
 
            <!--Ask for login credentials-->
            <form action="Servlet14">
                Enter Username:  <input type="text" name="username"/> <br>
                Enter Password:  <input type="password" name="password"/> <br>
            <input type="submit" value="Login" name="action">
            </form>

        </div>
    </body>
</html>
