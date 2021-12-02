<%@page contentType="text/html" pageEncoding="UTF-8"
	import="fun.kolowert.c92b.bean.Operator"
	import="fun.kolowert.c92b.dao.DaoOperator"
	import="java.util.List"
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Page</title>
</head>

<body>
	<b>List of all operators:</b><br />
	<%
	DaoOperator daoOperator = DaoOperator.getInstance();
	List<Operator> operators = daoOperator.getOperators();
	
	for (Operator operator : operators) {
		int id = operator.getId();
		out.println(operator.briefInfo() + "\t" + "<a href=\"/editOper?id=" + id + "\">edit</a><br />");
	}
	%>	
	
</body>
</html>