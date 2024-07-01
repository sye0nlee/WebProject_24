<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<%
	request.setCharacterEncoding("UTF-8");
	session.setAttribute("user_id", request.getParameter("user_id"));
	String user_id = (String)session.getAttribute("user_id");
	System.out.println(user_id);
%>
<body>

<div class="w3-container">
  <h2>Shopping Mall</h2>
</div>

<div class="w3-bar w3-yellow">

				<div>
						<a href="index.jsp?user_id=${user_id}" class="w3-bar-item w3-button w3-hover-white">Main</a>
						<a href="#" class="w3-bar-item w3-button w3-hover-white">Street-Wear</a>
						<a href="#" class="w3-bar-item w3-button w3-hover-white">Young-Casual</a>
						<a href="#" class="w3-bar-item w3-button w3-hover-white">Accessory</a>
						<a href="mypage.jsp?user_id=${user_id}" class="w3-bar-item w3-button w3-hover-white">My-Page</a>
				</div>
				<div style="position: fixed; right: 20px; bottom:20px;">
						<c:if test="${ user_id == null }">
							<a href="signIn.jsp" class="w3-bar-item w3-button w3-hover-gray">로그인</a>
							<a href="signUp.jsp" class="w3-bar-item w3-button w3-hover-gray">회원가입</a>
						</c:if>
						<c:if test="${ user_id != null}">
							<a href="mypage.jsp" class="w3-bar-item w3-button w3-hover-white"><%= user_id %> 님 </a>
							<a onclick="javascript:alert('로그아웃 되었습니다.'); location.href = 'index.jsp?logout=1'" href="index.jsp" class="w3-bar-item w3-button w3-hover-white">로그아웃</a>
							<!-- onclick 이벤트의 리턴값이 true이면 href로 연결된 링크로 이동하고, false이면 이동하지 않는다. // 더 좋은 방법은 없는지... -->
							
						</c:if>
						<c:if test="${ user_id.equals('10') }">
							<a href="#" class="w3-bar-item w3-button w3-hover-white">관리자 모드</a>
						</c:if>
				

				</div>
</div>

</body>
</html>


