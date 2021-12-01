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
	<h1 style="color: red">Error</h1>
	<h2>Exception occurred while processing the request</h2>
	<p>
		Type:
		<%=exception%></p>
	<p>
		Message:
		<%=message%></p>

	<b><a href="index.jsp">go home >>></a></b>

</body>
</html>