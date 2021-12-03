<%@page contentType="text/html" pageEncoding="UTF-8"
	import="fun.kolowert.c92b.bean.Operator"
	import="fun.kolowert.c92b.dao.DaoOperator" import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<title>Staff Page</title>
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

		<div class="container">
			<div class="row">
				<div class="col-sm-6">
					<h5 class="text-muted">Operators:</h5>
				</div>
				<div class="col-sm-6">
					<p style="text-align: right">
						Register new Operator
						<button id="newOperBtn" type="button" class="btn-info">new</button>
					</p>
				</div>
			</div>
		</div>

		<div class="container-lg">
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
						String path = request.getContextPath();
						String editLink = "<a href=\"" + path + "/edit-oper.jsp?id=" + id + "\">edit</a>";
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