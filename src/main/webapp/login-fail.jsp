<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="fun.kolowert.c92b.bean.Operator"%>
<%@page import="fun.kolowert.c92b.utility.Utils"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>
</head>

<body>
	<div class="container-lg p-1">
		<jsp:include page="_header-light.jsp"></jsp:include>
		<br />
		<h3>Login process was interrupted</h3>

		<p style="color: Fuchsia"><%=request.getAttribute("failMessage")%></p>
		<a href="${pageContext.request.contextPath}/entry">try again</a>

		<jsp:include page="_footer.jsp"></jsp:include>
	</div>
</body>
</html>