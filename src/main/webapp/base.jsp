<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="fun.kolowert.c92b.bean.Operator"%>

<!DOCTYPE html>
<html>
<head>
<title>Base Page</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>
</head>

<body>
	<div class="container-lg p-1">
		<jsp:include page="_header.jsp"></jsp:include>
		<jsp:include page="_menu.jsp"></jsp:include>
		<br />

		<h3 style="color: Brown">base page</h3>

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

		<h3 style="color: Brown">base-BASE.jsp page</h3>
		<p>
			operator id:
			<%=dutyOperatorId%></p>
		<p>
			operator Login:
			<%=dutyOperatorName%></p>
		<p>
			operator Role:
			<%=dutyOperatorRole%></p>
		<p>
			operator Full Info:
			<%=dutyOperator.toString()%></p>
		<p>
			operator Brief Info:
			<%=dutyOperator.briefInfo()%></p>

		<jsp:include page="_footer.jsp"></jsp:include>
	</div>
</body>
</html>