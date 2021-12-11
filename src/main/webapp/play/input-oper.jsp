<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" 
	import="fun.kolowert.c92b.utility.Role"
	import="fun.kolowert.c92b.utility.Utils" 
	import="java.util.List"%>
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
			<br />
			<h5 style="text-align: left">Register new Operator</h5>
			<br />
		</div>

		<form action="${pageContext.request.contextPath}/oper" method="POST">
			<input type="hidden" name="task" value="inputNew" /> 
			Login: <input name="login" /> &nbsp; &nbsp; Role: <select name="role">
				<%
				List<String> roles = Role.getAll();
				for (String role : roles) {
					out.println("<option>" + role + "</option>");
				}
				%>
			</select> &nbsp; &nbsp; Password: <input type="password" name="password" />

			&nbsp; &nbsp; 
			<input type="submit" value="input" /> 
			&nbsp; &nbsp; 
			<input
				type="button" onclick="location.href='${pageContext.request.contextPath}/play/staff.jsp'"
				value="cancel" />
		</form>

		<jsp:include page="_footer.jsp"></jsp:include>
	</div>
</body>
</html>