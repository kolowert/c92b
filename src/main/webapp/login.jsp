<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="fun.kolowert.c92b.bean.Operator"%>
<%@page import="fun.kolowert.c92b.utility.Utils"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<title>Login Page</title>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>
</head>

<body>
	<jsp:include page="_header-light.jsp"></jsp:include>

	<div style="color: DimGray">
		<h5 style="text-align: left">Choose your id:login and type password to enter</h5>
	</div>

	<form action="main" method="POST">
		<br> Operator: <select name="operator">
			<%
			Object preOperators = request.getAttribute("operators");
			List<Operator> operators = Utils.convert(preOperators);
			for (Operator operator : operators) {
				out.println("<option>id: " + operator.getId() + " ---  " + operator.getLogin() + " ---  " + operator.getRole()
				+ "</option>");
			}
			%>
		</select> <br> <br> Password: <input type="password" name="password" />

		<br> <br> <input type="submit" value="Log in" />
	</form>

	<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>