<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="fun.kolowert.c92b.bean.Operator"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Base Page</title>
</head>

<body>
	<jsp:include page="_header.jsp"></jsp:include>

	<h2 style="color: Brown">base page</h2>

	<%
	Operator dutyOperator = null;
	int dutyOperatorId = -1;
	String dutyOperatorName = "undefined";
	String dutyOperatorRole = "undefined";
	
	Object preDutyOperator = request.getSession().getAttribute("dutyOperator");
	if (preDutyOperator instanceof Operator) {
		dutyOperator = (Operator) preDutyOperator;
		dutyOperatorId = dutyOperator.getId();
		dutyOperatorName = dutyOperator.getLogin();
		dutyOperatorRole = dutyOperator.getRole();
	}
	%>
	
	<h2 style="color: Brown">base-BASE.jsp page</h2>
	<p>operator id: <%= dutyOperatorId %></p>
	<p>operator Login: <%= dutyOperatorName %></p>
	<p>operator Role: <%= dutyOperatorRole %></p>
	<p>operator Full Info: <%= dutyOperator.toString() %></p>
	<p>operator Brief Info: <%= dutyOperator.briefInfo() %></p>
	
	<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>