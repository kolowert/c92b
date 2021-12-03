<%@page contentType="text/html" pageEncoding="UTF-8"
	import="fun.kolowert.c92b.bean.Operator"
	import="fun.kolowert.c92b.dao.DaoOperator" import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<title>JSP Page</title>
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

		<div class="container mt-4">
			<h5 class="text-muted">List of all operators:</h5>
			<table class="table">
				<thead>
					<tr>
						<th>id</th>
						<th>Login</th>
						<th>Role</th>
						<th class="text-primary">Edit</th>
					</tr>
				</thead>
				<tbody>
					<%
					DaoOperator daoOperator = DaoOperator.getInstance();
					List<Operator> operators = daoOperator.getOperators();
					for (Operator operator : operators) {
						int id = operator.getId();
						String login = operator.getLogin();
						String role = operator.getRole();
						String editLink = "<a href=\"/editOper?id=" + id + "\">edit</a>";
						out.println("<tr><td>" + id + "</td><td>" + login + "</td><td>" + role + "</td><td>" 
								+ editLink + "</td></tr>");
					}
					%>
				</tbody>
			</table>
		</div>
		<jsp:include page="_footer.jsp"></jsp:include>
	</div>
</body>
</html>