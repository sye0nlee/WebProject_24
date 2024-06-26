<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>로그인</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="./memberJoinCheck.js"></script>
</head>
<body>
	
	<%
		
		
		String cookie = "";
		Cookie[] cookies = request.getCookies();
		if(cookies != null && cookies.length > 0){
			for(int i = 0; i < cookies.length; ++i){
				if(cookies[i].getName().equals("userId")){
					cookie = cookies[i].getValue();
				}
			}
		}
		
		
	%>
	<%@ include file="List/menuBar.jsp" %>
	
	
	<center>
	<div class="w3-main" style = "display: table;">
		<h3>로그인</h3>
		<div>
			<div class="w3-container w3-quarter w3-card-2" style="display: table-cell; width:200px; height:220px;">
				<form action="/SignInController" method = "post" style="margin-top:25px">
						<fieldset>
							<input type="text" class="w3-input" style="width:90%" name="id" id="id" placeholder="아이디" value = <%=cookie%>>
							
							<input type="password" class="w3-input" style="width:90%" name="pwd" id="pwd" placeholder="비밀번호">
						</fieldset>
						
					<input type="submit" class="w3-button w3-section w3-black w3-gray" value="로그인">
					<input type="checkbox" name="loginChk" value="Y"> id 저장
				</form>
			</div>
		</div>
	</div>
	</center>

</body>
</html>