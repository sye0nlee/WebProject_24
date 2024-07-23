<%@ page language="java" contentType="text/html; charset=EUC-KR"

    pageEncoding="EUC-KR"%>

<%@ page import="java.sql.*" %>

<%@ page import="javax.sql.*" %>

<%@ page import="javax.naming.*" %>

<%

           Connection conn = null;

 

           try {

                     Context init= new InitialContext();

    //초기화 객체를 얻어오고

                     DataSource ds= (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");

    //lookup에서

                     conn = ds.getConnection();

    //getConnection하면 이제 객체를 얻어옴.

                    

                     out.println("<h3>연결되었습니다.</h3>");

           }catch(Exception e){

                     out.println("<h3>연결에 실패하였습니다.</h3>");

                     e.printStackTrace();

           }

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

<title>Insert title here</title>

</head>

<body>

 

</body>

</html>