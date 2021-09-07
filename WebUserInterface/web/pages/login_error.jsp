<%--
  Created by IntelliJ IDEA.
  User: muham
  Date: 06/09/2021
  Time: 18:15
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<%@ page import="Utils.SessionUtils" %>
<%@ page import="Constants.Constants" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Online Chat</title>
    <!--        Link the Bootstrap (from twitter) CSS framework in order to use its classes-->
    <link rel="stylesheet" href="../../common/bootstrap.min.css"/>
    <!--        Link jQuery JavaScript library in order to use the $ (jQuery) method-->
    <!--        <script src="script/jquery-2.0.3.min.js"></script>-->
    <!--        and\or any other scripts you might need to operate the JSP file behind the scene once it arrives to the client-->
</head>
<body>
<div class="container">
    <% String usernameFromSession = SessionUtils.getUsername(request);%>
    <% String usernameFromParameter = request.getParameter(Constants.USERNAME) != null ? request.getParameter(Constants.USERNAME) : "";%>
    <% if (usernameFromSession == null) {%>
    <h2>login</h2>
    <form action="login" method="get">
        <input name="username" type="text"/>
        <input type="submit" value="login"/>
    </form>
    <% Object errorMessage = request.getAttribute(Constants.USERNAME_ERROR);%>
    <% if (errorMessage != null) {%>
    <span class="bg-danger" style="color:red;"><%=errorMessage%></span>
    <% } %>
    <% } else {%>
    <h1>Welcome back, <%=usernameFromSession%></h1>
    <a href="../chatroom/chatroom.html">Click here to enter the chat room</a>
    <br/>
    <a href="login?logout=true" id="logout">logout</a>
    <% }%>
</div>
</body>
</html>
