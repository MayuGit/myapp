<%-- 
    Document   : dashboard
    Created on : 19-Oct-2016, 20:31:47
    Author     : ubuntu
--%>

<%@page import="com.myclasses.UserBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
        UserBean user = (UserBean) session.getAttribute("user");
        if(user!=null){
        %>
        Welcome to my site, <%=user.getLastname()%><br>
        Your registered email is <%=user.getEmail()%><br>
       
        <%}
        else
        {%>
            You have not logged in, .
            <a href="../index.jsp">please login to continue</a>
        <%}%>
    </body>
</html>
