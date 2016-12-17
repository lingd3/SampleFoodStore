<%@page import="entity.Cuisine"%>
<%@page import="service.CuisineService"%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>欢迎光临</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css/style.css">
</head>
  
<body background="<%=request.getContextPath()%>/static/images/background.jpg">
	<h1>菜单</h1>
	<a href="<%=request.getContextPath() %>/index" class="a">首页</a> >> 菜单
	<hr>
	<div class="table">
		<table>
			<tr>
				<td>
				<%
					List<Cuisine> list = (List<Cuisine>)request.getAttribute("menu_cuisines");
					if (list != null && list.size() > 0) {
						for (Cuisine c:list) {
				%>
					<div class="cuisine">
						<div>
			            	<dl>
			            		<dt>
			            			<a href="<%=request.getContextPath() %>/cuisine_detail/<%=c.getId()%>">
										<img src="<%=request.getContextPath() %>/static/images/<%=c.getPicture()%>" class="img"/>
									</a>
			            		</dt>
			              		<dd class="detail"><%=c.getName() %></dd> 
			              		<dd class="detail">
			              			￥<%=c.getPrice() %>
			              			<%
										if (c.getVip() == 1) {
									%>
									<img src="<%=request.getContextPath() %>/static/images/vip.jpg" class="vip">
									<%	}%>
			              		</dd>
			             	</dl>
			          	</div>
					</div>
				<%
						}
					}
				%>
				</td>
			</tr>
		</table>
	</div>
  
  </body>
</html>



























