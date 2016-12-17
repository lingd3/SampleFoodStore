<%@page import="entity.Cuisine"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>欢迎光临</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css/style.css">
</head>
<body background="<%=request.getContextPath()%>/static/images/background.jpg">
	<h1>欢迎光临</h1>
	<hr>
	<form action="<%=request.getContextPath()%>/index">
		请选择城市:<input type="text" name="city">
		<input type="submit" value="查询">
	</form>
	<form action="<%=request.getContextPath()%>/cuisine_detail">
		请输入菜名:<input type="text" name="name">
		<input type="submit" value="查询">
	</form><br>
	<a href="<%= request.getContextPath()%>/menu">查看详细菜单-></a><br>
	
	猜您喜欢：<br>
	<div class="table">
		<table>
			<tr>
				<td>
				<%
					List<Cuisine> list = (List<Cuisine>)request.getAttribute("cuisines");
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
			              		<dd class="detail">￥<%=c.getPrice() %></dd>
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