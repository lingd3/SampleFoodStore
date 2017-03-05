<%@page import="entity.User"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="entity.Cuisine"%>
<%@page import="java.util.HashMap"%>
<%@page import="entity.Order"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css/style.css">
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/store.js"></script>
	<title>订单详情</title>
</head>
<body background="<%=request.getContextPath()%>/static/images/background.jpg">
	<h1>我的订单</h1>
	<a href="<%=request.getContextPath() %>/index" class="a">首页</a> >> <a href="<%=request.getContextPath() %>/menu" class="a">菜单</a> >> 订单
	<hr> 
 
	<div id="order">
		<form>		
			<table>
				<tr>
					<th>菜品名称</th>
					<th>菜品单价</th>
					<th>菜品价格</th>
					<th>购买数量</th>
					<th>操作</th>
				</tr>
				<% 
					User u = (User)request.getSession().getAttribute("user");
					//首先判断session中是否有订单对象
					if(request.getSession().getAttribute("order")!=null) {
				%>
				<!-- 循环的开始 -->
				     <% 
				     	 Order order = (Order)request.getSession().getAttribute("order");
				         HashMap<Cuisine,Integer> cuisines = order.getCuisines();
				         Set<Cuisine> items = cuisines.keySet();
				         Iterator<Cuisine> it = items.iterator();
				         while (it.hasNext()) {
				            Cuisine c = it.next();
				     %> 
				<tr>
					<td><%=c.getName() %></td>
					
					<%
						if (c.getVip() == 1) {
					%>
					<td><%=c.getPrice() %> Vip价: <%=c.getPrice()*0.95%></td>
					<%} else { %>
					<td><%=c.getPrice() %></td>
					<%} %>
					<%
						if (u.getVip() == 1 && c.getVip() == 1) {
					%>
					<td><%=c.getPrice()*0.95*cuisines.get(c) %></td>
					<%} else { %>
					<td><%=c.getPrice()*cuisines.get(c) %></td>
					<%} %>
					<td><%=cuisines.get(c)%></td>
					<td class="delete"><a href="<%=request.getContextPath() %>/delete/<%=c.getId()%>" onclick="delcfm();">删除</a></td>
				</tr>
				     <% 
				         }
				     %>
				<!--循环的结束-->
				
			</table>
			<div class="total">
				<span id="total">总计：<%=order.getTotalPrice()%>￥</span>
			 	<a href="<%=request.getContextPath()%>/orderSuccess">点击下单</a>
			</div>
			  <% 
			    }
			 %>
		</form>
	</div>
  </body>
</html>