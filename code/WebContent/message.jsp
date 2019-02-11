<%@page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
	<meta charset=UTF-8>
	<title>错误信息</title>
	<link rel="stylesheet" type="text/css" href="css/message.css">
	<link rel="icon" type="image/x-ico" href="images/stu.ico">
</head>
<body>
	<main>
		<div class="message">
			<div class="left">
			<%
				//获取提示信息
				String info = (String) request.getAttribute("info");
				//如果提示信息不为空，则输出
				if(info != null){
			%>
				<h3><%=info%></h3>
			<%
				}
				//获取登录成功的用户信息
				User user = (User) session.getAttribute("user");
				//判断用户是否登录
				if(user != null){
					
				}else{
					//out.print("<script>alert('对不起！您还未登录！');</script>"); 
			%>
				<p><%="对不起！您还未登录！"%></p>
			<%
				}	
			%>
			</div>
			<div class="right">
				<a class="relogin" href="login.html">重新登录></a>
			</div>
		</div>
	</main>
	
	<footer>
		<div class="info">
				<ul>
					<a href="#"><li>学生信息管理系统</li></a>
					<a href="#"><li>帮助与反馈</li></a>
					<a href="#"><li>联系我们</li></a>
				</ul>
			</div>
			<div class="copyright">
				&copy; Copyright. All rights reserved. Design by <a href="http://www.github.com/Soarkey/">Soarkey</a>
			</div>
	</footer>
</body>
</html>