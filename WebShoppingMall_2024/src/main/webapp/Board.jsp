<%@ page contentType="text/html; charset=UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%@ include file="List/menuBar.jsp" %>

</head>
<body>
<center>
	<div id="bbs">
	<div id="bbs_title">

	<br><br>
	
	<form action="/ItemUploadController" method="post" enctype="multipart/form-data">
		<div id="bbsCreated">
			<div class="bbsCreated_bottomLine">
					상품명 <input type="text" name="item_name" style="width:25%" class="boxTF" required/>
			</div>
			<div class="bbsCreated_bottomLine">
				<dl>
					판매자 <input type="text" name="item_seller" style="width:25%" class="boxTF" value = "<%= user_id %>" readonly>
				</dl>		
			</div>
			
			<div id="bbsCreated_content">
				<dl>
					<dt>내&nbsp;&nbsp;&nbsp;&nbsp;용</dt>
					<dt><textarea cols="50" rows="10" name="item_info" style="overflow-y:scroll; resize:vertical"></textarea></dt>
				</dl>
				</div>
			<div>
				<dl>
					<input type="file" name="fileName" required>
				</dl>
			</div>
			<div class="bbsCreated_noLine">
				<dl>
					가격 <input type="Number" name="item_price" min = "0" step = "100" value = "0" required/> 원 /
					재고수량 <input type="Number" name="item_left" style="width:10%" min = "0" value = "0" required/> 개 <br /><br />
					패스워드 = <input type="password" name="info_pwd" style="width:10%" class="boxTF"  required/>
				</dl>		
			</div>	
		
		</div>
		
		<div id="bbsCreated_footer">
			<input type="submit" value=" 등록하기 " class="btn1" onclick="sendIt();"/>
			<input type="reset" value=" 다시입력 " class="btn2" 
			onclick="document.myForm.subject.focus();"/>
			<input type="button" value=" 작성취소 " class="btn3" 
			onclick="history.back();"/>
		</div>
	
	</form>

</div>
</center>

</body>
</html>