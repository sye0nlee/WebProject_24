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
	
	for(int i = 0; i<list1.size(); i++)
	{
		int row = i % 3;
		ServletContext sc = getServletContext();
		String realFolder = sc.getRealPath("/File");
		String[] split = realFolder.split("wtpwebapps");
		realFolder = split[1]; // realPath에서 프로젝트 경로만 남김. 왜 상대경로로만 될까...ㅜㅜ 
		String fullpath1 = realFolder + "\\"+ dao.displayImg(list1.get(i).getItem_id()); 
		//String fullpath2 = realFolder + "\\"+ dao.displayImg(list1.get(i+1).getItem_id()); 
		//String fullpath3 = realFolder + "\\"+ dao.displayImg(list1.get(i+2).getItem_id()); 
		
	%>
	<c:if test = "${ row == 0 }">
	 	<div class="w3-row-padding">
	</c:if>	 
		    <div class="w3-col s4">
			      <img src=<%=fullpath1%>  style="width:100%; padding:5px" alt="img">
			      <center>
			      	<dt>상품명 : <%=list1.get(i).getItem_name() %></dt>
			      	<dt>가격 : <%=list1.get(i).getItem_price() %> 원</dt>
			      </center>
			</div>
		 </div>
	<c:if test = "${ row == 0 }">
	 	</div>
	</c:if>	
	
  
  </div>
</div>
<%
		}
	

%>
</body>
</html>


