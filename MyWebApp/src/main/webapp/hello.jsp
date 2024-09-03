<%@page import="java.time.LocalTime"%>
<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>
</head>
<body>
	<%
	String str = "Welcome to JSP";
	%>
	<h1><%=str%></h1>
	<h2>
		Today:
		<%=LocalDate.now()%>
	</h2>
	<%
	for (int i = 0; i < 5; i++) {
	%>
		<div>Hello Everyone</div>
	<%
	}
	%>
	<br>
	<% 
	for (int i = 1; i <= 10; i++) {
	%>
		<div>2 * <%= i %> = <%= 2*i %> </div>
	<%
	}
	%>
	
	<% String agent = request.getHeader("user-agent"); %>
	
	<h1>You care currently using : <%= agent %></h1>
	<h2><%= LocalTime.now() %></h2>
	
	<% 
		//response.setHeader("refresh", "1"); 
		//response.setHeader("refresh", "5;url=https://www.google.com");
		response.addHeader("Pragma", "no-cache");
	    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	    response.addHeader("Cache-Control", "pre-check=0, post-check=0");
	    response.setDateHeader("Expires", 0);
	%>
</body>
</html>