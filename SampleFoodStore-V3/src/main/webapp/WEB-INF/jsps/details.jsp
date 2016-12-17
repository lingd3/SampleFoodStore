<%@page import="dao.CuisinesDaoImpl"%>
<%@page import="dao.CuisinesDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entity.Cuisine"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Cuisine c = (Cuisine)request.getAttribute("cuisine");
%>    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><%=c.getName() %></title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css/style.css">
	<script src="<%=request.getContextPath() %>/static/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/store.js"></script>
</head>
<body background="<%=request.getContextPath()%>/static/images/background.jpg">
	<h1>菜品详情</h1>
	<a href="<%=request.getContextPath() %>/index" class="a">首页</a> >> <a href="<%=request.getContextPath() %>/menu" class="a">菜单</a> >> 详情
	<hr>
	
	<div class="main">
		<table>
			<tr>
				<td><img src="<%=request.getContextPath() %>/static/images/<%=c.getPicture() %>" class="img_detail"></td>
				<td>
					<table class="details">
						<tr>
							<td><%=c.getName() %></td>
						</tr>
						<tr>
							<td class="more">
								<p>介绍：<%=c.getDescription() %></p>
							</td>
						</tr>
						<tr>
							<td>
								价格：<%=c.getPrice() %>￥ 
								<%
									if (c.getVip() == 1) {
								%>
								<img src="<%=request.getContextPath() %>/static/images/vip.jpg" class="vip">
								<%	}%>
							</td>
						</tr>
						<tr>
			                 <td>购买数量：<span id="sub" onclick="sub();">- </span><input type="text" id="number" name="number" value="1" size="2"/><span id="add" onclick="add();"> +</span></td>
			            </tr>
			            <%
			            	if (request.getSession().getAttribute("user") != null) {
			            %>
			            <tr>
			            	<td>
			            		<a href="javascript:addToOrder(<%=c.getId()%>)"><input type="submit" value="加入订单" class="input"></a>
			            		<a href="<%=request.getContextPath()%>/order"><input type="submit" value="查看订单" class="input"></a>
			            	</td>
			            </tr>
			            <%} else { %>
			            <tr>
			            	<td>
			            		<a href="<%=request.getContextPath()%>/login"><input type="submit" value="请登录，再点餐" class="input"></a>
			            	</td>
			            </tr>
			            <%} %>
					</table>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>