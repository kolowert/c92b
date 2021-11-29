<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="fun.kolowert.c92b.bean.Operator"%>
<%@page import="fun.kolowert.c92b.utility.Utils"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Entry Page</title>
</head>

<body>
	<div style="background: #E0E0E0; height: 55px; padding: 5px;">
		<h1>Cash Register c92b</h1>
	</div>
	<div style="padding: 10px;">
		<h3>Choose your id:login and type password to enter</h3>
	</div>

	<form action="loginServ" method="POST">
		<br>
		Operator: <select name="operator">
			<%
			Object preOperators = request.getAttribute("operators");
			List<Operator> operators = Utils.convert(preOperators);
			for (Operator operator : operators) {
				out.println("<option>id: " + operator.getId() + " ---  " + operator.getLogin() + "</option>");
			}
			%>
		</select> 
		
		<br> <br> 
		Password: <input type="password"
		name="password" /> <br> <br> <input type="submit"
		value="Log in" />
	</form>

	<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>