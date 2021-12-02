<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="fun.kolowert.c92b.bean.Operator"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Base Page</title>
</head>

<body>
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
	
	<jsp:include page="_header.jsp"></jsp:include>
	
	<jsp:include page="_menu-expert.jsp"></jsp:include>
	
	<h5 style="color: Brown"><%=dutyOperator.briefInfo()%></h5>

	<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>