<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원정보 등록</title>
</head>
<body>
	<%@ include file="List/menuBar.jsp" %>
	<center>
	<div class="w3-main" style = "display: table;">
		<h3>회원가입</h3>
		<div>
			<div class="w3-container w3-quarter w3-card-2" style="display: table-cell; width:200px; height:350px">
				<form action="/SignUpController" method="post" style="margin-top:25px">
					<fieldset>
						<input type="text" class="w3-input"  style="width:90%" name="id" id="id" required="" placeholder="아이디">
						<input type="password" class="w3-input" style="width:90%" name="pwd" id="pwd" required="" placeholder="비밀번호">
						<input type="text" class="w3-input" style="width:90%" name="name" id="name" required="" placeholder="이름">
						<input type="text" class="w3-input" style="width:90%" name="email" id="email" required="" placeholder="이메일">
						<input type="text" class="w3-input" style="width:90%" name="nickname" id="nickname" required="" placeholder="닉네임">
					</fieldset>
					<input type="submit" class="w3-button w3-section w3-black w3-gray" value="회원 가입">
				</form>
			</div>
		</div>
	</div>
	</center>
</body>
</html>