<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
import="fun.kolowert.c92b.bean.Operator"
import="fun.kolowert.c92b.dao.DaoOperator"
import="fun.kolowert.c92b.dao.DaoRole"
import="fun.kolowert.c92b.utility.Utils"
import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<title>Input Edit Operator</title>
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
			<br /><h5 style="text-align: left">Edit Operator</h5><br />
		</div>
		
		<%

		String editLogin = "undefined";
		int	editId = Utils.parseIntIdFromObject(request.getParameter("id"));

		DaoOperator daoOperator = DaoOperator.getInstance();
		Operator operator = daoOperator.getOperatorById(editId);
		
		if (operator != null) {
			editLogin = operator.getLogin();
		}
		
		%>
		
		<form action="oper" method="POST">
			<input type="hidden" name="task" value="editOper">
			<input type="hidden" name="id" value=<%=editId%>>
			id: <%=editId%> &nbsp; | &nbsp; 
			Login: <input name="login" value=<%= editLogin %> /> &nbsp; &nbsp; 
			Role: <select name="role">
			<%
				DaoRole daoRole = DaoRole.getInstance();
				List<String> roles = daoRole.getRoles();
				for (String role : roles) {
					out.println("<option>" + role + "</option>");
				}
			%>
			</select> &nbsp; &nbsp; Password*: <input type="password" name="password" />
			&nbsp; &nbsp; <input type="submit" value="edit" />
			&nbsp; &nbsp; <input type="button" onclick="location.href='staff.jsp'" value="cancel" />
		</form>

		<br /><br />
		<h6 style="text-align: right" class="text-muted"><small>* do not enter a password to keep the current one</small></h6>
		
		<form action="oper" method="POST">
			<input type="hidden" name="task" value="deleteOper">
			<input type="hidden" name="id" value=<%=editId%>>
			&nbsp; &nbsp; <input type="submit" value="delete operator" />
		</form>
		
		<jsp:include page="_footer.jsp"></jsp:include>
	</div>
</body>
</html>