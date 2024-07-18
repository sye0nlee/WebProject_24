<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import = "common.Item.ItemDTO" %>
<%@ page import = "common.Item.ItemDAO" %>
<%@ page import = "java.util.List" %>
<%@ page import = "java.sql.*" %>
<%@ page import = "java.util.ArrayList" %>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

<%@ include file="List/menuBar.jsp" %>

</head>

</head>
<body>

<h1>STREET :</h1>

<div class="grid-container">
  <div class="grid-item">
  <%
	int pageNumber = 1;
	ItemDAO dao = new ItemDAO();
	ArrayList<ItemDTO> list1 = dao.displayItem(pageNumber);
	ArrayList<ItemDTO> list2 = dao.displayItem(pageNumber);
	for(int i = 0; i<list1.size(); i = i+3){
		
%>
	 <dl>
	 	<div class="w3-row-padding">
		    <div class="w3-col s4">
			      <img src="List/img/sound.jpg" style="width:100%">
			      <center>
			      	<dt>상품명 : <%=list1.get(i).getItem_name() %></dt>
			      	<dt>가격 : <%=list1.get(i).getItem_price() %> 원</dt>
			      </center>
			  </div>
			  <div class="w3-col s4">
			      <img src="List/img/sound.jpg" style="width:100%">
			      <center>
			      	<dt>상품명 : <%=list1.get(i+1).getItem_name() %></dt>
			      	<dt>가격 : <%=list1.get(i+1).getItem_price() %> 원</dt>
			      </center>
			  </div>
			  <div class="w3-col s4">
			      <img src="List/img/sound.jpg" style="width:100%">
			      <center>
			      	<dt>상품명 : <%=list1.get(i+2).getItem_name() %></dt>
			      	<dt>가격 : <%=list1.get(i+2).getItem_price() %> 원</dt>
			      </center>
			  </div>
		 </div>
		  
	</dl>
  
  </div>
</div>
<%
		}
	

%>
</body>
</html>


