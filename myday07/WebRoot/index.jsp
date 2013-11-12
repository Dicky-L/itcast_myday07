<%@page import="cn.itcast.login.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'index.jsp' starting page</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
欢迎
<%
	User user = (User)session.getAttribute("user");
	if(user != null){
	out.write(user.getUsername());
	}
 %>
 <a href="/myday07/servlet/LogoutServlet">注销</a>
  </body>
</html>
