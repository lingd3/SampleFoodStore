<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>登录</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css/style.css">
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery-1.8.3.min.js"></script> 
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery.cookie.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/login.js"></script>
</head>
<body background="<%=request.getContextPath()%>/static/images/background.jpg">
	<div class="login">
		<%
			if (request.getAttribute("msg") != null) {
		%>
		<p class="text"><%=	request.getAttribute("msg") %></p>
		<%	} %>
		<form action="<%=request.getContextPath() %>/dologin" method="post">
			用户名:<input id="username" type="text" name="username"><br>
			密码: &nbsp;&nbsp;&nbsp;<input id="password" type="password" name="password"><br>
			<input id="saveUser" type="checkbox">记住账号
			<input type="submit" value="登录" onclick="saveUserInfo();">
		</form>
	</div>
</body>
</html>



