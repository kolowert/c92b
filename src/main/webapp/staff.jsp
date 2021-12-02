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
	<jsp:include page="_header.jsp"></jsp:include>
	
	<jsp:include page="_menu-expert.jsp"></jsp:include>

	<br /><h4>List of all operators:</h4>
	<%
	DaoOperator daoOperator = DaoOperator.getInstance();
	List<Operator> operators = daoOperator.getOperators();
	
	for (Operator operator : operators) {
		int id = operator.getId();
		out.println(operator.briefInfo() + "\t" + "<a href=\"/editOper?id=" + id + "\">edit</a><br />");
	}
	%>	
	
	<jsp:include page="_footer.jsp"></jsp:include>
	
</body>
</html>