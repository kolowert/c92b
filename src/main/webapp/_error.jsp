<!DOCTYPE html>
<%
String message = pageContext.getException().getMessage();
String exception = pageContext.getException().getClass().toString();
%>

<html>
<head>
<meta charset="UTF-8">
<title>Exception</title>
</head>
<body>
	<jsp:include page="_header-light.jsp"></jsp:include>
	<h1 style="color: red">Error</h1>
	<h2>Exception occurred while processing the request</h2>
	<p>
		Type:
		<%=exception%></p>
	<p>
		Message:
		<%=message%></p>

	<b><a href="${pageContext.request.contextPath}/index.jsp">go home >>></a></b>
	<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>