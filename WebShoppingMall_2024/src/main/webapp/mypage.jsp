<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="List/menuBar.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
	
	<% 
	
	//로그아웃 유무에 따라 세션값에 저장된 id 보여줌.
		String logout = request.getParameter("logout");
		if(logout!=null){
			session.setAttribute("user_id", null);
			session.setMaxInactiveInterval(0);	//세션시간 0 

		}else{
			session.setAttribute("user_id", request.getParameter("user_id"));
		}
	%>
	

