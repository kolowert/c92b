<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="fun.kolowert.c92b.bean.Operator"%>
<%@page import="fun.kolowert.c92b.utility.Utils"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
</head>

<body>
	<jsp:include page="_header.jsp"></jsp:include>
	
	<h3>Login process was interrupted</h3>
	
	<p style="color: Fuchsia"><%= request.getAttribute("failMessage") %></p>
	<a href="${pageContext.request.contextPath}/entry">try again</a>
	
	<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>