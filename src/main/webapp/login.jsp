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
</head>

<body>
	<jsp:include page="_header-light.jsp"></jsp:include>

	<div style="color: DimGray">
		<h3>Choose your id:login and type password to enter</h3>
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