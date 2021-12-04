<%@page import="fun.kolowert.c92b.dao.DaoRole"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="fun.kolowert.c92b.dao.DaoRole"%>
<%@page import="fun.kolowert.c92b.utility.Utils"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<title>Input new Operator Page</title>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>
</head>

<body>
	<div class="container-lg p-1">
		<jsp:include page="_header.jsp"></jsp:include>

		<div style="color: DimGray">
			<br /><h5 style="text-align: left">Input new Operator</h5><br />
		</div>

		<!-- ----------------------------------------------------------------------------------------------------------- -->
		<form action="oper" method="POST">
			Name: <input name="login" /> &nbsp; &nbsp; 
			Role: <select name="role">
			<%
				DaoRole daoRole = DaoRole.getInstance();
				List<String> roles = daoRole.getRoles();
				for (String role : roles) {
					out.println("<option>" + role + "</option>");
				}
			%>
			</select> &nbsp; &nbsp; Password: <input type="password" name="password" />
			&nbsp; &nbsp; <input type="submit" value="input" />
			&nbsp; &nbsp; <input type="button" onclick="location.href='staff.jsp'" value="cancel" />
		</form>

		<!-- ----------------------------------------------------------------------------------------------------------- -->

		<jsp:include page="_footer.jsp"></jsp:include>
	</div>
</body>
</html>