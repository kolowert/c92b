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
		<br />

		<h3 class="text-info">Order page stub</h3>

		<%
		Operator dutyOperator = null;
		int dutyOperatorId = -1;
		String dutyOperatorName = "undefined";
		String dutyOperatorRole = "undefined";
		String info = "undefined";
		String brief = "undefined";

		Object preDutyOperator = request.getSession().getAttribute("dutyOperator");

		if (preDutyOperator instanceof Operator) {
			dutyOperator = (Operator) preDutyOperator;
			dutyOperatorId = dutyOperator.getId();
			dutyOperatorName = dutyOperator.getLogin();
			dutyOperatorRole = dutyOperator.getRole();
			info = dutyOperator.toString();
			brief = dutyOperator.briefInfo();
			
		} else {
			request.setAttribute("failMessage", "Current Login is off in some reason");
			getServletContext().getRequestDispatcher("/login-fail.jsp").forward(request, response);
		}
		%>

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
			<%=info%></p>
		<p>
			operator Brief Info:
			<%=brief%></p>

		<jsp:include page="_footer.jsp"></jsp:include>
	</div>
</body>
</html>