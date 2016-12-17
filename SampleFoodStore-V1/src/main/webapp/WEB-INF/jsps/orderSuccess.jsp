<%@page import="entity.Order"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>谢谢惠顾</title>
</head>
<body background="<%=request.getContextPath()%>/static/images/background.jpg">
	<%
		Order order = (Order)request.getSession().getAttribute("order");
		if (order != null) {
	%>
	
	谢谢惠顾，下定成功，您需付款：<%=order.getTotalPrice() %>元。<br>
	返回<a href="<%=request.getContextPath()%>/index">首页</a>
	<%	} else {%>
	您还没有点餐，返回<a href="<%=request.getContextPath()%>/menu">点餐</a>
	
	<%} %>
</body>
</html>